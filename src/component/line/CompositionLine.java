package component.line;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Color;


public class CompositionLine extends Line{

    @Override
    protected void drawTail(Graphics2D g2d, Point beginPoint, Point endPoint) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'drawTail'");
    }

    @Override
    protected void drawHead(Graphics2D g2d, Point beginPoint, Point endPoint) {
        double angle = Math.atan2(endPoint.y - beginPoint.y, endPoint.x - beginPoint.x);
		double arrowLength = arrowSize;

		// 計算箭頭的的座標
		int[] arrowX = { (int) Math.round(endPoint.x - arrowLength * Math.cos(angle - Math.PI / 4)),
				endPoint.x,
				(int) Math.round(endPoint.x - arrowLength * Math.cos(angle + Math.PI / 4)),
				(int) Math.round(endPoint.x - arrowLength * (Math.cos(angle - Math.PI / 4) + Math.cos(angle + Math.PI / 4))) };
		int[] arrowY = { (int) Math.round(endPoint.y - arrowLength * Math.sin(angle - Math.PI / 4)),
				endPoint.y,
				(int) Math.round(endPoint.y - arrowLength * Math.sin(angle + Math.PI / 4)),
				(int) Math.round(endPoint.y - arrowLength * (Math.sin(angle - Math.PI / 4) + Math.sin(angle + Math.PI / 4))) };

		// 繪製箭頭四角形
        g2d.setStroke(new BasicStroke(arrowThickness)); // Set thicker stroke
        g2d.setColor(Color.white);
		g2d.fillPolygon(new Polygon(arrowX, arrowY, 4));
		g2d.setColor(this.color);
		g2d.drawPolygon(new Polygon(arrowX, arrowY, 4));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
}
