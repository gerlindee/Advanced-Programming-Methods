package Model.Statements;

import ADTs.IList;
import Exceptions.DivisionByZeroException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;
import Model.Expressions.*;
import Model.ProgramState;

public class PrintStatement implements IStatement {
    private IExpression exp;

    public PrintStatement(IExpression ex) {
        this.exp = ex;
    }

    public String toString() {
        return "Print(" + exp.toString() + ")";
    }

    public ProgramState execute(ProgramState state) {
        IList<Integer> out = state.getOut();
        try {
            Integer result = this.exp.eval(state.getSymTable());
            out.addFirst(result);
        } catch (UndefinedVariableException ex) {
            System.out.println(ex.getMessage());
        } catch (DivisionByZeroException ex1) {
            System.out.println(ex1.getMessage());
        } catch (UndefinedOperationException ex2) {
            System.out.println(ex2.getMessage());
        }
        return state;
    }
}
