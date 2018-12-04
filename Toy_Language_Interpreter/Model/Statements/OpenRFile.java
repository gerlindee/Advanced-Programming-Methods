package Model.Statements;

import ADTs.IDictionary;
import ADTs.MyTuple;
import Exceptions.ExistingFileException;
import Model.ProgramState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStatement {
    private String var_file_id;
    private String filename;

    public OpenRFile(String id, String name) {
        this.var_file_id = id;
        this.filename = name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ExistingFileException, IOException {
        IDictionary<Integer, MyTuple<String, BufferedReader>> file = state.getFileTable();
        IDictionary<String, Integer> sym = state.getSymTable();
        for(MyTuple<String,BufferedReader> item : file.values()) {
            if(item.getFirst().equals(this.var_file_id))
                throw new ExistingFileException();
        }
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        MyTuple<String, BufferedReader> tuple = new MyTuple<>(filename, reader);
        int key = state.getUniqueInt();
        file.put(key, tuple);
        sym.put(this.var_file_id, key);
        return state;
    }

    public String toString() {
        return "open(" + this.var_file_id + "," + this.filename + ")";
    }
}
