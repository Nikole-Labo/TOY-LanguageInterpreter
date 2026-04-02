package Module.Statements;

import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.ADT.InterfaceList;
import Module.ProgramState;
import Module.Expressions.InterfaceExpression;
import Module.Types.InterfaceType;
import Module.Values.InterfaceValue;

public class PrintStatement implements InterfaceStatement
{
    private InterfaceExpression expression;

    public PrintStatement(InterfaceExpression expr) {
        this.expression = expr;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        InterfaceValue value = expression.evaluate(state.getSymbolTable(), state.getHeap());
        InterfaceList<InterfaceValue> out = state.getOut();
        out.add(value);
        return null;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new PrintStatement(expression);
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String,InterfaceType> typeEnv) throws MyException
    {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
