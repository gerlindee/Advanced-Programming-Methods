package ADTs;

import Exceptions.EmptyContainerException;

import java.util.LinkedList;

public class MyList<T> implements IList<T> {
    private LinkedList<T> list;

    public MyList() {
        this.list = new LinkedList<>();
    }

    public void addFirst(T elem) {
        this.list.addFirst(elem);
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public T remove() throws EmptyContainerException {
        if(this.isEmpty())
            throw new EmptyContainerException("THE LIST IS EMPTY!");
        return this.list.remove();
    }

    public String toString() {
        return this.list.toString();
    }
}
