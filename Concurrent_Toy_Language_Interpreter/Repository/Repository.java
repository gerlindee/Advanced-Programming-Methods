package Repository;

import Exceptions.EmptyContainerException;
import Model.ProgramState;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Repository implements IRepo{
    private List<ProgramState> states;
    private String logFilePath;

    public Repository(String filePath) {
        this.states = new LinkedList<>();
        this.logFilePath = filePath;
    }

    public boolean isEmpty() {
        return states.isEmpty();
    }

    public void add(ProgramState state) {
        this.states.add(0,state);
    }

    public void logPrgStateExec(ProgramState state) throws IOException {
        FileWriter fileName = new FileWriter(this.logFilePath, true);
        BufferedWriter buff = new BufferedWriter(fileName);
        PrintWriter logFile = new PrintWriter(buff);
        logFile.println(state.toString());
        logFile.close();
    }

    public List<ProgramState> getPrgList() {
        return this.states;
    }

    public void setPrgList(List<ProgramState> new_states) {
        this.states = new_states;
    }


}
