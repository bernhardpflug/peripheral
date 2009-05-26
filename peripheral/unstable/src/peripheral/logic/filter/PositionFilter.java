/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package peripheral.logic.filter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import peripheral.logic.datatype.Percentage;
import peripheral.logic.positioningtool.Line;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;

/**
 *
 * @author Andi
 */
public class PositionFilter extends Filter{

    private Line line;

    public PositionFilter (SymbolAdapter adapter, Line line){
        super(adapter);
        this.line = line;
    }

    @Override
    public Object doFilter() {
        Percentage percentage = (Percentage)this.getInputValue();
        Point pos = new Point();

        pos.x = line.getStartPoint().x + (int)((line.getEndPoint().x - line.getStartPoint().x)*percentage.getVal());
        pos.y = line.getStartPoint().y + (int)((line.getEndPoint().y - line.getStartPoint().y)*percentage.getVal());

        new ConstValue(adapter, getOutputVarName(), pos);
        return pos;
    }

    @Override
    public List<Class> getAcceptedInputTypes() {
        List<Class> acc = new ArrayList<Class>();

        acc.add(Percentage.class);

        return acc;
    }

    @Override
    public Class getOutputType() {
        return Point.class;
    }


}
