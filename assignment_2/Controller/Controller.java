package Controller;

import ADTs.IStack;
import Exceptions.EmptyContainerException;
import Exceptions.ExistingFileException;
import Exceptions.MissingBufferReaderException;
import Model.ProgramState;
import Model.Statements.IStatement;
import Repository.IRepo;

import java.io.IOException;

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
        }catch (EmptyContainerException ex) {
            System.out.println(ex.getMessage());
        }catch (ExistingFileException ex1) {
            System.out.println(ex1.getMessage());
        }catch (IOException ex2) {
            System.out.println(ex2.toString());
        } catch (MissingBufferReaderException ex3) {
            System.out.println(ex3.getMessage());
        }
        return state;
    }

    public void allStep() {
        ProgramState state = this.repository.getCrtPrg();
        try {
            while (!state.getExeStack().isEmpty()) {
                oneStep(state);
                if (this.flag.equals("on")) {
                    System.out.println(state.toString());
                }
                this.repository.logPrgStateExec(state);
            }
            System.out.println("\n");
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public void setFlag(String value) {
        this.flag = value;
    }
}
