package Model.Statements;

import ADTs.IDictionary;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapReadingException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;
import Heap.IHeap;
import Model.Expressions.IExpression;
import Model.ProgramState;

public class HeapWritingStatement implements IStatement {
    private String var_name;
    private IExpression expression;

    public HeapWritingStatement(String name, IExpression exp) {
        this.var_name = name;
        this.expression = exp;
    }

    public ProgramState execute(ProgramState state) throws HeapReadingException, DivisionByZeroException, UndefinedVariableException, UndefinedOperationException {
        IDictionary<String, Integer> symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        int address = symTable.get(this.var_name);
        int new_value = this.expression.eval(symTable, heap);
        heap.Update(address, new_value);
        return null;
    }

    public String toString() {
        return "writeHeap(" + this.var_name + "," + this.expression.toString() + ")";
    }
}