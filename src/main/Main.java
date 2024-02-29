package main;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import button.LeftPanelButton;
import button.MenuBar;

public class Main {    
    private static MFrame frame;
    private static MCanvas canvas;
    private static LeftPanelButton leftPanelButtons;
    private static JPanel container;
    private static MenuBar menuBar;



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
            // test();
        });
    }

    private static void createAndShowGUI() {
        frame = new MFrame();

        // Create canvas on the right
        canvas = MCanvas.getInstance();

        // Create buttons on the left
        leftPanelButtons = new LeftPanelButton();        


        // Create a container
        container = new JPanel(new BorderLayout());
        container.add(canvas);
        container.add(leftPanelButtons, BorderLayout.WEST);
        frame.add(container);

        menuBar = new MenuBar();
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    public static MFrame getFrame(){
        return frame;
    }
}