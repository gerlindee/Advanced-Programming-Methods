package Model.Expressions;

import ADTs.IDictionary;
import Exceptions.HeapReadingException;
import Heap.IHeap;

public class HeapReadingExpression extends IExpression {
    private String var_name;

    public HeapReadingExpression(String var) {
        this.var_name = var;
    }

    @Override
    public int eval(IDictionary<String, Integer> table, IHeap heap) throws HeapReadingException, NullPointerException {
        int address = table.get(var_name);
        return heap.Read(address);
    }

    public String toString() {
        return "readHeap(" + this.var_name + ")";
    }
}