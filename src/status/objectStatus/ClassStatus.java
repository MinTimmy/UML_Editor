package status.objectStatus;

import java.awt.event.MouseEvent;

import component.object.ClassObject;
import status.Status;

public class ClassStatus extends Status{
    public ClassStatus() {
        super();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        ClassObject object = new ClassObject(this.clickPoint);
        this.canvas.addShape(object, 0);
    }
}
