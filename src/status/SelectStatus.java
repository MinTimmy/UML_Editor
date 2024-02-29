package status;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import component.SelectionArea;
import component.Shape;

public class SelectStatus extends Status{
    private ArrayList<Point> originOffset = new ArrayList<Point>();
    private ArrayList<Shape> pressedShapes = new ArrayList<Shape>();
    private SelectionArea selectionArea;
    private Point selectionAreaBeginPoint;

    public SelectStatus() {
        super();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        this.canvas.unselectAllObject();
        this.pressedShapes = this.canvas.getSelectedShapes(pressPoint.x,  pressPoint.y);
        
        if (!this.pressedShapes.isEmpty()) {
            originOffset.clear();
            for (Shape shape : this.pressedShapes) {
                shape.setIsSelected(true);
                Point location = shape.getLocation();
                originOffset.add(new Point(location.x - pressPoint.x, location.y - pressPoint.y));
            }
            
        }
        else{
            if (selectionArea == null){
                selectionArea = new SelectionArea();
                selectionAreaBeginPoint = pressPoint;
                this.canvas.addShape(selectionArea, -1);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (selectionArea != null){
            Rectangle sa  = selectionArea.getSelectionArea();
            Point p1 = new Point(sa.x, sa.y);
            Point p2 = new Point(sa.x + sa.width, sa.y + sa.height);
            this.canvas.getSelectedShapes(p1, p2);
            this.canvas.removeShape(selectionArea);
            selectionArea = null;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if (!this.pressedShapes.isEmpty()){
            int index = 0;
            for (Shape shape : this.pressedShapes) {
                shape.setIsSelected(true);
                shape.setLocation(e.getX() + originOffset.get(index).x, e.getY() + originOffset.get(index).y);
                shape.refreshLine();
                index++;
            }
        }
        if (this.selectionArea != null){
            this.selectionArea.renewBound(selectionAreaBeginPoint, e.getPoint());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        this.canvas.unselectAllObject();
        ArrayList<Shape> shapes = this.canvas.getSelectedShapes(clickPoint.x, clickPoint.y);

        if (!shapes.isEmpty()) {            
            for (Shape shape : shapes) {
                shape.refreshLine();
            }
        }
    }
}
