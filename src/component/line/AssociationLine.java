package component.line;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;


public class AssociationLine extends Line{
// public class AssociationLine extends JPanel{
    public AssociationLine() {
        // super(new AssociationLine());
        super();
    }
    @Override
    protected void drawTail(Graphics2D g2d, Point beginPoint, Point endPoint) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void drawHead(Graphics2D g2d, Point beginPoint, Point endPoint) {
        // TODO Auto-generated method stub
        // g2d.drawRect(endPoint.x, endPoint.y, 30, 30);
        int x1 = beginPoint.x;
        int y1 = beginPoint.y;
        int x2 = endPoint.x;
        int y2 = endPoint.y;
        double angle = Math.atan2(y2 - y1, x2 - x1);

        // Set thicker stroke for arrow lines
        g2d.setStroke(new BasicStroke(arrowThickness)); // Set thicker stroke
        // Draw arrow lines
        g2d.drawLine(x2, y2, 
                        (int) (x2 - arrowSize * Math.cos(angle - Math.PI / 6)),
                        (int) (y2 - arrowSize * Math.sin(angle - Math.PI / 6))
                    );
        g2d.drawLine(x2, y2, 
                        (int) (x2 - arrowSize * Math.cos(angle + Math.PI / 6)),
                        (int) (y2 - arrowSize * Math.sin(angle + Math.PI / 6))
                    );
    
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
}
