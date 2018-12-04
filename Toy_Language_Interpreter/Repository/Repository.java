package Repository;

import ADTs.MyList;
import Exceptions.EmptyContainerException;
import Model.ProgramState;

import java.io.*;

public class Repository implements IRepo{
    private MyList<ProgramState> states;
    private String logFilePath;

    public Repository(String filePath) {
        this.states = new MyList<>();
        this.logFilePath = filePath;
    }

    public ProgramState getCrtPrg() throws EmptyContainerException {
        return this.states.remove();
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

