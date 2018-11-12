package ADTs;

public interface ITuple<T1,T2> {
    T1 getFirst();
    T2 getSecond();
    void setFirst(T1 ent);
    void setSecond(T2 ent);
    String toString();
}
