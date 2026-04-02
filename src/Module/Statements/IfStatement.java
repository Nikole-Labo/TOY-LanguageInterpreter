package Module.Statements;

import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.Types.BoolType;
import Module.Types.InterfaceType;
import Module.Values.BoolValue;
import Module.ADT.ExecutionStack;
import Module.Expressions.InterfaceExpression;
import Module.ProgramState;

public class IfStatement implements InterfaceStatement
{
    public InterfaceExpression expression;
    public InterfaceStatement thenStatement;
    public InterfaceStatement elseStatement;

    public IfStatement(InterfaceExpression expr, InterfaceStatement then, InterfaceStatement els)
    {
        expression = expr;
        thenStatement = then;
        elseStatement = els;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        ExecutionStack stack = state.getExecutionStack();
        var symbolTable = state.getSymbolTable();
        var condition = expression.evaluate(symbolTable, state.getHeap());
        if (!condition.getType().equals(new BoolType()))
        {
            throw new MyException("The condition of IF is not a boolean.");
        }
        BoolValue boolCondition = (BoolValue) condition;
        if (boolCondition.getValue())
        {
            stack.push(thenStatement);
        }
        else
        {
            stack.push(elseStatement);
        }
        return null;
    }

    public String toString()
    {
        return "(IF("+ expression.toString()+") THEN(" + thenStatement.toString() +")ELSE("+ elseStatement.toString()+"))";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new IfStatement(this.expression, this.thenStatement, this.elseStatement);
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws
            MyException
    {
        InterfaceType expressionType = expression.typeCheck(typeEnv);
        if (expressionType.equals(new BoolType()))
        {
            thenStatement.typeCheck(typeEnv.deepCopy());
            elseStatement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else throw new MyException("The condition of IF must be of type bool!");
    }
}
