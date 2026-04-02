package Module.Statements;

import Exceptions.HeapDeclarationException;
import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.Expressions.InterfaceExpression;
import Module.ProgramState;
import Module.Types.InterfaceType;
import Module.Types.ReferenceType;
import Module.Values.InterfaceValue;
import Module.Values.ReferenceValue;

public class AllocateHeapStatement implements InterfaceStatement
{
    private String variableName;
    private InterfaceExpression expression;

    public AllocateHeapStatement(String varName, InterfaceExpression expr)
    {
        this.variableName = varName;
        this.expression = expr;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException
    {
        InterfaceValue variableValue = programState.getSymbolTable().lookUp(this.variableName);
        if(variableValue == null) throw new HeapDeclarationException("There is no reference with this name!");
        if(variableValue instanceof ReferenceValue referenceValue)
        {
            InterfaceValue value = expression.evaluate(programState.getSymbolTable(), programState.getHeap());
            if(value.getType().equals(referenceValue.getLocationType()))
            {
                int freeAddress = programState.getHeap().getFreeAddressLocation();
                programState.getHeap().generateNewAddress();
                programState.getHeap().addElement(freeAddress, value);
                programState.getSymbolTable().update(variableName, new ReferenceValue(freeAddress, value.getType()));
                return null;
            }
            else throw new HeapDeclarationException("The refType must have the same location Type!");
        }
        else throw new HeapDeclarationException("The variable must be a ref type");
    }

    @Override
    public String toString()
    {
        return "new("+this.variableName +", "+this.expression.toString()+")";
    }

    @Override
    public InterfaceStatement deepCopy()
    {
        return new AllocateHeapStatement(this.variableName, expression.deepCopy());
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws MyException
    {
        InterfaceType variableTye = typeEnv.lookUp(variableName);
        if(variableTye == null)
            throw new MyException("Type check excep");
        InterfaceType expressionType = expression.typeCheck(typeEnv);
        if (variableTye.equals(new ReferenceType(expressionType)))
            return typeEnv;
        else throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }
}
