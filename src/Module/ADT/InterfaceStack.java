package Module.ADT;

import Exceptions.StackException;

import java.util.ArrayList;
import java.util.List;

public interface InterfaceStack<T>
{
    void push(T elem);
    T pop() throws StackException;
    boolean isEmpty();
    List<T> toListS();
    InterfaceStack<T> deepCopy();
    ArrayList<T> values();
}
