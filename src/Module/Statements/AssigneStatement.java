package Module.Statements;

import Exceptions.MyException;
import Module.ADT.ExecutionStack;
import Module.ADT.InterfaceDictionary;
import Module.ADT.InterfaceSymbolTable;
import Module.ADT.MyStack;
import Module.Expressions.InterfaceExpression;
import Module.Types.InterfaceType;
import Module.ProgramState;
import Module.Values.InterfaceValue;

public class AssigneStatement implements InterfaceStatement
{

    private String id;
    private InterfaceExpression expression;

    public AssigneStatement(String i, InterfaceExpression e)
    {
        id = i;
        expression = e;
    }

    public String toString(){ return id + "=" + expression.toString();}

    public ProgramState execute(ProgramState state) throws MyException
    {
        ExecutionStack stack = state.getExecutionStack();
        InterfaceSymbolTable symbolTable = state.getSymbolTable();
        if (symbolTable.contains(id))
        {
            InterfaceValue value = expression.evaluate(symbolTable, state.getHeap());
            InterfaceType typeId=(symbolTable.lookUp(id)).getType();
            if (value.getType().equals(typeId))
                symbolTable.update(id, value);
            else
                throw new MyException("declared type of variable"+id+" and type of the assigned expression do not match");
        }
        else
            throw new MyException("the used variable" +id + " was not declared before");
        return null;

    }

    @Override
    public InterfaceStatement deepCopy() {
        return new AssigneStatement(this.id, this.expression);
    }

    @Override
    public InterfaceDictionary<String,InterfaceType> typeCheck(InterfaceDictionary<String,InterfaceType> typeEnv) throws MyException
    {
        InterfaceType variableType = typeEnv.lookUp(id);
        if (variableType==null)
            throw new MyException("Type check exception !");


        InterfaceType expressionType = expression.typeCheck(typeEnv);
        if(variableType.equals(expressionType))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");



    }

}
