package component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.OverlayLayout;

import main.MCanvas;


public class SelectionArea extends Shape{
    private static SelectionArea instance;
    private static Rectangle selectionArea;
    private MCanvas canvas;

    public SelectionArea(){
        super();
        canvas = MCanvas.getInstance();

        // this.setOpaque(true);
        selectionArea = new Rectangle(0,0,0,0);
        this.setLocation(new Point(0,0));
        this.setLayout(new OverlayLayout(this));
        this.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        this.setOpaque(false);

        instance = this;
    }

    public static synchronized SelectionArea getInstance() {
        if (instance == null) {
            instance = new SelectionArea();
        }
        return instance;
    }

    public void renewBound(Point beginPoint, Point endPoint) {
        int x       = Math.min(beginPoint.x, endPoint.x);
        int y       = Math.min(beginPoint.y, endPoint.y);
        int width   = Math.abs(endPoint.x - beginPoint.x);
        int height  = Math.abs(endPoint.y - beginPoint.y);
        selectionArea.setBounds(x, y, width, height);
        this.canvas.refresh();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!selectionArea.isEmpty()){
            g.setColor(Color.WHITE);
            g.fillRect(selectionArea.x, selectionArea.y, selectionArea.width, selectionArea.height);
            g.setColor(Color.BLACK);
            g.drawRect(selectionArea.x, selectionArea.y, selectionArea.width, selectionArea.height);
        }
    }
    @Override
    public boolean contains(int x, int y) {
        return false;
    }
    public Rectangle getSelectionArea(){
        return selectionArea;
    }
    
}
