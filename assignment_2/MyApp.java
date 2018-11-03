package MyApp;

import ADTs.MyDictionary;
import ADTs.MyList;
import ADTs.MyStack;
import Controller.Controller;
import Model.Expressions.ArithmetricExpression;
import Model.Expressions.ConstantExpression;
import Model.Expressions.VarExpression;
import Model.ProgramState;
import Model.Statements.*;
import Repository.Repository;
import View.View;

public class MyApp {
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

        ProgramState prg1 = new ProgramState(stack1,symTable1,out1,statement1);

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

        ProgramState prg2 = new ProgramState(stack2, symTable2, out2, comp3);

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

        ProgramState prg3 = new ProgramState(stack3, symTable3, out3, comp5);

        Repository repo = new Repository();
        Controller ctrl = new Controller(repo, "on");
        ctrl.setFlag("on");
        ctrl.addProgram(prg1);
        ctrl.addProgram(prg2);
        ctrl.addProgram(prg3);
        View view = new View(ctrl);
        view.runCrtPrg();
        view.runAllSteps();

    }
}
