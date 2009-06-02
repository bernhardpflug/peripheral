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
    private boolean needFilenameUserInput = true;

    public SymbolSwapAction(SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolSwapAction(SymbolAdapter adapter, Value filename) {
        this(adapter);
        this.filename = filename;
        this.needFilenameUserInput = false;
    }

    public String getFilename() {
        return filename.getValue().toString();
    }

    @Override
    public java.util.List<UserInput> getUserInput() {
        if (this.needFilenameUserInput) {
            if (filename == null) {
                filename = new ConstValue(adapter, "filename", "", String.class);
                userInput.add(new UserInput("Filename", "Filename of image to swap", filename));
            }
        }
        return userInput;
    }

    public String getDescription() {
        return "Swaps the image of the selected symbol to the specified new image.";
    }

    public void execute(Symbol s) {
        Runtime.getInstance().getVisualization().swapSymbol(s, getFilename());
    }
}

