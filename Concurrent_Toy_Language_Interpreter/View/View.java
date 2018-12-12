package View;


import ADTs.*;
import Controller.Controller;
import Heap.Heap;
import Model.Expressions.ConstantExpression;
import Model.Expressions.HeapReadingExpression;
import Model.Expressions.VarExpression;
import Model.ProgramState;
import Model.Statements.*;
import Repository.Repository;

import java.io.BufferedReader;

public class View {

    public static void main(String[] args) {
        IStatement stmt = new CompStatement(
                new AssignStatement("v", new ConstantExpression(10)),
                new CompStatement(
                        new NewStatement("a", new ConstantExpression(22)),
                        new CompStatement(
                                new ForkStatement(
                                        new CompStatement(
                                                new HeapWritingStatement("a", new ConstantExpression(30)),
                                                new CompStatement(
                                                        new AssignStatement("v", new ConstantExpression(32)),
                                                        new CompStatement(
                                                                new PrintStatement(new VarExpression("v")),
                                                                new PrintStatement(new HeapReadingExpression("a"))
                                                        )
                                                )
                                        )
                                ),
                                new CompStatement(
                                        new PrintStatement(new VarExpression("v")),
                                        new PrintStatement(new HeapReadingExpression("a"))
                                )
                        )
                )
        );

        MyStack<IStatement> exeStack = new MyStack<>();
        MyDictionary<String, Integer> symTable = new MyDictionary<>();
        MyList<Integer> outTable = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> fileTable = new MyDictionary<>();
        Heap heap = new Heap();

        ProgramState prg = new ProgramState(exeStack, symTable, outTable, stmt, fileTable, heap, 1);
        Repository repo = new Repository("logfile12.txt");
        Controller ctrl = new Controller(repo, "on");
        ctrl.addProgram(prg);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", "prog_5", ctrl));

        menu.show();
    }
}