package Model.Expressions;

import ADTs.IDictionary;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapReadingException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;
import Heap.IHeap;

public class ArithmetricExpression extends IExpression {
    private IExpression exp1;
    private IExpression exp2;
    private String operator;

    public ArithmetricExpression(IExpression left, IExpression right, String op) {
        this.exp1 = left;
        this.exp2 = right;
        this.operator = op;
    }

    @Override
    public int eval(IDictionary<String, Integer> table, IHeap heap)  throws DivisionByZeroException, UndefinedOperationException, UndefinedVariableException, HeapReadingException {
        int result;
        switch (this.operator) {
            case "+" : result = this.exp1.eval(table,heap) + this.exp2.eval(table,heap); break;
            case "-" : result = this.exp1.eval(table,heap) - this.exp2.eval(table,heap); break;
            case "*" : result = this.exp1.eval(table,heap) * this.exp2.eval(table,heap); break;
            case "/" : {
                if(this.exp2.eval(table,heap) == 0)
                    throw new DivisionByZeroException();
                result = this.exp1.eval(table,heap) / this.exp2.eval(table,heap);
            } break;
            default: throw new UndefinedOperationException();
        }
        return result;
    }

    public String toString() {
        return this.exp1.toString() + " " + this.operator + " " + this.exp2.toString();
    }
}
