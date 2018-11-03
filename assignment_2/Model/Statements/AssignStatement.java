package Model.Statements;

import ADTs.IDictionary;
import Exceptions.DivisionByZeroException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;
import Model.Expressions.IExpression;
import Model.ProgramState;

public class AssignStatement implements IStatement {
    private String variable;
    private IExpression exp;

    public AssignStatement(String var, IExpression ex) {
        this.variable = var;
        this.exp = ex;
    }

    public String toString() {
        return this.variable + " = " + this.exp.toString();
    }

    public ProgramState execute(ProgramState state) {
        IDictionary<String, Integer> symTable = state.getSymTable();
        try {
            int result = this.exp.eval(symTable);
            symTable.put(variable, result);
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
