package Model.Expressions;

import ADTs.IDictionary;
import Exceptions.DivisionByZeroException;
import Exceptions.HeapReadingException;
import Exceptions.UndefinedOperationException;
import Exceptions.UndefinedVariableException;
import Heap.IHeap;

public class BooleanExpression extends IExpression {
    private IExpression left;
    private IExpression right;
    private String operator;

    public BooleanExpression(IExpression one, String op, IExpression two) {
        this.left = one;
        this.right = two;
        this.operator = op;
    }

    public int eval(IDictionary<String, Integer> table, IHeap heap) throws HeapReadingException, UndefinedVariableException, UndefinedOperationException, DivisionByZeroException {
        int result;
        switch (this.operator) {
            case "<" :
            {
                if(this.left.eval(table,heap) < this.right.eval(table,heap))
                    result = 1;
                else
                    result = 0;
            } break;
            case "<=" :
            {
                if(this.left.eval(table,heap) <= this.right.eval(table,heap))
                    result = 1;
                else
                    result = 0;
            } break;
            case "==" :
            {
                if(this.left.eval(table,heap) == this.right.eval(table,heap))
                    result = 1;
                else
                    result = 0;
            } break;
            case "!=" :
            {
                if(this.left.eval(table,heap) != this.right.eval(table,heap))
                    result = 1;
                else
                    result = 0;
            } break;
            case ">" :
            {
                if(this.left.eval(table,heap) > this.right.eval(table,heap))
                    result = 1;
                else
                    result = 0;
            } break;
            case ">=" :
            {
                if(this.left.eval(table,heap) >= this.right.eval(table,heap))
                    result = 1;
                else
                    result = 0;
            } break;
            default: throw new UndefinedOperationException();
        }
        return result;
    }

    public String toString() {
        return "(" + this.left.toString() + this.operator + this.right.toString() + ")";
    }
}
