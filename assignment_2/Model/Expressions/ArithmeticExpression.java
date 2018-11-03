package Model.Expressions;

import ADTs.IDictionary;
import Exceptions.DivisionByZeroException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;

public class ArithmetricExpression extends IExpression {
    private IExpression exp1;
    private IExpression exp2;
    private String operator;

    public ArithmetricExpression(IExpression left, IExpression right, String op) {
        this.exp1 = left;
        this.exp2 = right;
        this.operator = op;
    }

    public int eval(IDictionary<String, Integer> table)  throws DivisionByZeroException, UndefinedOperationException{
        int result = 0;
        try {
            switch (this.operator) {
                case "+" : result = this.exp1.eval(table) + this.exp2.eval(table); break;
                case "-" : result = this.exp1.eval(table) - this.exp2.eval(table); break;
                case "*" : result = this.exp1.eval(table) * this.exp2.eval(table); break;
                case "/" : {
                    if(this.exp2.eval(table) == 0)
                        throw new DivisionByZeroException();
                    result = this.exp1.eval(table) / this.exp2.eval(table);
                } break;
                default: throw new UndefinedOperationException();

            }
        } catch (UndefinedVariableException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    public String toString() {
        return this.exp1.toString() + " " + this.operator + " " + this.exp2.toString();
    }
}
