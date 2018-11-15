package View;

import ADTs.*;
import Controller.Controller;
import Model.Expressions.ArithmetricExpression;
import Model.Expressions.ConstantExpression;
import Model.Expressions.VarExpression;
import Model.ProgramState;
import Model.Statements.*;
import Repository.Repository;

import java.io.BufferedReader;

public class View {
    private static void display() {
        System.out.println("Choose a program: ");
        System.out.println("program_1 : v = 2; Print(v)");
        System.out.println("program_2 : a = 2+3*5; b = a+1; Print(b)");
        System.out.println("program_3 : a = 2 - 2; (IF a THEN v = 2 ELSE v = 3); Print(v)");
        System.out.println("program_4: open(var_f, \"test_in\"); read(var_f,var_c); Print(var_c); (if var_c then read(var_f,var_c); Print(var_c) else Print(0)); close(var_f)");
        System.out.println("program_5: open(var_f,\"test.in\"); readFile(var_f+2,var_c); Print(var_c); (if var_c then read(var_f,var_c); Print(var_c) eles print(0)); close(var_f);");
    }

    public static void main(String[] args) {
        // program_1 : v = 2; Print(v)
        ConstantExpression const1 = new ConstantExpression(2);
        AssignStatement assign1 = new AssignStatement("v", const1);
        VarExpression var1 = new VarExpression("v");
        PrintStatement print1 = new PrintStatement(var1);
        CompStatement statement1 = new CompStatement(assign1, print1);

        MyStack<IStatement> stack1 = new MyStack<>();
        MyDictionary<String, Integer> symTable1 = new MyDictionary<>();
        MyList<Integer> out1 = new MyList<>();
        MyDictionary<Integer,Tuple<String, BufferedReader>> file1 = new MyDictionary<>();

        ProgramState prg1 = new ProgramState(stack1,symTable1,out1,statement1,file1);

        Repository repo1 = new Repository("logfile1.txt");
        Controller ctrl1 = new Controller(repo1, "on");
        ctrl1.setFlag("on");
        ctrl1.addProgram(prg1);

        // program_2 : a = 2+3*5; b = a+1; Print(b)

        ConstantExpression const2 = new ConstantExpression(3);
        ConstantExpression const3 = new ConstantExpression(5);
        ConstantExpression const4 = new ConstantExpression(1);
        ArithmetricExpression arith1 = new ArithmetricExpression(const2, const3, "*");
        ArithmetricExpression arith2 = new ArithmetricExpression(const1, arith1, "+");
        AssignStatement stmt2 = new AssignStatement("a", arith2);
        VarExpression var2 = new VarExpression("a");
        ArithmetricExpression arith3 = new ArithmetricExpression(var2, const4, "+");
        AssignStatement stmt3 = new AssignStatement("b", arith3);
        VarExpression var3 = new VarExpression("b");
        PrintStatement print2 = new PrintStatement(var3);
        CompStatement comp2 = new CompStatement(stmt3, print2);
        CompStatement comp3 = new CompStatement(stmt2, comp2);

        MyStack<IStatement> stack2 = new MyStack<>();
        MyDictionary<String, Integer> symTable2 = new MyDictionary<>();
        MyList<Integer> out2 = new MyList<>();
        MyDictionary<Integer,Tuple<String, BufferedReader>> file2 = new MyDictionary<>();

        ProgramState prg2 = new ProgramState(stack2, symTable2, out2, comp3, file2);

        Repository repo2 = new Repository("logfile2.txt");
        Controller ctrl2 = new Controller(repo2, "on");
        ctrl2.setFlag("on");
        ctrl2.addProgram(prg2);

        // program_3 : a = 2 - 2; (IF a THEN v = 2 ELSE v = 3); Print(v)

        ArithmetricExpression arith4 = new ArithmetricExpression(const1, const1, "-");
        AssignStatement assign3 = new AssignStatement("a", arith4);
        AssignStatement assign4 = new AssignStatement("v", const2);
        IfStatement ifstmt1 = new IfStatement(var2,assign1,assign4);
        CompStatement comp4 = new CompStatement(ifstmt1, print1);
        CompStatement comp5 = new CompStatement(assign3, comp4);

        MyStack<IStatement> stack3 = new MyStack<>();
        MyDictionary<String, Integer> symTable3 = new MyDictionary<>();
        MyList<Integer> out3 = new MyList<>();
        MyDictionary<Integer,Tuple<String, BufferedReader>> file3 = new MyDictionary<>();

        ProgramState prg3 = new ProgramState(stack3, symTable3, out3, comp5, file3);

        Repository repo3 = new Repository("logfile3.txt");
        Controller ctrl3 = new Controller(repo3, "on");
        ctrl3.setFlag("on");
        ctrl3.addProgram(prg3);

        // program_4: open(var_f, "test_in"); read(var_f,var_c); Print(var_c); (if var_c then read(var_f,var_c); Print(var_c) else Print(0)); close(var_f)

        VarExpression var4 = new VarExpression("var_f");
        ReadFile read1 = new ReadFile(var4, "var_c");
        VarExpression var5 = new VarExpression("var_c");
        PrintStatement print3 = new PrintStatement(var5);
        CompStatement comp6 = new CompStatement(read1, print3);
        ConstantExpression const5 = new ConstantExpression(0);
        PrintStatement print4 = new PrintStatement(const5);
        IfStatement if1 = new IfStatement(var5, comp6, print4);
        CloseRFile close1 = new CloseRFile(var4);
        CompStatement comp7 = new CompStatement(if1, close1);
        CompStatement comp8 = new CompStatement(print3, comp7);
        ReadFile read2 = new ReadFile(var4, "var_c");
        CompStatement comp9 = new CompStatement(read2, comp8);
        OpenRFile open1 = new OpenRFile("var_f", "test.in");
        CompStatement comp10 = new CompStatement(open1, comp9);

        MyStack<IStatement> stack4 = new MyStack<>();
        MyDictionary<String, Integer> symTable4 = new MyDictionary<>();
        MyList<Integer> out4 = new MyList<>();
        MyDictionary<Integer,Tuple<String, BufferedReader>> file4 = new MyDictionary<>();

        ProgramState prg4 = new ProgramState(stack4, symTable4, out4, comp10, file4);

        Repository repo4 = new Repository("logfile4.txt");
        Controller ctrl4 = new Controller(repo4, "on");
        ctrl4.setFlag("on");
        ctrl4.addProgram(prg4);

        // program_5: open(var_f,"test.in"); readFile(var_f+2,var_c); Print(var_c); (if var_c then read(var_f,var_c); Print(var_c) else print(0)); close(var_f);

        VarExpression var6 = new VarExpression("var_f");
        ReadFile read3 = new ReadFile(var6, "var_c");
        VarExpression var7 = new VarExpression("var_c");
        PrintStatement print5 = new PrintStatement(var7);
        CompStatement comp11 = new CompStatement(read3, print5);
        ConstantExpression const6 = new ConstantExpression(0);
        PrintStatement print6 = new PrintStatement(const6);
        IfStatement if2 = new IfStatement(var7, comp11, print6);
        CloseRFile close2 = new CloseRFile(var6);
        CompStatement comp12 = new CompStatement(if2, close2);
        CompStatement comp13 = new CompStatement(print5, comp12);
        ArithmetricExpression var8 = new ArithmetricExpression(var6, new ConstantExpression(2), "+");
        ReadFile read4 = new ReadFile(var8, "var_c");
        CompStatement comp14 = new CompStatement(read4, comp13);
        OpenRFile open2 = new OpenRFile("var_f", "test.in");
        CompStatement comp15 = new CompStatement(open2, comp14);

        MyStack<IStatement> stack5 = new MyStack<>();
        MyDictionary<String, Integer> symTable5 = new MyDictionary<>();
        MyList<Integer> out5 = new MyList<>();
        MyDictionary<Integer,Tuple<String, BufferedReader>> file5 = new MyDictionary<>();

        ProgramState prg5 = new ProgramState(stack5, symTable5, out5, comp15, file5);

        Repository repo5 = new Repository("logfile5.txt");
        Controller ctrl5 = new Controller(repo5, "on");
        ctrl5.setFlag("on");
        ctrl5.addProgram(prg5);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", "program_1", ctrl1));
        menu.addCommand(new RunExample("2", "program_2", ctrl2));
        menu.addCommand(new RunExample("3", "program_3", ctrl3));
        menu.addCommand(new RunExample("4", "program_4", ctrl4));
        menu.addCommand(new RunExample("5","program_5", ctrl5));
        display();
        menu.show();

    }
}
