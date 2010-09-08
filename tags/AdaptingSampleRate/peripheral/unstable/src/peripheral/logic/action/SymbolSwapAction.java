package peripheral.logic.action;

import java.io.File;
import peripheral.logic.Logging;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.value.UserInput;
import peripheral.logic.value.Value;
import peripheral.logic.Runtime;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;

public class SymbolSwapAction extends SymbolAction {

    private Value filename = null;
    private Value fileFolder = null;
    private boolean needFilenameUserInput = true;

    public SymbolSwapAction(SymbolAdapter adapter) {
        super(adapter);
    }

    public SymbolSwapAction(SymbolAdapter adapter, Value filename, Value fileFolder) {
        this(adapter);
        this.filename = filename;
        this.fileFolder = fileFolder;
        this.needFilenameUserInput = false;
    }

    public SymbolSwapAction(SymbolAdapter adapter, Value filename) {
        this(adapter, filename, null);
    }

    public String getFilename() {
        Object val = filename.getValue();
        if (val == null) {
            return null;
        }
        if (val instanceof File) {
            return getFileFolder() + ((File) val).getPath();
        }
        return getFileFolder() + val.toString();
    }

    private String getFileFolder() {
        if (fileFolder == null) {
            return "";
        }

        Object val = fileFolder.getValue();
        if (val == null) {
            return "";
        }

        if (val instanceof File) {
            return ((File) val).getPath() + "/";
        }

        return "";
    }

    @Override
    public java.util.List<UserInput> getUserInput() {
        if (this.needFilenameUserInput) {
            if (filename == null) {
                filename = new ConstValue(adapter, "filename", null, File.class);
                userInput.add(new UserInput("Filename", "Filename of image to swap", filename));
            }
        }
        return userInput;
    }

    public String getDescription() {
        return "Swaps the image of the selected symbol to the specified new image.";
    }

    public void execute(Symbol s) {
        boolean execute = s.getFile() == null;
        String path = getFilename() == null ? null : getFilename();
        if (!execute) {
            if (path == null) {
                execute = true;
            } else {
                execute = !s.getFile().getPath().replace("\\", "/").equals(path.replace("\\", "/"));
            }
        }

        if (execute) {
            if (path != null) {
                File newFile = new File(path);
                if (!newFile.exists()) {
                    newFile = null;
                    path = null;
                }
                s.setFile(newFile);
            } else {
                s.setFile(null);
            }
            Runtime.getInstance().getVisualization().swapSymbol(s, path);
            Logging.getLogger().finest("swap, new file: " + path);
        }
    }
}

