package Module.Statements;

import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.ProgramState;
import Module.Types.InterfaceType;

public class NopStatement implements InterfaceStatement
{
    @Override
    public String toString() {
        return ";";
    }

    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new NopStatement();
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws MyException
    {
        return typeEnv;
    }
}
