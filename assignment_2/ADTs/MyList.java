package ADTs;

import Exceptions.EmptyContainerException;

import java.util.LinkedList;

public class MyList<T> implements IList<T> {
    private LinkedList<T> list;

    public MyList() {
        this.list = new LinkedList<>();
    }

    @Override
    public void addFirst(T elem) {
        this.list.addFirst(elem);
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public T remove() throws EmptyContainerException {
        if(this.isEmpty())
            throw new EmptyContainerException("THE LIST IS EMPTY!");
        return this.list.remove();
    }

    @Override
    public T get(int index) {
        return this.list.get(index);
    }

    @Override
    public T getFirst() {
        return this.list.getFirst();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        int size = 100;
        result.setLength(size);
        for(T elem : this.list) {
            if(result.length() > size) {
                size = 2 * size;
                result.setLength(size);
            }
            result.append(elem.toString());
            result.append("\n");
        }
        return result.toString();
    }
}
