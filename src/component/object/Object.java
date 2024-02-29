package component.object;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.OverlayLayout;

import main.Config;
import main.MCanvas;
import component.Shape;
import component.line.Line;

public abstract class Object extends Shape{
    protected int index;
    protected String labelText;
    private int lineCount = 0;

    /*
     *  0: _upper,
        1: _right,
        2: _bottom,
        3: _left,
     */
    protected ArrayList<ArrayList<LinePairs>> connectedLines = new ArrayList<ArrayList<LinePairs>>(); 

    public Object(Point location) {
        super();
        this.setLocation(location);
        this.setLayout(new OverlayLayout(this));
        this.setOpaque(true);
        for (Config.DirectionPort i : Config.DirectionPort.values()) {
            if (i == Config.DirectionPort.None) {
                continue;
            }
            this.connectedLines.add(new ArrayList<LinePairs>());
        }

    } 

    /**
     * 儲存每個連結的 line
     */
    private class LinePairs {
        public Line line;
        public Config.ArrowDirection arrowDirection; // 箭頭的方向

        public LinePairs(Line line, Config.ArrowDirection arrowDirection) {
            this.line = line;
            this.arrowDirection = arrowDirection;
        }
    }

    /*
     * 確認滑鼠點擊的位置是否在物件內
     */
    public boolean isInside(int mouseX, int mouseY) {
        if (
            mouseX <= this.getLocation().x + (this.width)   && 
            mouseX >= this.getLocation().x   && 
            mouseY <= this.getLocation().y + (this.height)  && 
            mouseY >= this.getLocation().y
        ) {
            return true;
        }
        return false;
    }
    /*
     * 取得物件的四個方向的點
     */ 
    public Point getDirectionPortPoints(Config.DirectionPort directionPort){
        
        switch (directionPort){
            case _upper:
                return new Point(this.getLocation().x + this.width / 2,                     this.getLocation().y + Config.dot_heigh / 2);               // top
            case _right:
                return new Point(this.getLocation().x + this.width - Config.dot_width / 2,  this.getLocation().y + (this.height/2));                    // right
            case _bottom:
                return new Point(this.getLocation().x + this.width / 2,                     this.getLocation().y + this.height - Config.dot_heigh / 2); // bottom
            case _left:
                return new Point(this.getLocation().x + Config.dot_width / 2,               this.getLocation().y + (this.height/2));                    // left
            default:
                return null;
        }
    }
    /*
     * 計算兩點之間的距離
     */
    public static double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
    /*
     * 取得最近的連接點
     */
    public Point getPortPoint(Point point){
        Config.DirectionPort port = Config.DirectionPort.None;
        double minDistance = Double.MAX_VALUE;
        for (Config.DirectionPort i : Config.DirectionPort.values()) {
            if (i == Config.DirectionPort.None) {
                continue;
            }
            double distance = calculateDistance(point,  this.getDirectionPortPoints(i));
            if (distance < minDistance) {
                minDistance = distance;
                port = i;
            }
        }
        return this.getDirectionPortPoints(port);
    }

    /*
     * 取得最近的連接點的方向
     */
    public Config.DirectionPort getPort(Point point){
        Config.DirectionPort port = Config.DirectionPort.None;
        double minDistance = Double.MAX_VALUE;
        for (Config.DirectionPort i : Config.DirectionPort.values()) {
            if (i == Config.DirectionPort.None) {
                continue;
            }
            double distance = calculateDistance(point,  this.getDirectionPortPoints(i));
            if (distance < minDistance) {
                minDistance = distance;
                port = i;
            }
        }
        return port;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void setLabelText(String labelText){
        this.labelText = labelText;
        MCanvas.getInstance().refresh();

    }

    public void addLine(Line line, Config.ArrowDirection arrowDirection) {
        if (arrowDirection == Config.ArrowDirection._arrow) {
            this.connectedLines.get(this.getPort(line.getEndPointCanvas()).ordinal()).add(new LinePairs(line, arrowDirection));
            lineCount++;
        }
        else if (arrowDirection == Config.ArrowDirection._tail) {
            this.connectedLines.get(this.getPort(line.getBeginPointCanvas()).ordinal()).add(new LinePairs(line, arrowDirection));
            lineCount++;
        }
    }

    public void removeLine(Line line) {
        for (ArrayList<LinePairs> pairsList : this.connectedLines) {
            Iterator<LinePairs> iterator = pairsList.iterator();
            while (iterator.hasNext()) {
                LinePairs linePairs = iterator.next();
                if (linePairs.line == line) {
                    iterator.remove();
                    lineCount--;
                    break;
                }
            }
        }
    }

    /**
     * 更新與此物件相連的所有 Line 的位置
     */
    public void refreshLine() {
        for (Config.DirectionPort i : Config.DirectionPort.values()) {
            if (i == Config.DirectionPort.None) {
                continue;
            }
            for (LinePairs pair : this.connectedLines.get(i.ordinal())) {
                Point point = this.getDirectionPortPoints(i);
                if (pair.arrowDirection == Config.ArrowDirection._tail) {
                    pair.line.setBeginPoint(point);
                } else {
                    pair.line.setEndPoint(point);
                }
                MCanvas.getInstance().remove(pair.line);
                MCanvas.getInstance().add(pair.line,0);
            }

        }
        MCanvas.getInstance().refresh();
    }
    @Override
    public int getLineCount() {
        return lineCount;
    }



}
