package Heap;

import Exceptions.HeapReadingException;

public interface IHeap {
    int New(int value);
    int Read(int address) throws HeapReadingException;
    void Update(int address, int value) throws HeapReadingException;
    String toString();
}
