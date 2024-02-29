package status.lineStatus;

import component.line.CompositionLine;
import component.line.Line;

public class CompositionLineStatus extends NewLineStatus{
    public CompositionLineStatus() {
        super();
    }


    @Override
    protected Line newLine() {
        // return null;
        return new CompositionLine();
    } 
}
