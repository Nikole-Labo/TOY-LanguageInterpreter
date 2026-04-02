package Module.Expressions;

import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.ADT.InterfaceHeap;
import Module.ADT.InterfaceSymbolTable;
import Module.Types.InterfaceType;
import Module.Values.InterfaceValue;

public class VariableExpression implements InterfaceExpression
{
    private String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public InterfaceValue evaluate(InterfaceSymbolTable symbolTable, InterfaceHeap<InterfaceValue> heap) throws MyException
    {return symbolTable.lookUp(id);}


    @Override
    public String toString() {
        return id;
    }

    @Override
    public InterfaceExpression deepCopy()
    {
        return new VariableExpression(id);
    }

    @Override
    public InterfaceType typeCheck(InterfaceDictionary<String,InterfaceType> typeEnv) throws MyException
    {
        InterfaceType type = typeEnv.lookUp(id);
        if(type == null)
            throw new MyException("Type check exception=var not defined");
        else
            return type;
    }

}
