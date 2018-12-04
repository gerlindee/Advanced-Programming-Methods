package View;

import ADTs.*;
import Controller.Controller;
import Heap.Heap;
import Model.Expressions.ArithmetricExpression;
import Model.Expressions.BooleanExpression;
import Model.Expressions.ConstantExpression;
import Model.Expressions.HeapReadingExpression;
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
        System.out.println("program_4: open(var_f, \"test_in\"); open(var_b, \"logfile1.txt\"); read(var_f,var_c); Print(var_c); (if var_c then read(var_f,var_c); Print(var_c) else Print(0)); close(var_f)");
        System.out.println("program_5: open(var_f,\"test.in\"); readFile(var_f+2,var_c); Print(var_c); (if var_c then read(var_f,var_c); Print(var_c) eles print(0)); close(var_f);");
        System.out.println("program_6: v = 10; new(v, 20); new(a, 22); print(v);");
        System.out.println("program_7: v = 10; new(v,20); print(100+rh(v)); print(100+rh(a));");
        System.out.println("program_8: v = 10; new(v,20); new(a,22); wH(a,30); print(a); print(rh(a)); a = 0;");
        System.out.println("program_9 : v = (10 + (2 < 6));");
        System.out.println("program_10 : v = 15 + (16 <= 12); Print(v); new(v,(14 > 13)); Print(v); new(a,(14==14)); writeHeap(a,(12!=12)); Print(a)");
        System.out.println("program_11 :  a = 3; b = 5; while(a<=b) { Print(a); a = a + 1; }");
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
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file1 = new MyDictionary<>();
        Heap heap1 = new Heap();

        ProgramState prg1 = new ProgramState(stack1,symTable1,out1,statement1,file1, heap1);

        Repository repo1 = new Repository("logfile1.txt");
        Controller ctrl1 = new Controller(repo1, "off");
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
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file2 = new MyDictionary<>();
        Heap heap2 = new Heap();

        ProgramState prg2 = new ProgramState(stack2, symTable2, out2, comp3, file2, heap2);

        Repository repo2 = new Repository("logfile2.txt");
        Controller ctrl2 = new Controller(repo2, "on");
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
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file3 = new MyDictionary<>();
        Heap heap3 = new Heap();

        ProgramState prg3 = new ProgramState(stack3, symTable3, out3, comp5, file3, heap3);

        Repository repo3 = new Repository("logfile3.txt");
        Controller ctrl3 = new Controller(repo3, "on");
        ctrl3.addProgram(prg3);

        // program_4: open(var_f, "test_in"); open(var_b, "logfile1.txt"); read(var_f,var_c); Print(var_c); (if var_c then read(var_f,var_c); Print(var_c) else Print(0)); close(var_f)

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
        OpenRFile open_d = new OpenRFile("var_b", "logfile1.txt");
        CompStatement comp10 = new CompStatement(open_d, comp9);
        CompStatement comp21 = new CompStatement(open1, comp10);

        MyStack<IStatement> stack4 = new MyStack<>();
        MyDictionary<String, Integer> symTable4 = new MyDictionary<>();
        MyList<Integer> out4 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file4 = new MyDictionary<>();
        Heap heap4 = new Heap();

        ProgramState prg4 = new ProgramState(stack4, symTable4, out4, comp21, file4, heap4);

        Repository repo4 = new Repository("logfile4.txt");
        Controller ctrl4 = new Controller(repo4, "on");
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
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file5 = new MyDictionary<>();
        Heap heap5 = new Heap();

        ProgramState prg5 = new ProgramState(stack5, symTable5, out5, comp15, file5, heap5);

        Repository repo5 = new Repository("logfile5.txt");
        Controller ctrl5 = new Controller(repo5, "on");
        ctrl5.addProgram(prg5);

        // program_6: v = 10; new(v, 20); new(a, 22); print(v);

        VarExpression varExpression = new VarExpression("v");
        ConstantExpression constantExpression = new ConstantExpression(20);
        AssignStatement assignStatement = new AssignStatement("v", new ConstantExpression(10));
        NewStatement newStatement1 = new NewStatement("v", constantExpression);
        NewStatement newStatement = new NewStatement("a",new ConstantExpression(22));
        PrintStatement printStatement = new PrintStatement(varExpression);
        CompStatement compStatement = new CompStatement(newStatement, printStatement);
        CompStatement compStatement1 = new CompStatement(newStatement1, compStatement);
        CompStatement compStatement2 = new CompStatement(assignStatement, compStatement1);

        MyStack<IStatement> stack6 = new MyStack<>();
        MyDictionary<String, Integer> symTable6 = new MyDictionary<>();
        MyList<Integer> out6 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file6 = new MyDictionary<>();
        Heap heap6 = new Heap();

        ProgramState prg6 = new ProgramState(stack6, symTable6, out6, compStatement2, file6, heap6);

        Repository repo6 = new Repository("logfile6.txt");
        Controller ctrl6 = new Controller(repo6, "on");
        ctrl6.addProgram(prg6);

        // program_7: v = 10; new(v,20); print(100+rh(v)); print(100+rh(a));

        AssignStatement assignStatement1 = new AssignStatement("v", new ConstantExpression(10));
        NewStatement newStatement2 = new NewStatement("v", constantExpression);
        NewStatement newStatement3 = new NewStatement("a",new ConstantExpression(22));
        HeapReadingExpression heapReadingExpression = new HeapReadingExpression("v");
        ArithmetricExpression arithmetricExpression = new ArithmetricExpression(new ConstantExpression(100), heapReadingExpression, "+");
        PrintStatement printStatement1 = new PrintStatement(arithmetricExpression);
        HeapReadingExpression heapReadingExpression1 = new HeapReadingExpression("a");
        ArithmetricExpression arithmetricExpression1 = new ArithmetricExpression(new ConstantExpression(100), heapReadingExpression1, "+");
        PrintStatement printStatement2 = new PrintStatement(arithmetricExpression1);
        CompStatement compStatement3 = new CompStatement(printStatement1, printStatement2);
        CompStatement compStatement4 = new CompStatement(newStatement3, compStatement3);
        CompStatement compStatement5 = new CompStatement(newStatement2, compStatement4);
        CompStatement compStatement6 = new CompStatement(assignStatement1, compStatement5);

        MyStack<IStatement> stack7 = new MyStack<>();
        MyDictionary<String, Integer> symTable7 = new MyDictionary<>();
        MyList<Integer> out7 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file7 = new MyDictionary<>();
        Heap heap7 = new Heap();

        ProgramState prg7 = new ProgramState(stack7, symTable7, out7, compStatement6, file7, heap7);

        Repository repo7 = new Repository("logfile7.txt");
        Controller ctrl7 = new Controller(repo7, "on");
        ctrl7.addProgram(prg7);

        // program_8: v = 10; new(v,20); new(a,22); wH(a,30); print(a); print(rh(a)); a = 0;

        VarExpression varExpression1 = new VarExpression("a");
        AssignStatement assignStatement2 = new AssignStatement("v", new ConstantExpression(10));
        NewStatement newStatement4 = new NewStatement("v", new ConstantExpression(20));
        NewStatement newStatement5 = new NewStatement("a",new ConstantExpression(22));
        HeapWritingStatement heapWritingStatement = new HeapWritingStatement("a", new ConstantExpression(30));
        PrintStatement printStatement3 = new PrintStatement(varExpression1);
        HeapReadingExpression heapReadingExpression2 = new HeapReadingExpression("a");
        PrintStatement printStatement4 = new PrintStatement(heapReadingExpression2);
        AssignStatement assignStatement3 = new AssignStatement("a", new ConstantExpression(0));
        CompStatement compStatement7 = new CompStatement(printStatement4, assignStatement3);
        CompStatement compStatement8 = new CompStatement(printStatement3, compStatement7);
        CompStatement compStatement9 = new CompStatement(heapWritingStatement, compStatement8);
        CompStatement compStatement10 = new CompStatement(newStatement5, compStatement9);
        CompStatement compStatement11 = new CompStatement(newStatement4, compStatement10);
        CompStatement compStatement12 = new CompStatement(assignStatement2, compStatement11);


        MyStack<IStatement> stack8 = new MyStack<>();
        MyDictionary<String, Integer> symTable8 = new MyDictionary<>();
        MyList<Integer> out8 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file8 = new MyDictionary<>();
        Heap heap8 = new Heap();

        ProgramState prg8 = new ProgramState(stack8, symTable8, out8, compStatement12, file8, heap8);

        Repository repo8 = new Repository("logfile8.txt");
        Controller ctrl8 = new Controller(repo8, "on");
        ctrl8.addProgram(prg8);

        // program_9 : v = (10 + (2 < 6));

        BooleanExpression booleanExpression = new BooleanExpression(new ConstantExpression(2),"<", new ConstantExpression(6));
        ArithmetricExpression arithmetricExpression2 = new ArithmetricExpression(new ConstantExpression(10), booleanExpression, "+");
        AssignStatement assignStatement4 =  new AssignStatement("v", arithmetricExpression2);

        MyStack<IStatement> stack9 = new MyStack<>();
        MyDictionary<String, Integer> symTable9 = new MyDictionary<>();
        MyList<Integer> out9 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file9 = new MyDictionary<>();
        Heap heap9 = new Heap();

        ProgramState prg9 = new ProgramState(stack9, symTable9, out9, assignStatement4, file9, heap9);

        Repository repo9 = new Repository("logfile9.txt");
        Controller ctrl9 = new Controller(repo9, "on");
        ctrl9.addProgram(prg9);

        // program_10 : v = 15 + (16 <= 12); Print(v); new(v,(14 > 13)); Print(v); new(a,(14==14)); writeHeap(a,(12!=12)); Print(a)

        BooleanExpression booleanExpression1 = new BooleanExpression(new ConstantExpression(16), "<=", new ConstantExpression(12));
        ArithmetricExpression arithmetricExpression3 = new ArithmetricExpression(new ConstantExpression(15), booleanExpression1, "+");
        AssignStatement assignStatement5 = new AssignStatement("v", arithmetricExpression3);
        PrintStatement printStatement5 = new PrintStatement(new VarExpression("v"));
        BooleanExpression booleanExpression2 = new BooleanExpression(new ConstantExpression(14), ">", new ConstantExpression(13));
        NewStatement newStatement6 = new NewStatement("v", booleanExpression2);
        PrintStatement printStatement6 = new PrintStatement(new VarExpression("v"));
        BooleanExpression booleanExpression3 = new BooleanExpression(new ConstantExpression(14), "==", new ConstantExpression(14));
        NewStatement newStatement7 = new NewStatement("a", booleanExpression3);
        BooleanExpression booleanExpression4 = new BooleanExpression(new ConstantExpression(12), "!=", new ConstantExpression(12));
        HeapWritingStatement heapWritingStatement1 = new HeapWritingStatement("a", booleanExpression4);
        PrintStatement printStatement7 = new PrintStatement(new VarExpression("a"));
        CompStatement compStatement13 = new CompStatement(heapWritingStatement1, printStatement7);
        CompStatement compStatement14 = new CompStatement(newStatement7, compStatement13);
        CompStatement compStatement15 = new CompStatement(printStatement6, compStatement14);
        CompStatement compStatement16 = new CompStatement(newStatement6, compStatement15);
        CompStatement compStatement17 = new CompStatement(printStatement5, compStatement16);
        CompStatement compStatement18 = new CompStatement(assignStatement5, compStatement17);

        MyStack<IStatement> stack10 = new MyStack<>();
        MyDictionary<String, Integer> symTable10 = new MyDictionary<>();
        MyList<Integer> out10 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file10 = new MyDictionary<>();
        Heap heap10 = new Heap();

        ProgramState prg10 = new ProgramState(stack10, symTable10, out10, compStatement18, file10, heap10);

        Repository repo10 = new Repository("logfile10.txt");
        Controller ctrl10 = new Controller(repo10, "on");
        ctrl10.addProgram(prg10);

        // program_11 : a = 3; b = 5; while(a<=b) { Print(a); a = a + 1; }

        AssignStatement assignStatement6 = new AssignStatement("a", new ConstantExpression(3));
        AssignStatement assignStatement7 = new AssignStatement("b", new ConstantExpression(5));
        ArithmetricExpression arithmetricExpression4 = new ArithmetricExpression(new VarExpression("a"), new ConstantExpression(1), "+");
        AssignStatement assignStatement8 = new AssignStatement("a", arithmetricExpression4);
        PrintStatement printStatement8 = new PrintStatement(new VarExpression("a"));
        CompStatement compStatement19 = new CompStatement(printStatement8, assignStatement8);
        BooleanExpression booleanExpression5 = new BooleanExpression(new VarExpression("a"), "<=", new VarExpression("b"));
        WhileStatement whileStatement = new WhileStatement(booleanExpression5, compStatement19);
        CompStatement compStatement20 = new CompStatement(assignStatement7, whileStatement);
        CompStatement compStatement21 = new CompStatement(assignStatement6, compStatement20);

        MyStack<IStatement> stack11 = new MyStack<>();
        MyDictionary<String, Integer> symTable11 = new MyDictionary<>();
        MyList<Integer> out11 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file11 = new MyDictionary<>();
        Heap heap11 = new Heap();

        ProgramState prg11 = new ProgramState(stack11, symTable11, out11, compStatement21,file11, heap11);

        Repository repo11 = new Repository("logfile11.txt");
        Controller ctrl11 = new Controller(repo11, "on");
        ctrl11.addProgram(prg11);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", "program_1", ctrl1));
        menu.addCommand(new RunExample("2", "program_2", ctrl2));
        menu.addCommand(new RunExample("3", "program_3", ctrl3));
        menu.addCommand(new RunExample("4", "program_4", ctrl4));
        menu.addCommand(new RunExample("5","program_5", ctrl5));
        menu.addCommand(new RunExample("6","program_6", ctrl6));
        menu.addCommand(new RunExample("7", "program_7", ctrl7));
        menu.addCommand(new RunExample("8", "program_8", ctrl8));
        menu.addCommand(new RunExample("9", "program_9", ctrl9));
        menu.addCommand(new RunExample("10", "program_10", ctrl10));
        menu.addCommand(new RunExample("11", "program_11", ctrl11));

        display();
        menu.show();

    }
}