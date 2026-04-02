package Module;
import Exceptions.MyException;
import Module.ADT.*;
import Module.Statements.CompoundStatement;
import Module.Statements.InterfaceStatement;
import Module.Statements.NopStatement;
import Module.Values.InterfaceValue;
import Module.Values.StringValue;

import java.io.BufferedReader;
import java.util.*;
import java.util.List;

public class ProgramState
{
    private static int nextId = 1;
    private ExecutionStack executionStack;
    private SymbolTable symbolTable;
    private InterfaceList<InterfaceValue> out;
    private InterfaceDictionary<StringValue, BufferedReader> fileTable;
    private InterfaceHeap<InterfaceValue> Heap;
    private int id;
    private final InterfaceStatement originalProgram;

    public ProgramState(ExecutionStack stack, SymbolTable symtbl, InterfaceDictionary<StringValue, BufferedReader> filetbl, InterfaceList<InterfaceValue>
            ot, InterfaceHeap<InterfaceValue> h, InterfaceStatement program)
    {
        executionStack = stack;
        symbolTable = symtbl;
        Heap = h;
        out = ot;
        id = getNextId();
        originalProgram = program.deepCopy();
        fileTable = filetbl;
        stack.push(program);
    }
    public int getId()
    {
        return this.id;
    }

    public synchronized static int getNextId(){
        return nextId++;
    }

    public ProgramState oneStep() throws MyException
    {
        if(executionStack.isEmpty()) throw new MyException("PrgState stack is empty !");
        InterfaceStatement  currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public ExecutionStack getExecutionStack()
    {
        return this.executionStack;
    }

    public InterfaceSymbolTable getSymbolTable()
    {
        return this.symbolTable;
    }

    public InterfaceHeap<InterfaceValue> getHeap()
    {
        return this.Heap;
    }

    public InterfaceStatement getOriginalProgram(){ return this.originalProgram; }

    public InterfaceList<InterfaceValue> getOut()
    {
        return this.out;
    }

    public String toString()
    {
        return "PrgStateId:" + id + "\nExeStack:\n" + distinctStatamentsString()+"\nSymTable: "+ symbolTable.toString()+"\nOut: "+out.toString()+"\nFile Table: "+fileTableToString()
                + "\nHeap: " + Heap.toString();
    }

    public boolean isNotCompleted(){
        return !executionStack.isEmpty();
    }

    public InterfaceDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public List<InterfaceStatement> distinctStatements()
    {
        Tree<InterfaceStatement> tree =  new Tree<InterfaceStatement>();
        List<InterfaceStatement> inOrderList = new LinkedList<InterfaceStatement>();
        if(getExecutionStack().toList().size()>0)
        {
            tree.setRoot(toTree( (InterfaceStatement)getExecutionStack().toList().get(0)));
            tree.inorderTraversal(inOrderList, tree.getRoot());
        }
        return inOrderList;
    }

    public String distinctStatamentsString()
    {
        List<InterfaceStatement> inOrderList = distinctStatements();
        StringBuilder str = new StringBuilder();
        for (InterfaceStatement statement : inOrderList)
        {
            if(!Objects.equals(statement.toString(),";"))
            {
                str.append(statement.toString());
                str.append("\n");
            }
        }
        return str.toString();
    }

    public String fileTableToString()
    {
        StringBuilder str = new StringBuilder();
        for (StringValue file : fileTable.getAllKeys())
        {
            str.append(file.getValue());
            str.append("\n");
        }
        return str.toString();
    }

    private Node<InterfaceStatement> toTree(InterfaceStatement statement)
    {
        Node node;
        if (statement instanceof CompoundStatement)
        {
            CompoundStatement compoundStatement = (CompoundStatement) statement;
            node = new Node<>(new NopStatement());
            node.setLeft(new Node<>(compoundStatement.getFirstStatement()));
            node.setRight(toTree( compoundStatement.getSecondStatement()));
        }
        else
        {
            node = new Node<>(statement);
        }
        return node;

    }

    public void setSymbolTable(SymbolTable symTable)
    {
        symbolTable = symTable;
    }
}

