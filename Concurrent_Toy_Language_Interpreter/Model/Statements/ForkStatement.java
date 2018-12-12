package Model.Statements;

import ADTs.*;
import Exceptions.*;
import Heap.IHeap;
import Model.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement stmt) {
        this.statement = stmt;
    }

    public String toString() {
        return "fork(" + this.statement.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws HeapReadingException, IOException, MissingBufferReaderException, UndefinedVariableException, DivisionByZeroException, UndefinedOperationException, ExistingFileException {
        IStack<IStatement> new_stack = new MyStack<>();
        IDictionary<String, Integer> new_symTable = new MyDictionary<>();
        IList<Integer> new_out = state.getOut();
        IDictionary<Integer, MyTuple<String, BufferedReader>> new_fileTable = state.getFileTable();
        IHeap new_heap = state.getHeap();
        int new_id = state.getId() * 10;

        for(Map.Entry<String, Integer> elem : state.getSymTable().getDictionary().entrySet()) {
            new_symTable.put(elem.getKey(), elem.getValue());
        }

        return new ProgramState(new_stack, new_symTable, new_out, this.statement, new_fileTable, new_heap, new_id);
    }


}
