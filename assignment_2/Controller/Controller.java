package Controller;

import ADTs.IStack;
import Exceptions.*;

import Model.ProgramState;
import Model.Statements.IStatement;
import Repository.IRepo;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class Controller {
    private IRepo repository;
    private String flag;

    public Controller(IRepo repo, String flag) {
        this.repository = repo;
        this.setFlag(flag);
    }

    private Map<Integer,Integer> garbageCollector(Collection<Integer> symTableValues, Map<Integer, Integer> heap) {
        return heap.entrySet().stream().filter(e->symTableValues.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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
        } catch (EmptyContainerException ex) {
            System.out.println(ex.getMessage());
        } catch (NullPointerException ex7) {
            System.out.println("Missing file!");
        } catch (ExistingFileException ex1) {
            System.out.println(ex1.getMessage());
        } catch (IOException ex2) {
            System.out.println(ex2.toString());
        } catch (MissingBufferReaderException ex3) {
            System.out.println(ex3.getMessage());
        } catch (UndefinedVariableException ex4) {
            System.out.println(ex4.getMessage());
        } catch (DivisionByZeroException ex5) {
            System.out.println(ex5.getMessage());
        } catch (UndefinedOperationException ex6) {
            System.out.println(ex6.getMessage());
        } catch (HeapReadingException ex7) {
            System.out.println(ex7.getMessage());
        }
        return state;
    }

    public void allStep() {
        try {
            ProgramState state = this.repository.getCrtPrg();
            while (!state.getExeStack().isEmpty()) {
                oneStep(state);
                state.getHeap().setDictionary((HashMap<Integer, Integer>)this.garbageCollector(state.getSymTable().values(),state.getHeap().getDictionary()));
                if (this.flag.equals("on")) {
                    System.out.println(state.toString());
                }
                this.repository.logPrgStateExec(state);
            }
            System.out.println("\n");
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (EmptyContainerException ex1) {
            System.out.println(ex1.getMessage());
        }
    }

    public void setFlag(String value) {
        this.flag = value;
    }
}
