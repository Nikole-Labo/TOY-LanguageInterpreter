package Module.ADT;

import Exceptions.DictionaryException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dictionary<K,V> implements InterfaceDictionary<K,V>
{
    protected Map<K,V> table;

    public Dictionary(){
        table = new HashMap<>();
    }

    @Override
    public void put(K key, V value) {
        table.put(key, value);
    }

    @Override
    public V lookUp(K key) {
        return table.get(key);
    }

    @Override
    public boolean contains(K key) {
        return table.containsKey(key);
    }

    @Override
    public String toString(){
        return table.toString();
    }

    @Override
    public void update(K key, V value) {
        table.put(key, value);
    }
    @Override
    public V getElement(K key){
        return table.get(key);
    }

    @Override
    public void removeElement(K key) throws DictionaryException {
        if(!this.contains(key))
            throw new DictionaryException("Element does not exist");
        this.table.remove(key);

    }
    @Override
    public Set<K> getAllKeys() {
        return table.keySet();
    }
    @Override
    public Dictionary<K, V> deepCopy() {
        Dictionary<K, V> newDictionary = new Dictionary<>();
        for (Map.Entry<K, V> entry : this.table.entrySet()) {
            newDictionary.put(entry.getKey(), entry.getValue());
        }
        return newDictionary;
    }

    @Override
    public Map<K,V> getContent()
    {
        return this.table;
    }

}
