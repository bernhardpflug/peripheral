/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.filter;

import java.awt.Point;
import peripheral.logic.Logging;
import peripheral.logic.datatype.Percentage;
import peripheral.logic.positioningtool.Line;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;

/**
 *
 * @author Andi
 */
public class PositionFilter extends Filter{

    public PositionFilter(SymbolAdapter adapter, String outputVarName) {
        super(adapter, outputVarName);

        FilterInput fi = new FilterInput("percentage", new Class[]{Percentage.class});
        filterInputs.put(fi.getName(), fi);

        fi = new FilterInput("line", new Class[]{Line.class});
        filterInputs.put(fi.getName(), fi);

        outputValue = new ConstValue(adapter, outputVarName, new Point(), Point.class);
    }

    @Override
    public void doFilter() {
        Percentage percentage = (Percentage) getFilterInputValue("percentage");
        Line line = (Line) getFilterInputValue("line");

        Point pos = new Point();

        pos.x = line.getStartPoint().x + (int)((line.getEndPoint().x - line.getStartPoint().x)*percentage.getVal());
        pos.y = line.getStartPoint().y + (int)((line.getEndPoint().y - line.getStartPoint().y)*percentage.getVal());

        outputValue.setValue(pos);

        Logging.getLogger().finer("percentage=" + percentage + "; line=" + line + "; position=" + pos);
    }

    /*@Override
    public List<Class> getAcceptedInputTypes() {
        List<Class> acc = new ArrayList<Class>();

        acc.add(Percentage.class);

        return acc;
    }

    @Override
    public Class getOutputType() {
        return Point.class;
    }*/


}
