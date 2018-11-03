package ADTs;

import Exceptions.EmptyContainerException;

public interface IStack<T> {
    void push(T elem);
    T pop() throws EmptyContainerException;
    boolean isEmpty();
    String toString();
}
