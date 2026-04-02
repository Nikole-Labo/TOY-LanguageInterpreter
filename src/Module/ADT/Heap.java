package Module.ADT;

import java.util.HashMap;
import java.util.Map;

public class Heap<V> implements InterfaceHeap<V>
{
    private int freeAddrLoc;
    private Map<Integer, V> heap;

    public Heap()
    {
        this.heap=new HashMap<>();
        this.freeAddrLoc=1;
    }

    @Override
    public void update(int addr, V value)
    {
        this.heap.put(addr, value);
    }

    @Override
    public void setHeap(Map<Integer, V> garbageCollector)
    {
        this.heap=garbageCollector;
    }

    public Map<Integer,V> getHeap()
    {
        return this.heap;
    }

    public int getFreeAddressLocation()
    {
        return this.freeAddrLoc;
    }

    public void generateNewAddress(){
        this.freeAddrLoc+=1;
    }

    @Override
    public V lookUp(int addr)
    {
        return this.heap.get(addr);
    }

    public String toString(){
        return this.heap.toString();
    }

    @Override
    public void addElement(int adr,V value){
        this.heap.put(adr,value);
    }

}
