package Model.Expressions;

import ADTs.IDictionary;
import Exceptions.*;

public abstract class IExpression {
    public abstract int eval(IDictionary<String, Integer> table) throws UndefinedVariableException, DivisionByZeroException, UndefinedOperationException; // we put this as a parameter because our expressions may need to look up variable
                                                           // names in the symbol table
    public abstract String toString();
}
