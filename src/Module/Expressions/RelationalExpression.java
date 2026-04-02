package Module.Expressions;

import Exceptions.MyException;
import Exceptions.RelationalException;
import Module.ADT.InterfaceDictionary;
import Module.Types.BoolType;
import Module.Types.IntType;
import Module.Types.InterfaceType;
import Module.Values.BoolValue;
import Module.Values.IntValue;
import Module.Values.InterfaceValue;
import Module.ADT.InterfaceHeap;
import Module.ADT.InterfaceSymbolTable;


import java.util.Objects;

public class RelationalExpression implements InterfaceExpression
{
    private InterfaceExpression expression1;
    private InterfaceExpression expression2;
    private String operand;

    public RelationalExpression(InterfaceExpression expr1, InterfaceExpression expr2, String op)
    {
        expression1 = expr1;
        expression2 = expr2;
        this.operand = op;
    }

    @Override
    public InterfaceValue evaluate(InterfaceSymbolTable symbolTable, InterfaceHeap<InterfaceValue> heap) throws MyException
    {
        InterfaceValue nr1 = this.expression1.evaluate(symbolTable, heap);
        if (nr1.getType() instanceof IntType)
        {
            IntValue Nr1 = (IntValue) nr1;
            InterfaceValue nr2 = this.expression2.evaluate(symbolTable,heap);
            if(nr2.getType() instanceof IntType)
            {
                IntValue Nr2=(IntValue) nr2;
                if(Objects.equals(this.operand,"<"))
                    return new BoolValue(Nr1.getValue() < Nr2.getValue());
                else if(Objects.equals(this.operand,">"))
                    return new BoolValue(Nr1.getValue() > Nr2.getValue());
                else if(Objects.equals(this.operand,"<="))
                    return new BoolValue(Nr1.getValue() <= Nr2.getValue());
                else if(Objects.equals(this.operand,">="))
                    return new BoolValue(Nr1.getValue() >= Nr2.getValue());
                else if(Objects.equals(this.operand,"=="))
                    return new BoolValue(Nr1.getValue() == Nr2.getValue());
                else if(Objects.equals(this.operand,"!="))
                    return new BoolValue(Nr1.getValue() != Nr2.getValue());
                else
                    throw new RelationalException("Invalid operator");
            }
            else throw new RelationalException("Second operand is not an integer");
        }
        else throw new RelationalException("First operand is not an integer");

    }

    @Override
    public String toString()
    {
        return expression1.toString()+ operand + expression2.toString();
    }

    @Override
    public RelationalExpression deepCopy()
    {
        return new RelationalExpression(expression1.deepCopy(), expression2.deepCopy(), operand);
    }

    @Override
    public InterfaceType typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws MyException
    {
        InterfaceType type1, type2;
        type1= expression1.typeCheck(typeEnv);
        type2= expression2.typeCheck(typeEnv);
        if (type1.equals(new IntType()))
        {
            if (type2.equals(new IntType()))
            {
                return new BoolType();
            }
            else throw new MyException("Second operand is not an integer!");
        }
        else throw new MyException("First operand is not an integer!");
    }




}
