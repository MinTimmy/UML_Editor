package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import status.SelectStatus;
import status.Status;

public class MCanvasMouseListener extends MouseAdapter{
    private Status status;
    private static MCanvasMouseListener instance;

    private MCanvasMouseListener(){
    }

    public static synchronized MCanvasMouseListener getInstance() {
        if (instance == null) {
            instance = new MCanvasMouseListener();
            instance.setStatus(new SelectStatus());
        }
        return instance;
    }
    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.status.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       status.mouseReleased(e);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
       status.mouseClicked(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        status.mouseDragged(e);
    }

}
