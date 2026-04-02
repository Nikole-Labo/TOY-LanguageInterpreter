package Module.Statements;

import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.ProgramState;
import Module.Types.InterfaceType;

public interface InterfaceStatement
{
    ProgramState execute(ProgramState state) throws MyException;
    InterfaceStatement deepCopy();
    InterfaceDictionary<String,InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws
            MyException;
}
