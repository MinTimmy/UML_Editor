package status.lineStatus;


import component.line.AssociationLine;
import component.line.Line;

public class AssociationLineStatus extends NewLineStatus{
    public AssociationLineStatus() {
        super();
    }


    @Override
    protected Line newLine() {
        // return null;
        return new AssociationLine();
    }


}
