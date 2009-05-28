package peripheral.logic.action;

import java.util.ArrayList;
import java.util.List;
import peripheral.logic.symboladapter.Symbol; 
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;
import peripheral.logic.Runtime;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;

public class SymbolSwapAction extends SymbolAction {

    private Value filename = null;

    public SymbolSwapAction (SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolSwapAction (SymbolAdapter adapter, Value filename) {
        this(adapter);
        this.filename = filename;
    }

    public String getFilename () {
        return filename.getValue().toString();
    }

    @Override
    public java.util.List<UserInput> getUserInput () {
        List<UserInput> ui = new ArrayList<UserInput>();
        if (filename == null){
            ui.add(new UserInput("Filename", "Filename of image to swap", new ConstValue(adapter, "filename", "", String.class)));
        }
        return ui;
    }

    public String getDescription () {
        return "Swaps the image of the selected symbol to the specified new image.";
    }

    public void execute (Symbol s) {
        Runtime.getInstance().getVisualization().swap(s, getFilename());
    }

}

