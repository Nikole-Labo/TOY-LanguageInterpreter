package Module.ADT;
import Exceptions.ListException;

import java.util.ArrayList;

public interface InterfaceList <T>
{
    T getElement(int position) throws ListException;
    int getPosition(T element) throws ListException;
    boolean isEmpty();
    int size();
    void insert(T element,int position) throws ListException;
    void removeByPosition(int position) throws ListException;
    void removeByElement(T element) throws ListException;
    String toString();
    public void add(T v);
    public InterfaceList<T> deepCopy();
    ArrayList<T> values();
}
