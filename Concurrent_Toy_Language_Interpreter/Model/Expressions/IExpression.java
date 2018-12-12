package Model.Expressions;

import ADTs.IDictionary;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapReadingException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;
import Heap.IHeap;

public abstract class IExpression {
    public abstract int eval(IDictionary<String, Integer> table, IHeap heap) throws HeapReadingException, DivisionByZeroException, UndefinedOperationException, UndefinedVariableException;
    // we put this as a parameter because our expressions may need to look up variable names in the symbol table
    public abstract String toString();
}