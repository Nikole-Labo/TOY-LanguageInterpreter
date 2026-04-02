package Module.Statements;

import Exceptions.HeapException;
import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.Expressions.InterfaceExpression;
import Module.ProgramState;
import Module.Types.InterfaceType;
import Module.Types.ReferenceType;
import Module.Values.InterfaceValue;
import Module.Values.ReferenceValue;

public class HeapWritingStatement implements InterfaceStatement
{
    private String variableName;
    private InterfaceExpression expression;

    public HeapWritingStatement(String name, InterfaceExpression expr)
    {
        this.variableName = name;
        this.expression = expr;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        InterfaceValue value = state.getSymbolTable().lookUp(variableName);
        if(value == null) throw new HeapException("The variable is not declared!");
        if( value instanceof ReferenceValue referenceValue)
        {
            if(state.getHeap().lookUp(referenceValue.getAddress()) == null) throw new HeapException("The memory is not allocated!");
            InterfaceValue newValue = expression.evaluate(state.getSymbolTable(), state.getHeap());
            if(newValue.getType().equals(referenceValue.getLocationType()))
            {
                state.getHeap().update(referenceValue.getAddress(), newValue);
                return null;
            }
            else throw new HeapException("The type of the variable is not the same as the type of the location!");
        }
        else throw new HeapException("The variable is not a RefValue!");
    }

    @Override
    public String toString()
    {
        return "wH(" + this.variableName + ", " + this.expression.toString() + ")";
    }

    @Override
    public InterfaceStatement deepCopy()
    {
        return new HeapWritingStatement(variableName, expression.deepCopy());
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws MyException
    {
        if(typeEnv.lookUp(variableName).equals(new ReferenceType(expression.typeCheck(typeEnv))))
        {
            return typeEnv;
        }
        else throw new MyException("Type Check exception!");
    }
}
