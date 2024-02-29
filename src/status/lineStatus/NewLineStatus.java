package status.lineStatus;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import component.line.Line;
import main.Config;
// import main.MCanvas;
import component.object.Object;
import component.Shape;
import status.Status;

public abstract class NewLineStatus extends Status{
    protected Object pressedObject = null;
    protected Object releasedObject = null;
    protected Point selectedPort;
    private Line line = null;


    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        ArrayList<Shape> shapes = this.canvas.getSelectedShapes(e.getX(), e.getY());
        if (this.line == null){
            if (shapes.size() == 0){
                return;
            }
            this.pressedObject = (Object)shapes.get(0);
            this.pressedObject.setIsSelected(true);
            this.line = newLine();
            this.selectedPort = this.pressedObject.getPortPoint(e.getPoint());
            
            this.line.mySetLocation(this.selectedPort, e.getPoint());
            this.pressedObject.addLine(this.line, Config.ArrowDirection._tail);
            this.canvas.addShape(this.line, 0);
        }
        else{
            if (this.pressedObject != null){
                this.pressedObject.setIsSelected(true);
            }
            this.line.mySetLocation(this.selectedPort, e.getPoint());
            this.canvas.addShape(this.line, 0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        if (this.line != null){
            ArrayList<Shape> shapes = this.canvas.getSelectedShapes(e.getX(), e.getY());
            if (this.pressedObject != null) {
                this.pressedObject.setIsSelected(true);
            }
            if (shapes.size() == 0){
                this.pressedObject.removeLine(this.line);
                this.canvas.removeShape(this.line);
                this.line = null;
                return;
            }
            this.releasedObject = (Object)shapes.get(0);
            if (this.releasedObject != this.pressedObject) {
                this.line.mySetLocation(this.selectedPort, this.releasedObject.getPortPoint(e.getPoint()));
                this.releasedObject.addLine(this.line, Config.ArrowDirection._arrow);
                this.canvas.addShape(this.line, 0);
            }
            else{
                this.pressedObject.removeLine(this.line);
                this.canvas.removeShape(this.line);
            }
            this.line = null;
        }
    }

    protected abstract Line newLine();
}
