package Model;

import ADTs.*;
import Model.Statements.IStatement;

public class ProgramState {
    private IStack<IStatement> exeStack;
    private IDictionary<String, Integer> symTable;
    private IList<Integer> out;
    //private IStatement originalProgram;

    public ProgramState(IStack<IStatement> es, IDictionary<String, Integer> st, IList<Integer> ot, IStatement program) {
        this.exeStack = es;
        this.symTable = st;
        this.out = ot;

        this.exeStack.push(program);
    }

    public IStack<IStatement> getExeStack() {
        return this.exeStack;
    }

    public IDictionary<String, Integer> getSymTable() {
        return this.symTable;
    }

    public IList<Integer> getOut() {
        return this.out;
    }

    public String toString() {
        String result = "Program state: " + '\n';
        result = result + '\t' + "Execution stack: " + this.exeStack.toString() + '\n';
        result = result + '\t' + "Symbol table: " + this.symTable.toString() + '\n';
        result = result + '\t' + "Out: " +  this.out.toString() + '\n';
        return result;
    }

    public ProgramState() {
        this.exeStack = new MyStack<>();
        this.symTable = new MyDictionary<>();
        this.out = new MyList<>();
    }
}
