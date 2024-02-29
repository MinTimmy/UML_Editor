package component.line;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import component.Shape;
import main.MCanvas;

public abstract class Line extends Shape{
    protected Point beginPointCanvas;
    protected Point endPointCanvas;
    protected Point beginPoint;
    protected Point endPoint;
    protected Point LeftUpperConnerPoint;
    protected Color color;
    protected int lineWidth;
    protected int arrowSize = 20;
    protected int arrowThickness = 3;

    public Line() {
        super();
        init(Color.BLACK, 2);
    }

    private void init(Color color, int lineWidth){
        this.lineWidth = lineWidth;
        this.color = color;
        this.setOpaque(false);
        // this.setOpaque(true);
    }
    public Point getBeginPointCanvas(){
        return this.beginPointCanvas;
    }
    public Point getEndPointCanvas(){
        return this.endPointCanvas;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(this.color);
        // g2d.fillRect(200, 200, 200, 200);
        this.drawLine(g2d, beginPoint, endPoint);
        this.drawHead(g2d, beginPoint, endPoint);
        this.drawTail(g2d, beginPoint, endPoint);
    }
    public void setBeginPoint(Point beginPointCanvas){
        this.beginPointCanvas = beginPointCanvas;
        this.mySetLocation(this.beginPointCanvas, this.endPointCanvas);
    }
    public void setEndPoint(Point endPointCanvas){
        this.endPointCanvas = endPointCanvas;
        this.mySetLocation(this.beginPointCanvas, this.endPointCanvas);
    }
    public void mySetLocation(Point _beginPointCanvas, Point _endPointCanvas){
        // System.out.println("mySetLocation");
        this.beginPointCanvas = _beginPointCanvas;
        this.endPointCanvas = _endPointCanvas;
        this.beginPoint = new Point();
        this.endPoint = new Point();
        this.LeftUpperConnerPoint = new Point();

        this.LeftUpperConnerPoint.setLocation(
            (int) Math.min(this.beginPointCanvas.x, this.endPointCanvas.x),
            (int) Math.min(this.beginPointCanvas.y, this.endPointCanvas.y)
        );
        this.setLocation(this.LeftUpperConnerPoint);


        this.width   = (int) (Math.abs(this.beginPointCanvas.x - this.endPointCanvas.x) == 0 ? 1 : Math.abs(this.beginPointCanvas.x - this.endPointCanvas.x));
        this.height  = (int) (Math.abs(this.beginPointCanvas.y - this.endPointCanvas.y) == 0 ? 1 : Math.abs(this.beginPointCanvas.y - this.endPointCanvas.y));


        // right down
        if (this.beginPointCanvas.x <= this.endPointCanvas.x && this.beginPointCanvas.y < this.endPointCanvas.y) {
            this.beginPoint.setLocation(this.arrowSize, this.arrowSize);
            this.endPoint.setLocation(width + this.arrowSize, height + this.arrowSize);
            this.setBounds(
                this.LeftUpperConnerPoint.x - this.arrowSize, 
                this.LeftUpperConnerPoint.y - this.arrowSize,
                this.width + this.arrowSize * 2, 
                this.height + this.arrowSize * 2
            );
        } 

        // right up
        else if (this.beginPointCanvas.x < this.endPointCanvas.x && this.beginPointCanvas.y >= this.endPointCanvas.y) {
            this.beginPoint.setLocation(this.arrowSize, height + this.arrowSize);
            this.endPoint.setLocation(width + this.arrowSize, this.arrowSize);
            this.setBounds(
                this.LeftUpperConnerPoint.x - this.arrowSize, 
                this.LeftUpperConnerPoint.y - this.arrowSize,
                this.width + this.arrowSize * 2, 
                this.height + this.arrowSize * 2
            );
        } 
        // left down
        else if (this.beginPointCanvas.x > this.endPointCanvas.x && this.beginPointCanvas.y <= this.endPointCanvas.y) {
            this.beginPoint.setLocation(width + this.arrowSize, this.arrowSize);
            this.endPoint.setLocation(this.arrowSize, height + this.arrowSize);
            this.setBounds(
                this.LeftUpperConnerPoint.x - this.arrowSize, 
                this.LeftUpperConnerPoint.y - this.arrowSize,
                this.width + this.arrowSize * 2, 
                this.height + this.arrowSize * 2
            );
        } 
        // left up
        else if (this.beginPointCanvas.x >= this.endPoint.x && this.beginPointCanvas.y > this.endPointCanvas.y) {
            this.beginPoint.setLocation(width + this.arrowSize, height + this.arrowSize);
            this.endPoint.setLocation(this.arrowSize, this.arrowSize);
            this.setBounds(
                this.LeftUpperConnerPoint.x - this.arrowSize, 
                this.LeftUpperConnerPoint.y - this.arrowSize,
                this.width + this.arrowSize * 2, 
                this.height + this.arrowSize * 2
            );
        }
        MCanvas.getInstance().refresh();
    }

    protected abstract void drawTail(Graphics2D g2d, Point beginPoint, Point endPoint);
    protected abstract void drawHead(Graphics2D g2d, Point beginPoint, Point endPoint);
    protected void drawLine(Graphics2D g2d, Point beginPoint, Point endPoint){
        g2d.drawLine(beginPoint.x, beginPoint.y, endPoint.x, endPoint.y);
    }
    @Override
    public boolean contains(int x, int y) {
        return false;
    }



}
