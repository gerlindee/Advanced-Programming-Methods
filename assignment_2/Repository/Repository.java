package Repository;

import ADTs.MyList;
import Exceptions.EmptyContainerException;
import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepo{
    private MyList<ProgramState> states;
    private String logFilePath;

    public Repository(String filePath) {
        this.states = new MyList<>();
        this.logFilePath = filePath;
    }

    public ProgramState getCrtPrg() {
        try{
            return this.states.remove();
        } catch (EmptyContainerException ex) {
            System.out.println(ex.getMessage());
        }
        return new ProgramState();
    }


    public boolean isEmpty() {
        return states.isEmpty();
    }

    public void add(ProgramState state) {
        this.states.addFirst(state);
    }

    public void logPrgStateExec(ProgramState state) throws IOException {
        FileWriter fileName = new FileWriter(this.logFilePath, true);
        BufferedWriter buff = new BufferedWriter(fileName);
        PrintWriter logFile = new PrintWriter(buff);
        logFile.println(state.toString());
        logFile.close();
    }
}

