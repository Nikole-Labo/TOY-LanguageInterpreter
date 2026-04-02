package Module.ADT;

import java.util.Map;

public interface InterfaceHeap<V>
{
    void setHeap(Map<Integer ,V> garbageCollector);
    Map<Integer,V> getHeap();
    int getFreeAddressLocation();
    void generateNewAddress();
    V lookUp(int a);
    void update(int addr, V value);
    void addElement(int addr,V value);
}
