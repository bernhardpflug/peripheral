package peripheral.logic.datatype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import peripheral.logic.symboladapter.ClonedSymbol;
import peripheral.logic.symboladapter.Symbol;

public class SymbolList implements Serializable {

    private Map<Symbol, List<ClonedSymbol>> symbols;
    private Map<Symbol, Integer> symbolCounts;

    public SymbolList() {
        symbols = new HashMap<Symbol, List<ClonedSymbol>>();
        symbolCounts = new HashMap<Symbol, Integer>();
    }

    public SymbolList(List<Symbol> symbols) {
        this();

        for (Symbol symbol : symbols) {
            addCloneOf(symbol);
        }
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
    /*public static SymbolList createSymbolList(List<Symbol> symbols) {
    SymbolList list = new SymbolList();

    for (Symbol symbol : symbols) {
    list.addSymbol(symbol);
    }

    return list;
    }*/
    public ClonedSymbol addCloneOf(Symbol source) {
        /*Symbol source = symbol;
        if (symbol instanceof ClonedSymbol) {
        source = ((ClonedSymbol) symbol).getSource();
        } else {
        if (map.containsKey(source)) {
        }
        }*/

        /*int cnt = 1;
        if (map.containsKey(symbol)) {
        cnt = map.get(symbol) + 1;
        }
        map.put(symbol, cnt);*/

        if (source instanceof ClonedSymbol) {
            source = ((ClonedSymbol) source).getSource();
        }

        if (!symbols.containsKey(source)) {
            symbols.put(source, new ArrayList<ClonedSymbol>());
        }

        ClonedSymbol clone;
        try {
            clone = source.cloneSymbol();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }

        symbols.get(source).add(clone);

        incNrOfClones(source);

        return clone;
    }

    public ClonedSymbol removeCloneOf(Symbol source) {
        if (symbols.containsKey(source)) {
            decNrOfClones(source);
            //remove last cloned symbol in list
            return symbols.get(source).remove(symbols.get(source).size() - 1);
        }
        return null;
    }

    public void incNrOfClones(Symbol source) {
        int cnt = 1;
        if (symbolCounts.containsKey(source)) {
            cnt = symbolCounts.get(source) + 1;
        }
        symbolCounts.put(source, cnt);
    }

    public void decNrOfClones(Symbol source) {
        if (symbolCounts.containsKey(source)) {
            symbolCounts.put(source, symbolCounts.get(source) - 1);
        }
    }

    /**
     *  <p style="margin-top: 0" align="left">
     *        test test2 adas fasd as<br>
     *      </p>
     */
    /*public Map<Symbol,Integer> getMap () {
    return map;
    }*/
    /**
     *  <p style="margin-top: 0">
     *        Intersects the actual SymbolList with the passed.
     *      </p>
     */
    /*public void intersect(SymbolList list) {
    }*/
    /**
     *  <p style="margin-top: 0">
     *        Merges the actual SymbolList with the passed one.
     *      </p>
     */
    /*public void merge(SymbolList list) {
    }

    public void remove(SymbolList list) {
    }

    public List<Symbol> getSymbols() {
    return null;
    }*/
    public Set<Symbol> getSourceSymbols() {
        return symbolCounts.keySet();
    }

    public int getNrOfClones(Symbol source) {
        if (symbolCounts.get(source) == null) {
            return 0;
        }
        return symbolCounts.get(source);
    }

    public List<ClonedSymbol> getClones (Symbol source){
        return this.symbols.get(source);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Symbol, Integer> entry : symbolCounts.entrySet()) {
            sb.append(entry.getKey() + ": " + entry.getValue());
            sb.append("; ");
        }
        try {
            sb.delete(sb.length() - 2, sb.length());

            return sb.toString();
        } catch (Exception e) {
        }
        return "";
    }
}

