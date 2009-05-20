package peripheral.logic.symboladapter;


public class ClonedSymbol extends Symbol {

    private Symbol source;

    public ClonedSymbol () {
    }

    public Symbol getSource () {
        return source;
    }

    public void setSource (Symbol val) {
        this.source = val;
    }

}

