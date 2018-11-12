package ADTs;

public class Tuple<T1,T2> implements ITuple<T1,T2> {
    private T1 first;
    private T2 second;

    public Tuple(T1 f, T2 s) {
        this.first = f;
        this.second = s;
    }

    public T1 getFirst() {
        return this.first;
    }

    public T2 getSecond() {
        return this.second;
    }

    public void setFirst(T1 ent) {
        this.first = ent;
    }

    public void setSecond(T2 ent) {
        this.second = ent;
    }

    public String toString() {
        return "(" + this.first.toString() + "," + this.second.toString() + ")";
    }
}
