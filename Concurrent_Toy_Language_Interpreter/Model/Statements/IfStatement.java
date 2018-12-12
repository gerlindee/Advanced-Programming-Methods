package Model.Statements;

import ADTs.IDictionary;
import ADTs.IStack;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapReadingException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;
import Heap.IHeap;
import Model.Expressions.IExpression;
import Model.ProgramState;

public class IfStatement implements IStatement {
    private IExpression exe;
    private IStatement stmt1;
    private IStatement stmt2;

    public IfStatement(IExpression ex, IStatement thenS, IStatement elseS) {
        this.exe = ex;
        this.stmt1 = thenS;
        this.stmt2 = elseS;
    }

    public String toString() {
        return "IF(" + exe.toString() + ") THEN (" + this.stmt1.toString() + ") ELSE (" + this.stmt2.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws UndefinedVariableException, DivisionByZeroException, UndefinedOperationException, HeapReadingException {
        IStack<IStatement> exeStack = state.getExeStack();
        IDictionary<String, Integer> symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        if(this.exe.eval(symTable, heap) == 0) {
            exeStack.push(this.stmt2);
        }
        else {
            exeStack.push(this.stmt1);
        }
        return null;
    }
}