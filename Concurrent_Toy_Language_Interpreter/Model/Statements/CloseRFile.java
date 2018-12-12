package Model.Statements;

import ADTs.IDictionary;
import ADTs.MyTuple;
import Exceptions.*;
import Heap.IHeap;
import Model.Expressions.IExpression;
import Model.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement {
    private IExpression exp_file_id;

    public CloseRFile(IExpression id) {
        this.exp_file_id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws UndefinedOperationException, UndefinedVariableException, DivisionByZeroException, IOException, HeapReadingException {
        IDictionary<String,Integer> sym = state.getSymTable();
        IDictionary<Integer, MyTuple<String, BufferedReader>> file = state.getFileTable();
        IHeap heap = state.getHeap();
        int value = this.exp_file_id.eval(sym, heap);
        BufferedReader buff = file.get(value).getSecond();
        if(buff == null) {
            throw new UndefinedVariableException();
        }
        buff.close();
        file.remove(value);
        return null;
    }

    public String toString() {
        return "close(" + this.exp_file_id.toString() + ")";
    }
}