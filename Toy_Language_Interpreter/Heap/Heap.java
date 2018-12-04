package Heap;

import Exceptions.HeapReadingException;

import java.util.Collection;
import java.util.HashMap;

public class Heap implements IHeap {
    private HashMap<Integer, Integer> mappings;
    private int location;

    public Heap() {
        this.mappings = new HashMap<>();
        this.location = 1;
    }

    public int New(int value) {
        int address = this.location;
        this.mappings.put(address, value);
        this.location++;
        return address;
    }

    public int Read(int address) throws HeapReadingException {
        for(int idx : this.getAddresses()) {
            if(idx == address)
                return this.mappings.get(address);
        }
        throw new HeapReadingException();

    }

    public void Update(int address, int value) throws HeapReadingException {
        boolean found = false;
        for(int idx : this.getAddresses()) {
            if(idx == address)
                found = true;
        }
        if(!found)
            throw new HeapReadingException();
        this.mappings.put(address, value);
    }

    private Collection<Integer> getAddresses() {
        return this.mappings.keySet();
    }

    public HashMap<Integer, Integer> getDictionary() {
        return this.mappings;
    }

    public void setDictionary(HashMap<Integer,Integer> content) {
        this.mappings = content;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        int size = 100;
        result.setLength(size);
        for(int key : this.getAddresses()) {
            if(result.length() > size) {
                size = 2 * size;
                result.setLength(size);
            }
            result.append(String.valueOf(key));
            result.append(" -> ");
            result.append(String.valueOf(this.mappings.get(key)));
            result.append("\n");
        }
        return result.toString();
    }

}
