package Module.ADT;

import Exceptions.DictionaryException;
import Module.Values.InterfaceValue;

import java.util.Map;
import java.util.Set;

public class SymbolTable implements InterfaceSymbolTable
{
    private InterfaceDictionary<String, InterfaceValue> dict;

    public SymbolTable() {
        this.dict = new Dictionary<>();
    }

    @Override
    public void put(String key, InterfaceValue value) {
        dict.put(key, value);
    }

    @Override
    public InterfaceValue lookUp(String key) {
        return dict.lookUp(key);
    }

    @Override
    public boolean contains(String key) {
        return dict.contains(key);
    }

    @Override
    public void update(String key, InterfaceValue value)
    {
        dict.update(key, value);
    }

    @Override
    public InterfaceValue getElement(String key) {
        return dict.getElement(key);
    }

    @Override
    public void removeElement(String key) throws DictionaryException
    {
        dict.removeElement(key);
    }

    @Override
    public Map<String,InterfaceValue> getContent()
    {
        return this.dict.getContent();
    }

    @Override
    public Set<String> getAllKeys() {
        return dict.getAllKeys();
    }

    @Override
    public String toString(){
        return dict.toString();
    }

    @Override
    public SymbolTable deepCopy()
    {
        SymbolTable newSymbolTable = new SymbolTable();
        for (String key : dict.getAllKeys())
        {
            newSymbolTable.put(key, dict.getElement(key));
        }
        return newSymbolTable;
    }
}
