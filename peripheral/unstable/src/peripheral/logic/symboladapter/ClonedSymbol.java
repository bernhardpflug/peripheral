package peripheral.logic.symboladapter;

import java.io.File;


public class ClonedSymbol extends Symbol {

    private Symbol source;

    public ClonedSymbol (File file) {
        super(file);
    }

    public Symbol getSource () {
        return source;
    }

    public void setSource (Symbol val) {
        this.source = val;
    }

}

