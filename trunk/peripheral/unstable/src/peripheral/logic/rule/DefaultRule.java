/**
 * 
 */
package peripheral.logic.rule;

import peripheral.logic.symboladapter.SymbolAdapter;

/**
 * 
 * This subclass defines a default rule that the user can create
 * in the rules panel that defines a standard behavior of a symboladapter
 * if no other rule fits.
 * 
 * @author Berni
 *
 */
public class DefaultRule extends Rule {

	public DefaultRule(SymbolAdapter adapter) {
		super(adapter);
	}

}
