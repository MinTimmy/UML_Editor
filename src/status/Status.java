package status;

import java.awt.Point;
import java.awt.event.MouseEvent;

import main.MCanvas;   

public abstract class Status{ 
    protected Point pressPoint;
    protected Point releasePoint;
    protected Point dragPoint;
    protected Point currentPoint;
    protected Point previousPoint;
    protected Point clickPoint;
    protected boolean isDragging;
    protected MCanvas canvas;


    public Status(){
        this.canvas = MCanvas.getInstance();
        this.pressPoint = null;
        this.releasePoint = null;
        this.dragPoint = null;
        this.currentPoint = null;
        this.previousPoint = null;
        this.isDragging = false;
        

    }

    public void mouseClicked(MouseEvent e) {
        this.clickPoint = e.getPoint();
    }

    public void mousePressed(MouseEvent e) {
        this.pressPoint = e.getPoint();
    }

    public void mouseReleased(MouseEvent e) {
        this.releasePoint = e.getPoint();
    }

    public void mouseDragged(MouseEvent e) {
        this.isDragging = true;
    }
}
