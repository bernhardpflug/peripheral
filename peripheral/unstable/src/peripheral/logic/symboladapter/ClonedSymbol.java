package peripheral.logic.symboladapter;

import java.io.File;


public class ClonedSymbol extends Symbol {

    private Symbol source;

    public ClonedSymbol (Symbol source, File file) {
        super(file, null);

        this.source = source;
    }

    public Symbol getSource () {
        return source;
    }

    @Override
    public ClonedSymbol cloneSymbol() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Only instances of class Symbol may be cloned, not of subclass ClonedSymbol.");
    }


}

