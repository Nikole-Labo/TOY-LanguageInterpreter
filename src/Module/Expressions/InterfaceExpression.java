package Module.Expressions;
import Exceptions.MyException;
import Module.ADT.InterfaceHeap;
import Module.ADT.InterfaceDictionary;
import Module.ADT.InterfaceSymbolTable;
import Module.Types.InterfaceType;
import Module.Values.InterfaceValue;
public interface InterfaceExpression
{
    InterfaceValue evaluate(InterfaceSymbolTable symbolTable, InterfaceHeap<InterfaceValue> heap) throws MyException;
    InterfaceExpression deepCopy();
    InterfaceType typeCheck(InterfaceDictionary<String,InterfaceType> typeEnv) throws MyException;
}
