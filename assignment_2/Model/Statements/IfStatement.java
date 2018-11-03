package Model.Statements;

import ADTs.IDictionary;
import ADTs.IStack;
import Exceptions.DivisionByZeroException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;
import Model.Expressions.IExpression;
import Model.ProgramState;

public class IfStatement implements IStatement {
    private IExpression exe;
    private IStatement stmt1;
    private IStatement stmt2;

    public IfStatement(IExpression ex, IStatement thenS, IStatement elseS) {
        this.exe = ex;
        this.stmt1 = thenS;
        this.stmt2 = elseS;
    }

    public String toString() {
        return "IF(" + exe.toString() + ") THEN (" + this.stmt1.toString() + ") ELSE (" + this.stmt2.toString() + ")";
    }

    public ProgramState execute(ProgramState state) {
        IStack<IStatement> exeStack = state.getExeStack();
        IDictionary<String, Integer> symTable = state.getSymTable();
        try {
            if(this.exe.eval(symTable) == 0) {
                exeStack.push(this.stmt2);
            }
            else {
                exeStack.push(this.stmt1);
            }
        } catch(UndefinedVariableException ex) {
            System.out.println(ex.getMessage());
        } catch (DivisionByZeroException ex1) {
            System.out.println(ex1.getMessage());
        } catch (UndefinedOperationException ex2) {
            System.out.println(ex2.getMessage());
        }
        return state;
    }
}
