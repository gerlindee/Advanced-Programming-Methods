package Repository;
import Model.Animal;
import Exceptions.Exception1;

public interface Repo {
    void add(Animal thing) throws Exception1;
    void remove(Animal thing) throws Exception1;
    Animal[] getAll();
    int getPos();
}
