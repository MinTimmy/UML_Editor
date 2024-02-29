package component;

import java.awt.Graphics;

import javax.swing.JPanel;

import main.MCanvas;

public abstract class Shape extends JPanel{
    protected int width;
    protected int height; 
    protected boolean isSelected = false;
    protected int groupID = -1;


    public Shape(){
        super();
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void refreshLine(){
    }
    
    public void setIsSelected(boolean isSelected){
        this.isSelected = isSelected;
        MCanvas.getInstance().refresh();
    }
    public void setLabelText(String labelText){
    }

    public void setGroupID(int groupID){
        this.groupID = groupID;
    }
    public int getGroupID(){
        return this.groupID;
    }
    public int getLineCount(){
        return -1;
    }
    
    public boolean getIsSelected() {
        return this.isSelected;
    }
}
