package peripheral.logic.action;

import java.util.Observable;
import peripheral.logic.value.UserInput;


public abstract class Action {

    protected java.util.Set<UserInput> userInput;

    public Action () {
    }

    /**
     *  <p style="margin-top: 0">
     *    for (UserInput ui : userInput){
     *  <br>  ui.addObserver(this);<br>}
     *      </p>
     */
    public abstract java.util.Set<UserInput> getUserInput ();

    public abstract String getName ();

    public abstract String getDescription ();

    /**
     *  <p style="margin-top: 0">
     *    fires, when a user input of the action has been changed. method has to 
     *    be overriden in concrete subclasses to extract the needed parameters.
     *      </p>
     */
    public void update (Observable o, Object arg) {
    }

}

