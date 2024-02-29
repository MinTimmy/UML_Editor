package status.objectStatus;

import java.awt.event.MouseEvent;

import component.object.UseCaseObject;
import status.Status;

public class UseCaseStatus extends Status{
    public UseCaseStatus() {
        super();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        UseCaseObject object = new UseCaseObject(this.clickPoint);
        this.canvas.addShape(object, 0);
    }
}
