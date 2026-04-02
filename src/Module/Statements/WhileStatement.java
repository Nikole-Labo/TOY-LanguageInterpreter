package Module.Statements;

import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.Expressions.InterfaceExpression;
import Module.Types.BoolType;
import Module.Types.InterfaceType;
import Module.Values.BoolValue;
import Module.ProgramState;

public class WhileStatement implements InterfaceStatement
{
    private InterfaceExpression condition;
    private InterfaceStatement execute;

    public WhileStatement(InterfaceExpression cond, InterfaceStatement exec)
    {
        this.condition = cond;
        this.execute = exec;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        if(condition.evaluate(state.getSymbolTable(), state.getHeap() )instanceof BoolValue boolval)
        {
            if( boolval.getValue())
            {
                state.getExecutionStack().push(this);
                state.getExecutionStack().push(execute);
            }
            return null;
        }
        else throw new MyException("cond exp is not boolean");
    }

    public String toString()
    {
        return "while("+this.condition.toString()+"){"+this.execute.toString()+"}";
    }

    @Override
    public InterfaceStatement deepCopy()
    {
        return new WhileStatement(this.condition.deepCopy(), this.execute.deepCopy());
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws MyException
    {
        InterfaceType expressionType = this.condition.typeCheck(typeEnv);
        if(expressionType.equals(new BoolType()))
        {
            this.execute.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else throw new MyException("The cond is not A bool type");
    }
}
