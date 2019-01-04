package Controller;

import ADTs.IDictionary;
import ADTs.MyTuple;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapReadingException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;
import Model.Expressions.VarExpression;
import Model.ProgramState;
import Model.Statements.CloseRFile;
import Repository.IRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Controller {
    private IRepo repository;
    private String flag;
    private ExecutorService executor;

    public Controller(IRepo repo, String flag) {
        this.repository = repo;
        this.setFlag(flag);
    }

    private Map<Integer,Integer> garbageCollector(Collection<Integer> symTableValues, Map<Integer, Integer> heap) {
        return heap.entrySet().stream().filter(e->symTableValues.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void closeOpenedFiles(ProgramState state) throws IOException, UndefinedOperationException, UndefinedVariableException, DivisionByZeroException, HeapReadingException {
        Collection<Integer> fileTableKeys = state.getFileTable().keys();
        IDictionary<String, Integer> symTable = state.getSymTable();
        IDictionary<Integer, MyTuple<String, BufferedReader>> fileTable = state.getFileTable();
        List<Map.Entry<String, Integer>> keys = symTable.getDictionary().entrySet().stream().filter(file->fileTableKeys.contains(file.getValue())).collect(Collectors.toList());
        for(Map.Entry<String, Integer> entry : keys) {
            if(fileTable.containsKey(entry.getValue()))
                new CloseRFile(new VarExpression(entry.getKey())).execute(state);
        }
        if (this.flag.equals("on")) {
            System.out.println(state.toString());
        }
        this.repository.logPrgStateExec(state);
    }

    private List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList) {
        return inPrgList.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }

    private void oneStepForAllPrg(List<ProgramState> prgList) throws IOException, InterruptedException{
        List<Callable<ProgramState>> callList = prgList.stream().map((ProgramState p)->(Callable<ProgramState>)(p::oneStep)).collect(Collectors.toList());
        List<ProgramState> newPrgList = this.executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception ex) {
                        ex.printStackTrace(System.out);
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
        prgList.addAll(newPrgList);
        for(ProgramState state : prgList) {
            this.repository.logPrgStateExec(state);
            System.out.println(state.toString());
        }
        this.repository.setPrgList(prgList);
    }

    public void allStep() throws InterruptedException, IOException, UndefinedOperationException, UndefinedVariableException, DivisionByZeroException, HeapReadingException{
        this.executor = Executors.newFixedThreadPool(2);
        List<ProgramState> prgList = this.removeCompletedPrg(this.repository.getPrgList());
        Map<Integer, Integer> commonHeap = prgList.get(0).getHeap().getDictionary();
        while(prgList.size() > 0) {
            for(ProgramState prg : prgList) {
                Collection<Integer> symTable = prg.getSymTable().values();
                this.garbageCollector(symTable, commonHeap);
            }
            this.oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(this.repository.getPrgList());
        }
        executor.shutdown();
        this.closeOpenedFiles(this.repository.getPrgList().get(0));
        this.repository.setPrgList(prgList);
    }

    public boolean isEmpty() {
        return this.repository.isEmpty();
    }

    public void addProgram(ProgramState prg) {
        this.repository.add(prg);
    }


    private void setFlag(String value) {
        this.flag = value;
    }
}
