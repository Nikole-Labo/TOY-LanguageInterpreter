package Module.ADT;
import Exceptions.DictionaryException;

import java.util.Map;
import java.util.Set;

public interface InterfaceDictionary <K,V>
{
    void put(K key, V value);
    V lookUp(K key);
    boolean contains(K key);
    void update(K key, V value);
    V getElement(K key);
    void removeElement(K key) throws DictionaryException;
    Set<K> getAllKeys();
    Map <K,V> getContent();
    public Dictionary<K,V> deepCopy();

}
