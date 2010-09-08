package peripheral.logic.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import peripheral.logic.positioningtool.ActionTool;
import peripheral.logic.symboladapter.SymbolAdapter;

public abstract class ActionToolAction extends Action {

    public ActionToolAction(SymbolAdapter adapter) {
        super(adapter);
    }

    public abstract void execute(ActionTool tool);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    public ActionToolAction clone() {
        ActionToolAction copy = null;

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(out);

            os.writeObject(this);
            os.flush();

            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream is = new ObjectInputStream(in);

            copy = (ActionToolAction) is.readObject();

            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return copy;
    }
}

