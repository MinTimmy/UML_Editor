package status.lineStatus;


import component.line.GeneralizationLine;
import component.line.Line;

public class GeneralizationLineStatus extends NewLineStatus{
    public GeneralizationLineStatus() {
        super();
    }


    @Override
    protected Line newLine() {
        // return null;
        return new GeneralizationLine();
    }

}
