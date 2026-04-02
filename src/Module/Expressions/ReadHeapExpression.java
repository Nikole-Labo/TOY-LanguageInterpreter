package Module.Expressions;

import Exceptions.HeapException;
import Exceptions.MyException;
import Exceptions.RelationalException;
import Module.ADT.InterfaceDictionary;
import Module.ADT.InterfaceHeap;
import Module.ADT.InterfaceSymbolTable;
import Module.Expressions.InterfaceExpression;
import Module.Types.InterfaceType;
import Module.Types.ReferenceType;
import Module.Values.InterfaceValue;
import Module.Values.ReferenceValue;

public class ReadHeapExpression implements InterfaceExpression
{
    private InterfaceExpression expression;

    public ReadHeapExpression(InterfaceExpression E){
        this.expression =E;
    }

    @Override
    public InterfaceValue evaluate(InterfaceSymbolTable symbolTable, InterfaceHeap<InterfaceValue> heap) throws MyException
    {
        if(expression.evaluate(symbolTable, heap) instanceof ReferenceValue referenceValue)
        {
            if (heap.lookUp(referenceValue.getAddress()) == null)
                throw new HeapException("The memory is not allocated!");
            else
                return heap.lookUp(referenceValue.getAddress());
        }
        else throw new RelationalException("Argument must be of type RefValue!");

    }

    @Override
    public String toString() {
        return "rH(" + expression.toString()+")";
    }

    @Override
    public InterfaceExpression deepCopy()
    {
        return new ReadHeapExpression(expression.deepCopy());
    }

    @Override
    public InterfaceType typeCheck(InterfaceDictionary<String,InterfaceType> typeEnv) throws MyException
    {
        InterfaceType type= expression.typeCheck(typeEnv);
        if (type instanceof ReferenceType)
        {
            ReferenceType refType =(ReferenceType) type;
            return refType.getInner();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }
}
