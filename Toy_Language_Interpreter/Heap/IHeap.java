package Heap;

import Exceptions.HeapReadingException;

import java.util.HashMap;

public interface IHeap {
    int New(int value);
    int Read(int address) throws HeapReadingException;
    void Update(int address, int value) throws HeapReadingException;
    String toString();
    HashMap<Integer, Integer> getDictionary();
    void setDictionary(HashMap<Integer,Integer> content);
}
