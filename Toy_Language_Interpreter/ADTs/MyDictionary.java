package ADTs;

import java.util.Collection;
import java.util.HashMap;

public class MyDictionary<K,V> implements IDictionary<K,V> {
    private HashMap<K,V> dictionary;

    public MyDictionary() {
        this.dictionary = new HashMap<>();
    }

    public void put(K key, V value) {
        this.dictionary.put(key, value);
    }

    public V get(K key) {
        return this.dictionary.get(key);
    }

    public void remove(K key) {
        this.dictionary.remove(key);
    }

    public Collection<V> values() {
        return this.dictionary.values();
    }

    public Collection<K> keys() {
        return this.dictionary.keySet();
    }

    public HashMap<K,V> getDictionary() {
        return this.dictionary;
    }

    public boolean containsKey(K key) {
        return this.dictionary.containsKey(key);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        int size = 100;
        result.setLength(size);
        for(K key : this.dictionary.keySet()) {
            if(result.length() > size) {
                size = size * 2;
                result.setLength(size);
            }
            result.append(key.toString());
            result.append(" -> ");
            result.append(this.dictionary.get(key).toString());
            result.append("\n");
        }
        return result.toString();
    }

}
