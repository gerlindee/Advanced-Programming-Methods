package Repository;

import ADTs.MyList;
import Exceptions.EmptyContainerException;
import Model.ProgramState;

public class Repository implements IRepo{
    private MyList<ProgramState> states;

    public Repository() {
        this.states = new MyList<>();
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
}
