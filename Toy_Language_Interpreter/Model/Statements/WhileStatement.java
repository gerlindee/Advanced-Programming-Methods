package Model.Statements;

import ADTs.IDictionary;
import ADTs.IStack;
import Exceptions.*;
import Heap.IHeap;
import Model.Expressions.IExpression;
import Model.ProgramState;

public class WhileStatement implements IStatement {
    private IExpression expression;
    private IStatement statement;

    public WhileStatement(IExpression exp, IStatement stmt) {
        this.expression = exp;
        this.statement = stmt;
    }

    public ProgramState execute(ProgramState state) throws HeapReadingException, UndefinedVariableException, DivisionByZeroException, UndefinedOperationException {
        IStack<IStatement> stack = state.getExeStack();
        IDictionary<String, Integer> symTable = state.getSymTable();
        IHeap heap = state.getHeap();
        if(this.expression.eval(symTable, heap) != 0) {
            stack.push(this);
            stack.push(this.statement);
        }
        return state;
    }

    public String toString() {
        return "while(" + this.expression.toString() + ")" + this.statement.toString();
    }
}
