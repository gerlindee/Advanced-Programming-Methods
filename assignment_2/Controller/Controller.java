package Controller;

import ADTs.IStack;
import Exceptions.EmptyContainerException;
import Model.ProgramState;
import Model.Statements.IStatement;
import Repository.IRepo;

public class Controller {
    private IRepo repository;
    private String flag;

    public Controller(IRepo repo, String flag) {
        this.repository = repo;
        this.setFlag(flag);
    }

    public boolean isEmpty() {
        return this.repository.isEmpty();
    }

    public void addProgram(ProgramState prg) {
        this.repository.add(prg);
    }

    private ProgramState oneStep(ProgramState state) {
        IStack<IStatement> stack = state.getExeStack();
        try {
            IStatement currentStmt = stack.pop();
            state = currentStmt.execute(state);
            if(this.flag.equals("on")) {
                System.out.println(state.toString());
            }
        }catch (EmptyContainerException ex) {
            System.out.println(ex.getMessage());
        }
        return state;
    }

    public void allStep() {
        ProgramState state = this.repository.getCrtPrg();
        int step = 0;
        while(!state.getExeStack().isEmpty()) {
            System.out.printf("Step: %d \n", step);
            oneStep(state);
            step++;
        }
        System.out.println("\n");
    }

    public void setFlag(String value) {
        this.flag = value;
    }
}
