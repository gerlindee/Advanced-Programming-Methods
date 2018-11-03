package Model.Expressions;

import ADTs.IDictionary;
import Exceptions.UndefinedVariableException;

public class VarExpression extends IExpression {
    private String variable;

    public VarExpression(String var) {
        this.variable = var;
    }

    public int eval(IDictionary<String, Integer> table) throws UndefinedVariableException {
        if(table.get(this.variable) != null)
            return table.get(this.variable);
        throw new UndefinedVariableException();
    }

    public String toString() {
        return this.variable;
    }
}
