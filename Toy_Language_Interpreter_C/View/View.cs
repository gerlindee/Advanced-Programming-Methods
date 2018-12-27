using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace a7
{
    class View
    {
        static void Main(string[] args)
        {
            // program_1 : v = 2; Print(v)

            ConstantExpression const1 = new ConstantExpression(2);
            AssignStatement assign1 = new AssignStatement("v", const1);
            VarExpression var1 = new VarExpression("v");
            PrintStatement print1 = new PrintStatement(var1);
            CompStatement statement1 = new CompStatement(assign1, print1);

            MyStack<IStatement> stack1 = new MyStack<IStatement>();
            MyDictionary<String, int> symTable1 = new MyDictionary<String, int>();
            MyList<int> out1 = new MyList<int>();
            MyDictionary<int, MyTuple<String, StreamReader>> file1 = new MyDictionary<int, MyTuple<String, StreamReader>>();

            ProgramState prg1 = new ProgramState(stack1, symTable1, out1, file1, statement1);

            Repository repo1 = new Repository("logfile1.txt");
            Controller ctrl1 = new Controller(repo1);
            ctrl1.AddProgram(prg1); 

            // program_2 : a = 2+3*5; b = a+1; Print(b);

            ConstantExpression const2 = new ConstantExpression(3);
            ConstantExpression const3 = new ConstantExpression(5);
            ConstantExpression const4 = new ConstantExpression(1);
            ArithmeticExpression arith1 = new ArithmeticExpression(const2, const3, "*");
            ArithmeticExpression arith2 = new ArithmeticExpression(const1, arith1, "+");
            AssignStatement stmt2 = new AssignStatement("a", arith2);
            VarExpression var2 = new VarExpression("a");
            ArithmeticExpression arith3 = new ArithmeticExpression(var2, const4, "+");
            AssignStatement stmt3 = new AssignStatement("b", arith3);
            VarExpression var3 = new VarExpression("b");
            PrintStatement print2 = new PrintStatement(var3);
            CompStatement comp2 = new CompStatement(stmt3, print2);
            CompStatement comp3 = new CompStatement(stmt2, comp2);

            MyStack<IStatement> stack2 = new MyStack<IStatement>();
            MyDictionary<String, int> symTable2 = new MyDictionary<String, int>();
            MyList<int> out2 = new MyList<int>();
            MyDictionary<int, MyTuple<String, StreamReader>> file2 = new MyDictionary<int, MyTuple<String, StreamReader>>();

            ProgramState prg2 = new ProgramState(stack2, symTable2, out2, file2, comp3);

            Repository repo2 = new Repository("logfile2.txt");
            Controller ctrl2 = new Controller(repo2);
            ctrl2.AddProgram(prg2);

            // program_3 : a = 2 - 2; (IF a THEN v = 2 ELSE v = 3); Print(v);

            ArithmeticExpression arith4 = new ArithmeticExpression(const1, const1, "-");
            AssignStatement assign3 = new AssignStatement("a", arith4);
            AssignStatement assign4 = new AssignStatement("v", const2);
            AssignStatement assign0 = new AssignStatement("v", const1);
            IfStatement ifstmt1 = new IfStatement(var2, assign0, assign4);
            CompStatement comp4 = new CompStatement(ifstmt1, print1);
            CompStatement comp5 = new CompStatement(assign3, comp4);

            MyStack<IStatement> stack3 = new MyStack<IStatement>();
            MyDictionary<String, int> symTable3 = new MyDictionary<String, int>();
            MyList<int> out3 = new MyList<int>();
            MyDictionary<int, MyTuple<String, StreamReader>> file3 = new MyDictionary<int, MyTuple<String, StreamReader>>();

            ProgramState prg3 = new ProgramState(stack3, symTable3, out3, file3, comp5);

            Repository repo3 = new Repository("logfile3.txt");
            Controller ctrl3 = new Controller(repo3);
            ctrl3.AddProgram(prg3);

            // program_4: open(var_f, "test_in"); open(var_b, "logfile1.txt"); read(var_f,var_c); Print(var_c); (if var_c then read(var_f,var_c); Print(var_c) else Print(0)); close(var_f)

            VarExpression var4 = new VarExpression("var_f");
            ReadFileStatement read1 = new ReadFileStatement(var4, "var_c");
            VarExpression var5 = new VarExpression("var_c");
            PrintStatement print3 = new PrintStatement(var5);
            CompStatement comp6 = new CompStatement(read1, print3);
            ConstantExpression const5 = new ConstantExpression(0);
            PrintStatement print4 = new PrintStatement(const5);
            IfStatement if1 = new IfStatement(var5, comp6, print4);
            CloseFileStatement close1 = new CloseFileStatement(var4);
            CompStatement comp7 = new CompStatement(if1, close1);
            CompStatement comp8 = new CompStatement(print3, comp7);
            ReadFileStatement read2 = new ReadFileStatement(var4, "var_c");
            CompStatement comp9 = new CompStatement(read2, comp8);
            OpenFileStatement open1 = new OpenFileStatement("var_f", "test.in");
            OpenFileStatement open_d = new OpenFileStatement("var_b", "logfile1.txt");
            CompStatement comp10 = new CompStatement(open_d, comp9);
            CompStatement comp21 = new CompStatement(open1, comp10);

            MyStack<IStatement> stack4 = new MyStack<IStatement>();
            MyDictionary<String, int> symTable4 = new MyDictionary<String, int>();
            MyList<int> out4 = new MyList<int>();
            MyDictionary<int, MyTuple<String, StreamReader>> file4 = new MyDictionary<int, MyTuple<String, StreamReader>>();

            ProgramState prg4 = new ProgramState(stack4, symTable4, out4, file4, comp21);

            Repository repo4 = new Repository("logfile4.txt");
            Controller ctrl4 = new Controller(repo4);
            ctrl4.AddProgram(prg4);

            // program_5: open(var_f,"test.in"); readFile(var_f+2,var_c); Print(var_c); (if var_c then read(var_f,var_c); Print(var_c) else print(0)); close(var_f);

            VarExpression var6 = new VarExpression("var_f");
            ReadFileStatement read3 = new ReadFileStatement(var6, "var_c");
            VarExpression var7 = new VarExpression("var_c");
            PrintStatement print5 = new PrintStatement(var7);
            CompStatement comp11 = new CompStatement(read3, print5);
            ConstantExpression const6 = new ConstantExpression(0);
            PrintStatement print6 = new PrintStatement(const6);
            IfStatement if2 = new IfStatement(var7, comp11, print6);
            CloseFileStatement close2 = new CloseFileStatement(var6);
            CompStatement comp12 = new CompStatement(if2, close2);
            CompStatement comp13 = new CompStatement(print5, comp12);
            ArithmeticExpression var8 = new ArithmeticExpression(var6, new ConstantExpression(2), "+");
            ReadFileStatement read4 = new ReadFileStatement(var8, "var_c");
            CompStatement comp14 = new CompStatement(read4, comp13);
            OpenFileStatement open2 = new OpenFileStatement("var_f", "test.in");
            CompStatement comp15 = new CompStatement(open2, comp14);

            MyStack<IStatement> stack5 = new MyStack<IStatement>();
            MyDictionary<String, int> symTable5 = new MyDictionary<String, int>();
            MyList<int> out5 = new MyList<int>();
            MyDictionary<int, MyTuple<String, StreamReader>> file5 = new MyDictionary<int, MyTuple<String, StreamReader>>();

            ProgramState prg5 = new ProgramState(stack5, symTable5, out5, file5, comp15);

            Repository repo5 = new Repository("logfile5.txt");
            Controller ctrl5 = new Controller(repo5);
            ctrl5.AddProgram(prg5);

            TextMenu menu = new TextMenu();
            menu.AddCommand(new ExitCommand("exit", "stop program execution;"));
            menu.AddCommand(new RunExample("prg_1", "v = 2; Print(v);", ctrl1));
            menu.AddCommand(new RunExample("prg_2", "a = 2+3*5; b = a+1; Print(b);", ctrl2));
            menu.AddCommand(new RunExample("prg_3", "a = 2 - 2; (IF a THEN v = 2 ELSE v = 3); Print(v);", ctrl3));
            menu.AddCommand(new RunExample("prg_4", "open(var_f, \"test_in\"); open(var_b, \"logfile1.txt\"); read(var_f,var_c); Print(var_c); (if var_c then read(var_f,var_c); Print(var_c) else Print(0)); close(var_f)",ctrl4));
            menu.AddCommand(new RunExample("prg_5", "open(var_f,\"test.in \"); readFile(var_f+2,var_c); Print(var_c); (if var_c then read(var_f,var_c); Print(var_c) else print(0)); close(var_f);", ctrl5));
            menu.Show();
            
        }
    }
}
