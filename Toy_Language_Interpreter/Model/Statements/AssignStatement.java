package Model.Statements;

import ADTs.IDictionary;
import Exceptions.*;
import Heap.IHeap;
import Model.Expressions.IExpression;
import Model.ProgramState;

public class AssignStatement implements IStatement {
    private String variable;
    private IExpression exp;

    public AssignStatement(String var, IExpression ex) {
        this.variable = var;
        this.exp = ex;
    }

    public String toString() {
        return this.variable + " = " + this.exp.toString();
    }

    public ProgramState execute(ProgramState state) throws UndefinedVariableException, DivisionByZeroException, UndefinedOperationException, HeapReadingException {
        IDictionary<String, Integer> symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        int result = this.exp.eval(symTable, heap);
        symTable.put(variable, result);
        return state;
    }
}
