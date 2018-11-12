package Model.Statements;

import ADTs.IDictionary;
import ADTs.IStack;
import ADTs.Tuple;
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
        IDictionary<Integer,Tuple<String, BufferedReader>> file = state.getFileTable();
        IDictionary<String, Integer> sym = state.getSymTable();
        for(Tuple<String,BufferedReader> item : file.values()) {
            if(item.getFirst().equals(var_file_id))
                throw new ExistingFileException("File already exists!");
        }
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        Tuple<String, BufferedReader> tuple = new Tuple<>(filename, reader);
        int key = state.getUniqueInt();
        file.put(key, tuple);
        sym.put(this.filename, key);
        return state;
    }
}
