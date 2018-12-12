package ADTs;

public class MyTuple<T1,T2> implements ITuple<T1,T2> {
    private T1 first;
    private T2 second;

    public MyTuple(T1 f, T2 s) {
        this.first = f;
        this.second = s;
    }

    public T1 getFirst() {
        return this.first;
    }

    public T2 getSecond() {
        return this.second;
    }

    public String toString() {
        return "(" + this.first.toString() + "," + this.second.toString() + ")";
    }
}