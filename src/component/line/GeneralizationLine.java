package component.line;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Color;

public class GeneralizationLine extends Line{

    @Override
    protected void drawTail(Graphics2D g2d, Point beginPoint, Point endPoint) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'drawTail'");
    }

    @Override
    protected void drawHead(Graphics2D g2d, Point beginPoint, Point endPoint) {
        int x1 = beginPoint.x;
        int y1 = beginPoint.y;
        int x2 = endPoint.x;
        int y2 = endPoint.y;
        int size = arrowSize; // Size of the diamond
        double angle = Math.atan2(y2 - y1, x2 - x1);

        int[] xPoints = {
            x2,
            (int) (x2 - size * Math.cos(angle + Math.PI / 6)),
            (int) (x2 - size * Math.cos(angle - Math.PI / 6)),
            x2
        };
        int[] yPoints = {
            y2,
            (int) (y2 - size * Math.sin(angle + Math.PI / 6)),
            (int) (y2 - size * Math.sin(angle - Math.PI / 6)),
            y2
        };
        int nPoints = 4;
        g2d.setStroke(new BasicStroke(arrowThickness)); // Set thicker stroke

        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(xPoints, yPoints, nPoints);
        g2d.setColor(this.color);
        g2d.drawPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
}
