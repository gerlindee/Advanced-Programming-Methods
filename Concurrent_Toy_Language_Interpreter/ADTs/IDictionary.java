package ADTs;

import java.util.Collection;
import java.util.HashMap;

public interface IDictionary<K,V> {
    void put(K key, V value);
    V get(K key);
    void remove(K key);
    Collection<V> values();
    Collection<K> keys();
    String toString();
    HashMap<K,V> getDictionary();
    boolean containsKey(K key);
}
