package Repository;

import Model.ProgramState;

public interface IRepo {
    ProgramState getCrtPrg();
    boolean isEmpty();
    void add(ProgramState state);
}
