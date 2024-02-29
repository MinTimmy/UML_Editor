package button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import main.MCanvas;
import main.Main;
import component.Shape;

public class MenuBar extends JMenuBar{
    private MCanvas canvas;

    public MenuBar(){    
        this.canvas = MCanvas.getInstance();

        JMenu editMenu = new JMenu("Edit");
        this.add(editMenu);
    
        JMenuItem item = new JMenuItem("Group");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.groupObject(null);
            }
        });
        editMenu.add(item);

        item = new JMenuItem("UnGroup");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.ungroupObject(null);
            }
        });
        editMenu.add(item);
    
        item = new JMenuItem("change object name");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Shape> selectedObjects = canvas.getSelectedShapes();
                if (selectedObjects != null){
                    if (selectedObjects.size() < 1){
                        return;
                    }
                    String input = JOptionPane.showInputDialog(Main.getFrame(), "Enter new label:");
                    for (Shape s : selectedObjects)
                        s.setLabelText(input);
                }
            }
        });
        editMenu.add(item);
    } 
}
