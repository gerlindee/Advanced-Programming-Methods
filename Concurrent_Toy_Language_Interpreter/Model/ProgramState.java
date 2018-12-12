package Model;

import ADTs.*;
import Exceptions.*;
import Heap.IHeap;
import Model.Statements.IStatement;

import java.io.BufferedReader;
import java.io.IOException;

import ADTs.MyTuple;

public class ProgramState {
    private IStack<IStatement> exeStack;
    private IDictionary<String, Integer> symTable;
    private IList<Integer> out;
    private IDictionary<Integer, MyTuple<String, BufferedReader>> fileTable;
    private IHeap heap;
    private int id;


    public ProgramState(IStack<IStatement> es, IDictionary<String, Integer> st, IList<Integer> ot, IStatement program, IDictionary<Integer, MyTuple<String, BufferedReader>> ft, IHeap hp, int nid) {
        this.exeStack = es;
        this.symTable = st;
        this.out = ot;
        this.fileTable = ft;
        this.heap = hp;
        this.id = nid;

        this.exeStack.push(program);
    }

    public boolean isNotCompleted() {
        return !(this.exeStack.isEmpty());
    }

    public ProgramState oneStep() throws EmptyContainerException, HeapReadingException, IOException, MissingBufferReaderException, UndefinedVariableException, DivisionByZeroException, UndefinedOperationException, ExistingFileException{
        IStatement currentStmt = this.exeStack.pop();
        return currentStmt.execute(this);
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

    public IDictionary<Integer, MyTuple<String, BufferedReader>> getFileTable() {
        return this.fileTable;
    }

    public IHeap getHeap() {
        return this.heap;
    }

    public int getId() {
        return this.id;
    }

    public int getUniqueInt() {
        int i = 1;
        for(Integer inx : this.fileTable.keys()) {
            if(inx == i)
                i++;
        }
        return i;
    }

    public String toString() {
        String result = "";
        result = result + "PROGRAM ID: " + this.id + "\n";
        result = result + "EXECUTION STACK" + "\n" + result + "---------------" + "\n" + this.getExeStack().toString() + "\n";
        result = result + "SYMBOL TABLE" + "\n" + "------------" + "\n" + this.getSymTable().toString() + "\n";
        result = result + "OUT" + "\n" + "---" + "\n" + this.getOut().toString() + "\n";
        result = result + "FILE TABLE" + "\n" + "----------" + "\n" + this.getFileTable().toString() + "\n";
        result = result + "HEAP" + "\n" + "----" + "\n" + this.getHeap().toString() + "\n";
        result = result + "\n";
        return result;
    }
}
