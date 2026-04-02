package Module.ADT;

import Exceptions.DictionaryException;
import Module.Values.InterfaceValue;

import java.util.Map;
import java.util.Set;

public interface InterfaceSymbolTable {
    void put(String key, InterfaceValue value);
    InterfaceValue lookUp(String key);
    boolean contains(String key);
    void update(String key, InterfaceValue value);
    InterfaceValue getElement(String key);
    void removeElement(String key) throws DictionaryException;
    Set<String> getAllKeys();
    Map<String,InterfaceValue> getContent();
    SymbolTable deepCopy();
}
