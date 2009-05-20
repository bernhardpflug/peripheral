package peripheral.logic.datatype;

import java.util.Map;
import peripheral.logic.symboladapter.Symbol;


public class SymbolList {

    private Map<Symbol,Integer> map;

    public SymbolList () {
    }

    /**
     *  <p style="margin-top: 0">
     *        SymbolList sList = new SymbolList();
     *      </p>
     *      <p style="margin-top: 0">
     *        for (Symbol s : symbols){ if (s instanceof ClonedSymbol){
     *      </p>
     *      <p style="margin-top: 0">
     *        Symbol source = ((ClonedSymbol)s).getSource(); int cnt = 1; if 
     *        (sList.map.containsKey(source)){ cnt = sList.map.get(source) + 1; }
     *      </p>
     *      <p style="margin-top: 0">
     *        sList.map.put(source, cnt); }
     *      </p>
     *      <p style="margin-top: 0">
     *        else{ int cnt = 1; if (sList.map.containsKey(s)){ cnt = sList.map.get(s) 
     *        + 1; } sList.map.put(s, cnt);
     *      </p>
     *      <p style="margin-top: 0">
     *        }
     *      </p>
     *      <p style="margin-top: 0">
     *        }
     *      </p>
     */
    public static SymbolList createSymbolList (java.util.Set<Symbol> symbols) {
        return null;
    }

    /**
     *  <p style="margin-top: 0" align="left">
     *        test test2 adas fasd as<br>
     *      </p>
     */
    public Map<Symbol,Integer> getMap () {
        return map;
    }

    /**
     *  <p style="margin-top: 0">
     *        Intersects the actual SymbolList with the passed.
     *      </p>
     */
    public void intersect (SymbolList list) {
    }

    /**
     *  <p style="margin-top: 0">
     *        Merges the actual SymbolList with the passed one.
     *      </p>
     */
    public void merge (SymbolList list) {
    }

    public void remove (SymbolList list) {
    }

    public java.util.Set<Symbol> getSymbols () {
        return null;
    }

}

