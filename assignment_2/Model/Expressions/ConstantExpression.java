package Model.Expressions;

import ADTs.IDictionary;

public class ConstantExpression extends IExpression {
    private int number;

    public ConstantExpression(int num) {
        this.number = num;
    }

    public int eval(IDictionary<String, Integer> table) {
        return this.number;
    }

    public String toString() {
        return Integer.toString(this.number); // sau return String.valueOf(this.number);
    }
}
