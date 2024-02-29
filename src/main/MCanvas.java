package main;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import component.object.Object;
import component.Shape;

public class MCanvas extends JPanel{
    private static ArrayList<ArrayList<Shape>> groupedShapes;

    private static MCanvas instance;
    
    private MCanvas(){
        super();
        groupedShapes = new ArrayList<ArrayList<Shape>>();
        MCanvasMouseListener mouseListener= MCanvasMouseListener.getInstance();
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setVisible(true);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
    }
    public static synchronized MCanvas getInstance() {
        if (instance == null) {
            instance = new MCanvas();
        }
        return instance;
    }
    // for mouse click
    public ArrayList<Shape> getSelectedShapes(int x, int y){
        MCanvas.getInstance().unselectAllObject();
        Component component = this.getComponentAt(x, y);
        ArrayList<Shape> selectedShapes = new ArrayList<>();

        if (component instanceof Object){
            Shape shape = (Shape) component;
            // the selection object has been grouped
            if (shape.getGroupID() != -1){
                groupedShapes.get(shape.getGroupID()).remove(shape);
                groupedShapes.get(shape.getGroupID()).add(0,shape);
                for (Shape s : groupedShapes.get(shape.getGroupID())){
                    s.setIsSelected(true);
                }
                MCanvas.getInstance().refresh();
                return groupedShapes.get(shape.getGroupID());
            }

            // the selection object has not been grouped
            selectedShapes.add(shape);
            this.remove(shape);
            this.addShape(shape, shape.getLineCount());
            // this.addShape(shape, 0);
            shape.setIsSelected(true);
            return selectedShapes;
        }
        else
            return selectedShapes;
    }

    // for mouse drag the selection area
    public ArrayList<Shape> getSelectedShapes(Point beginPoint, Point endPoint){
        Component[] components = this.getComponents();
        ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
        for (Component component : components){
            if (component instanceof Object){
                Object object = (Object) component;
                if (object.getBounds().intersects(beginPoint.x, beginPoint.y, endPoint.x - beginPoint.x, endPoint.y - beginPoint.y)){
                    object.setIsSelected(true);
                    selectedShapes.add(object);
                }
            }
        }
        return selectedShapes;
    }

    public ArrayList<Shape> getSelectedShapes() {
        ArrayList<Shape> selectedShapes = new ArrayList<Shape>();
        Component[] components = this.getComponents();
        for (Component component : components){
            if (component instanceof Object){
                Object object = (Object) component;
                if (object.getIsSelected()){
                    selectedShapes.add(object);
                }
            }
        }
        return selectedShapes;
    }
    public void checkComponent(){
        Component[] components = this.getComponents();
        for (Component component : components){
            System.out.println(component.getClass());
        }
        System.out.println("===================================="); 

    }
    public void addShape(Shape shape, int index){
        this.remove(shape);
        this.add(shape,index);
        MCanvas.getInstance().refresh();
    }
    public void removeShape(Shape shape){
        this.remove(shape);
        MCanvas.getInstance().refresh();
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }
    


    public void unselectAllObject() {
        Component[] components = this.getComponents();
        for (Component component : components){
            if (component instanceof Object){
                ((Object) component).setIsSelected(false);
            }
        }
        MCanvas.getInstance().refresh();
    }

    public void groupObject(ArrayList<Shape> selectedShapes){
        if (selectedShapes == null){
            selectedShapes = getSelectedShapes();
        }
        if (selectedShapes.size() < 2){
            return;
        }
        ArrayList<Shape> group = new ArrayList<Shape>();
        for (Shape shape : selectedShapes){
            if (shape.getGroupID() != -1 && shape.getGroupID() < groupedShapes.size()){
                int preIndex = shape.getGroupID();
                for (Shape s : groupedShapes.get(preIndex)){
                    s.setGroupID(groupedShapes.size());
                    group.add(s);
                }
                groupedShapes.get(preIndex).clear();
            }
            shape.setGroupID(groupedShapes.size());
            group.add(shape);
        }
        groupedShapes.add(group);
    }

    public void ungroupObject(ArrayList<Shape> selectedShapes){
        if (selectedShapes == null){
            selectedShapes = getSelectedShapes();
        }
        if (selectedShapes.size() < 2){
            return;
        }
        for (Shape shape : selectedShapes){
            if (shape.getGroupID() != -1){
                int groupID = shape.getGroupID();
                for (Shape s : groupedShapes.get(groupID)){
                    s.setGroupID(-1);
                }
                groupedShapes.get(groupID).clear();
                unselectAllObject();
            }
        }
    }
}
