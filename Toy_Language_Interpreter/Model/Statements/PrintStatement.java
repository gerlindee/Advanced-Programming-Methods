package Model.Statements;

import ADTs.IDictionary;
import ADTs.IList;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapReadingException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;
import Heap.IHeap;
import Model.Expressions.*;
import Model.ProgramState;

public class PrintStatement implements IStatement {
    private IExpression exp;

    public PrintStatement(IExpression ex) {
        this.exp = ex;
    }

    public String toString() {
        return "Print(" + exp.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws UndefinedVariableException, DivisionByZeroException, UndefinedOperationException, HeapReadingException {
        IList<Integer> out = state.getOut();
        IDictionary<String, Integer> symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        Integer result = this.exp.eval(symTable, heap);
        out.addFirst(result);
        return state;
    }

}
