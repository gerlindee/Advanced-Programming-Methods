package Model.Expressions;

import ADTs.IDictionary;
import Exceptions.UndefinedVariableException;
import Heap.IHeap;

public class VarExpression extends IExpression {
    private String variable;

    public VarExpression(String var) {
        this.variable = var;
    }

    @Override
    public int eval(IDictionary<String, Integer> table, IHeap heap) throws UndefinedVariableException {
        if(table.get(this.variable) != null)
            return table.get(this.variable);
        throw new UndefinedVariableException();
    }

    public String toString() {
        return this.variable;
    }
}
