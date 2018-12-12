package Model.Statements;

import ADTs.IDictionary;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapReadingException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;
import Heap.IHeap;
import Model.Expressions.IExpression;
import Model.ProgramState;

public class NewStatement implements IStatement {
    private String var_name;
    private IExpression expression;

    public NewStatement(String name, IExpression exp) {
        this.var_name = name;
        this.expression = exp;
    }

    public String toString() {
        return "new(" + this.var_name + "," + this.expression.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws UndefinedOperationException, UndefinedVariableException, DivisionByZeroException, HeapReadingException {
        IDictionary<String, Integer> symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        int value = this.expression.eval(symTable, heap);
        int address = heap.New(value);
        symTable.put(this.var_name, address);
        return null;
    }
}