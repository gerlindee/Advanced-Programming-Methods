package ADTs;

import Exceptions.EmptyContainerException;

import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    private Stack<T> stack;

    public MyStack(){
        this.stack = new Stack<>();
    }

    public void push(T elem) {
        stack.push(elem);
    }
    
    public T pop() throws EmptyContainerException {
        if(this.stack.isEmpty())
            throw new EmptyContainerException();
        return stack.pop();
    }

    public boolean isEmpty() {
        return stack.empty();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        int size = 100;
        result.setLength(size);
        if(this.stack.isEmpty()){
            result.append("[ ]");
            result.append("\n");
            return result.toString();
        }
        int i = this.stack.size() - 1;
        result.append(this.stack.get(i).toString());
        result.append("\n");
        i--;
        while(i >= 0) {
            if (result.length() > size) {
                size = 2 * size;
                result.setLength(size);
            }
            result.append(this.stack.get(i).toString());
            result.append("\n");
            i--;
        }
        return result.toString();

    }
}
