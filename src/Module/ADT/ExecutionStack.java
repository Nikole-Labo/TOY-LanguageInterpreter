package Module.ADT;

import Exceptions.MyException;
import Module.Statements.InterfaceStatement;

import java.util.ArrayList;
import java.util.List;

public class ExecutionStack implements InterfaceExecutionStack
{
    InterfaceStack<InterfaceStatement> stack;

    public ExecutionStack(){
        this.stack = new MyStack<>();
    }

    @Override
    public void push(InterfaceStatement element) {
        stack.push(element);
    }

    @Override
    public InterfaceStatement pop() throws MyException
    {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public List<InterfaceStatement> toList() {
        return stack.toListS();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    @Override
    public ArrayList<InterfaceStatement> values() {
        return this.stack.values();
    }
}
