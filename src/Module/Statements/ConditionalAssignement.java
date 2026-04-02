package Module.Statements;

import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.ADT.InterfaceSymbolTable;
import Module.ADT.SymbolTable;
import Module.Expressions.InterfaceExpression;
import Module.Types.BoolType;
import Module.Types.InterfaceType;
import Module.ProgramState;
import Module.Values.BoolValue;
import Module.Values.InterfaceValue;

public class ConditionalAssignement implements InterfaceStatement
{
    private final String key;
    private final InterfaceExpression expression1;
    private final InterfaceExpression expression2;
    private final InterfaceExpression expression3;

    public ConditionalAssignement (String k, InterfaceExpression expr1, InterfaceExpression expr2, InterfaceExpression expr3)
    {
        key = k;
        expression1 = expr1;
        expression2 = expr2;
        expression3 = expr3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        InterfaceSymbolTable interfaceSymbolTable = state.getSymbolTable();
        SymbolTable symbolTable = (SymbolTable) interfaceSymbolTable;

        if(symbolTable.contains(key))
        {
            InterfaceValue value1 = expression1.evaluate(symbolTable, state.getHeap());
            InterfaceValue value2 = expression2.evaluate(symbolTable, state.getHeap());
            InterfaceValue value3 = expression3.evaluate(symbolTable, state.getHeap());
            InterfaceType typeID = symbolTable.lookUp(key).getType();

            if(value2.getType().equals(typeID) && value3.getType().equals(typeID))
            {
                if(value1.getType().equals(new BoolType()))
                {
                    boolean condition = ((BoolValue)value1).getValue();
                    if (condition)
                    {
                        symbolTable.update(key, value2);
                    }
                    else
                    {
                        symbolTable.update(key, value3);
                    }
                }
                else
                {
                 throw new MyException("The expression of conditional Assignement need to pe of BoolType") ;
                }
            }
            else
            {
                throw new MyException("The declared types of variable " + key + " and the types of assigned expression do not match") ;
            }
        }
        else
        {
            throw new MyException("Variable " + key + " does not exist") ;
        }
        state.setSymbolTable(symbolTable);
        return null;
    }

    @Override
    public InterfaceStatement deepCopy()
    {
        return new ConditionalAssignement (key, expression1.deepCopy(), expression2.deepCopy(), expression3.deepCopy()) ;
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws MyException
    {
        InterfaceType typeVar = typeEnv.lookUp(key);
        InterfaceType typeExpression2 = expression2.typeCheck(typeEnv);
        InterfaceType typeExpression3 = expression3.typeCheck(typeEnv);
        if(typeVar.equals(typeExpression2) && typeVar.equals(typeExpression3))
        {
            InterfaceType typeExpression1 = expression1.typeCheck(typeEnv);
            if(typeExpression1.equals(new BoolType()))
            {
                return typeEnv;
            }
            else
            {
                throw new MyException("The expression of Conditional Assigment Statement need to be of BoolType") ;
            }
        }
        else
        {
            throw new MyException("expression2 and expression3 do not match") ;
        }
    }

    @Override
    public String toString()
    {
        return String.format("%s = %s ? %s : %s", key, expression1.toString(), expression2.toString(), expression3.toString()) ;
    }
}
