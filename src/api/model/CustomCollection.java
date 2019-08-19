package api.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * CustomCollection
 */
public abstract class CustomCollection<T> implements Map<String, T> {

    private Map<String, T> map = new HashMap<String,  T>();

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @Override
    public T get(Object key) {
        return this.map.get(key);
    }

    @Override
    public T put(String key, T value) {
        return this.map.put(key, value);
    }

    @Override
    public T remove(Object key) {
        return this.map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends T> m) {
        this.map.putAll(m);
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Override
    public Collection<T> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<String, T>> entrySet() {
        return this.map.entrySet();
    }
}