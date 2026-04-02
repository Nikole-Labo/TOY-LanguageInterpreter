package Module.Expressions;

import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.ADT.InterfaceHeap;
import Module.ADT.InterfaceSymbolTable;
import Module.Types.BoolType;
import Module.Types.InterfaceType;
import Module.Values.BoolValue;
import Module.Values.InterfaceValue;

public class LogicExpression implements InterfaceExpression
{
    public InterfaceExpression getExpression1() {
        return expression1;
    }

    public InterfaceExpression getExpression2() {
        return expression2;
    }

    public int getOperand() {
        return operand;
    }

    private InterfaceExpression expression1;
    private InterfaceExpression expression2;
    int operand;

    public LogicExpression(char op, InterfaceExpression e1, InterfaceExpression e2)
    {
        this.expression1 = e1;
        this.expression2 = e2;
        if (op == '&')
            operand = 1;
        if (op == '|')
            operand = 2;
    }

    @Override
    public InterfaceExpression deepCopy()
    {
        return new LogicExpression((char) operand, expression1.deepCopy(), expression2.deepCopy());
    }

    @Override
    public InterfaceValue evaluate(InterfaceSymbolTable symbolTable, InterfaceHeap<InterfaceValue> heap) throws MyException
    {
        InterfaceValue value1, value2;
        value1 = expression1.evaluate(symbolTable,heap);
        value2 = expression2.evaluate(symbolTable,heap);
        if (value1.getType().equals(new BoolType()) && value2.getType().equals(new BoolType()))
        {
            BoolValue b1 = (BoolValue)value1;
            BoolValue b2 = (BoolValue)value2;
            if (operand == 1)
                return new BoolValue(b1.getValue() && b2.getValue());
            if (operand == 2)
                return new BoolValue(b1.getValue() || b2.getValue());
        }
        throw new MyException("Operands are not boolean");
    }

    @Override
    public InterfaceType typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws MyException
    {
        InterfaceType typ1,typ2;
        typ1= expression1.typeCheck(typeEnv);
        typ2= expression2.typeCheck(typeEnv);
        if (typ1.equals(new BoolType()))
        {
            if (typ2.equals(new BoolType()))
            {
                return new BoolType();
            }
            else throw new MyException("Second operand is not an integer!");
        }
        else throw new MyException("First operand is not an integer!");

    }
}
