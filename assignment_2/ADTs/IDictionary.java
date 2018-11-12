package ADTs;

import java.util.Collection;

public interface IDictionary<K,V> {
    void put(K key, V value);
    V get(K key);
    V remove(K key);
    Collection<V> values();
    Collection<K> keys();
    String toString();
}
