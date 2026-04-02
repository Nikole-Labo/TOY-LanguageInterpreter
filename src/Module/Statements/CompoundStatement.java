package Module.Statements;

import Exceptions.MyException;
import Module.ADT.ExecutionStack;
import Module.ADT.InterfaceDictionary;
import Module.ProgramState;
import Module.Types.InterfaceType;

public class CompoundStatement implements InterfaceStatement
{
    private InterfaceStatement firstStatement;
    private InterfaceStatement secondStatement;

    public CompoundStatement(InterfaceStatement first, InterfaceStatement second)
    {
        this.firstStatement = first;
        this.secondStatement = second;
    }

    public InterfaceStatement getFirstStatement()
    {
        return this.firstStatement;
    }

    public InterfaceStatement getSecondStatement()
    {
        return this.secondStatement;
    }

    @Override
    public String toString() {
        return "(" + firstStatement.toString() + ";" + secondStatement.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        ExecutionStack stack = state.getExecutionStack();
        stack.push(secondStatement);
        stack.push(firstStatement);
        return null;
    }

    @Override
    public InterfaceStatement deepCopy()
    {
        return new CompoundStatement(this.firstStatement.deepCopy(), this.secondStatement.deepCopy());
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String,InterfaceType> typeEnv) throws
            MyException
    {
        return secondStatement.typeCheck(firstStatement.typeCheck(typeEnv));
    }

}
