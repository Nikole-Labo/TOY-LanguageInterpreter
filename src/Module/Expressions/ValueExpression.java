package Module.Expressions;

import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.ADT.InterfaceHeap;
import Module.ADT.InterfaceSymbolTable;
import Module.Types.InterfaceType;
import Module.Values.InterfaceValue;

public class ValueExpression implements InterfaceExpression
{
    private InterfaceValue value;

    public ValueExpression(InterfaceValue e) {
        this.value = e;
    }

    @Override
    public InterfaceValue evaluate(InterfaceSymbolTable symbolTable, InterfaceHeap<InterfaceValue> heap)
    {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public InterfaceExpression deepCopy()
    {
        return new ValueExpression(value.deepCopy());
    }

    @Override
    public InterfaceType typeCheck(InterfaceDictionary<String,InterfaceType> typeEnv) throws MyException
    {
        return value.getType();
    }
}
