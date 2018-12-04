package Repository;

import Exceptions.EmptyContainerException;
import Model.ProgramState;

import java.io.IOException;

public interface IRepo {
    ProgramState getCrtPrg() throws EmptyContainerException;
    boolean isEmpty();
    void add(ProgramState state);
    void logPrgStateExec(ProgramState state) throws IOException;
}
