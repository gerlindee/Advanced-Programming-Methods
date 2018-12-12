package ADTs;

import Exceptions.EmptyContainerException;

public interface IList<T> {
    void addFirst(T elem);
    boolean isEmpty();
    T remove() throws EmptyContainerException;
    String toString();
}