package Model.Statements;

import ADTs.IStack;
import Model.ProgramState;

public class CompStatement implements IStatement {
    private IStatement first;
    private IStatement second;

    public CompStatement(IStatement one, IStatement two) {
        this.first = one;
        this.second = two;
    }

    public String toString() {
        return "(" + first.toString() + "; " + second.toString() + ")";
    }

    public ProgramState execute(ProgramState state) {
        IStack<IStatement> stack = state.getExeStack();
        stack.push(this.second);
        stack.push(this.first);
        return null;
    }
}