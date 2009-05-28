package peripheral.logic.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.UserInput;


public abstract class Action implements Serializable, Observer {

    //@todo: sicherstellen, dass userInput wirklich nicht serialisiert werden muss
    //protected transient java.util.List<UserInput> userInput;

    protected SymbolAdapter adapter;

    public Action (SymbolAdapter adapter) {
        //userInput = new ArrayList<UserInput>();
        this.adapter = adapter;
    }

    /**
     *  <p style="margin-top: 0">
     *    for (UserInput ui : userInput){
     *  <br>  ui.addObserver(this);<br>}
     *      </p>
     */
    public abstract java.util.List<UserInput> getUserInput ();

    public abstract String getName ();

    public abstract String getDescription ();

    public SymbolAdapter getAdapter() {
        return adapter;
    }

    /**
     *  <p style="margin-top: 0">
     *    fires, when a user input of the action has been changed. method has to 
     *    be overriden in concrete subclasses to extract the needed parameters.
     *      </p>
     */
    public void update (Observable o, Object arg) {
    }

}

