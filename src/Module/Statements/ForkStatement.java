package Module.Statements;

import Exceptions.MyException;
import Module.ADT.ExecutionStack;
import Module.ADT.InterfaceDictionary;
import Module.ProgramState;
import Module.Types.InterfaceType;

public class ForkStatement implements InterfaceStatement
{
    InterfaceStatement statement;

    public ForkStatement(InterfaceStatement s){
        statement =s;
    }

    @Override
    public ProgramState execute(ProgramState state)throws MyException
    {
        ExecutionStack newStack = new ExecutionStack();
        return new ProgramState(newStack, state.getSymbolTable().deepCopy(), state.getFileTable(), state.getOut(), state.getHeap(), statement);
    }

    @Override
    public String toString(){
        return "fork(" + statement.toString() + ")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new ForkStatement(statement.deepCopy());
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws MyException
    {
        statement.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }


}
