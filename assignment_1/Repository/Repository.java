package Repository;
import Exceptions.Exception1;
import Model.Animal;

public class Repository implements Repo {
    private Animal[] elements;
    private int size; // the index of the last element of the elements list

    public Repository(int size) {
        this.elements = new Animal[size];
        this.size = 0;
    }

    public void add(Animal thing) throws Exception1 {
        if(this.size >= this.elements.length) {
            throw new Exception1("NO MORE SPACE!");
        }
        elements[this.size++] = thing;
    }

    public void remove(Animal thing) throws Exception1{
        if(this.size <= 0) {
            throw new Exception1("NOTHING TO REMOVE!");
        }
        int idx = -1, i;
        for(i = 0; i < this.size; i++) {
            if(this.elements[i].equals(thing)) {
                idx = i;
            }
        }
        if(idx == -1) {
            throw new Exception1("THE ELEMENT YOU ARE TRYING TO REMOVE DOES NOT EXIST!");
        }

        for(i = idx; i < this.size - 1 ; i++) {
            this.elements[i] = this.elements[i+1];
        }
        this.size--;
    }

    public Animal[] getAll() {
        return this.elements;
    }

    public int getPos() {
        return this.size;
    }
}
