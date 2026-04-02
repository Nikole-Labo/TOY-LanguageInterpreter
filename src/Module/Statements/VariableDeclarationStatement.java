package Module.Statements;

import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.ADT.InterfaceSymbolTable;
import Module.Types.InterfaceType;
import Module.ProgramState;

public class VariableDeclarationStatement implements InterfaceStatement
{
    private String name;
    private InterfaceType type;

    public VariableDeclarationStatement(String n, InterfaceType t)
    {
        name = n;
        type = t;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        InterfaceSymbolTable symTable = state.getSymbolTable();
        if (symTable.contains(name))
        {
            throw new MyException("Variable " + name + " is already declared.");
        }
        symTable.put(name, type.defaultValue());
        return null;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new VariableDeclarationStatement(this.name, this.type);
    }

    @Override
    public InterfaceDictionary<String,InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws MyException
    {
        typeEnv.put(name,type);
        return typeEnv;
    }

}
