package Model.Statements;

import ADTs.IDictionary;
import ADTs.MyTuple;
import Exceptions.*;
import Heap.IHeap;
import Model.Expressions.IExpression;
import Model.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    private IExpression exp_file;
    private String var_name;

    public ReadFile(IExpression ex, String name) {
        this.exp_file = ex;
        this.var_name = name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws HeapReadingException, NullPointerException, MissingBufferReaderException, IOException, UndefinedVariableException, DivisionByZeroException, UndefinedOperationException {
        IDictionary<Integer, MyTuple<String, BufferedReader>> file = state.getFileTable();
        IDictionary<String, Integer> sym = state.getSymTable();
        IHeap heap = state.getHeap();
        int value = this.exp_file.eval(sym, heap);
        BufferedReader reader = file.get(value).getSecond();
        if(reader == null)
            throw new MissingBufferReaderException();
        else {
            String line = reader.readLine();
            int new_value = 0;
            if(line != null)
                new_value = Integer.parseInt(line);
            sym.put(this.var_name, new_value);
        }
        return state;
    }

    public String toString() {
        return "read(" + this.exp_file.toString() + "," + this.var_name + ")";
    }
}
