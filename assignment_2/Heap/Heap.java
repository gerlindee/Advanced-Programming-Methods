package Heap;

import ADTs.IDictionary;
import ADTs.MyDictionary;
import Exceptions.HeapReadingException;

import java.util.Collection;
import java.util.HashMap;

public class Heap implements IHeap {
    private IDictionary<Integer, Integer> mappings;
    private int location;

    public Heap() {
        this.mappings = new MyDictionary<>();
        this.location = 1;
    }

    public int New(int value) {
        int address = this.location;
        this.mappings.put(address, value);
        this.location++;
        return address;
    }

    public int Read(int address) throws HeapReadingException {
        boolean found = false;
        for(int idx : this.getAddresses()) {
            if(idx == address)
                found = true;
        }
        if(!found)
            throw new HeapReadingException();
        return this.mappings.get(address);

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
        return this.mappings.keys();
    }

    public String toString() {
        return this.mappings.toString();
    }

    public HashMap<Integer, Integer> getDictionary() {
        return this.mappings.getDictionary();
    }

    public void setDictionary(HashMap<Integer,Integer> content) {
        this.mappings.setContent(content);
    }

}
