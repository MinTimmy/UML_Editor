package main;

import javax.swing.JFrame;


public class MFrame extends JFrame{
    public MFrame(){
        this.setTitle("UML Editor");
        this.setSize(Config.frame_width, Config.frame_height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
