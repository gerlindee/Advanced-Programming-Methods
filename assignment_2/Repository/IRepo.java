package Repository;

import Model.ProgramState;

import java.io.IOException;

public interface IRepo {
    ProgramState getCrtPrg();
    boolean isEmpty();
    void add(ProgramState state);
    void logPrgStateExec(ProgramState state) throws IOException;
}
