package ADTs;

import Exceptions.EmptyContainerException;
import Model.ProgramState;

public interface IList<T> {
    void addFirst(T elem);
    boolean isEmpty();
    T remove() throws EmptyContainerException;
    String toString();
    T get(int index);
    T getFirst();
}
