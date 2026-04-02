package Module.ADT;

import Exceptions.MyException;
import Module.Statements.InterfaceStatement;

import java.util.ArrayList;
import java.util.List;


public interface InterfaceExecutionStack
{
    InterfaceStatement pop() throws MyException;
    void push(InterfaceStatement element);
    boolean isEmpty();
    List<InterfaceStatement> toList();
    String toString();
    public ArrayList<InterfaceStatement> values();
}
