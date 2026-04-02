package Module.Expressions;
import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.ADT.InterfaceHeap;
import Module.ADT.InterfaceSymbolTable;
import Module.Types.IntType;
import Module.Types.InterfaceType;
import Module.Values.IntValue;
import Module.Values.InterfaceValue;

public class ArithmeticExpression implements InterfaceExpression
{
    InterfaceExpression expression1;
    InterfaceExpression expression2;
    int operand;

    public ArithmeticExpression(char op, InterfaceExpression e1, InterfaceExpression e2)
    {
        this.expression1 = e1;
        this.expression2 = e2;
        if (op == '+')
            this.operand = 1;
        if (op == '-')
            this.operand = 2;
        if (op == '*')
            this.operand = 3;
        if (op == '/')
            this.operand = 4;

    }
    public InterfaceValue evaluate(InterfaceSymbolTable symbolTable, InterfaceHeap<InterfaceValue> heap) throws MyException
    {
        InterfaceValue value1, value2;
        value1 = expression1.evaluate(symbolTable, heap);
        if (value1.getType().equals(new IntType()))
        {
            value2 = expression2.evaluate(symbolTable, heap);
            if (value2.getType().equals(new IntType()))
            {
                IntValue i1 = (IntValue)value1;
                IntValue i2 = (IntValue)value2;
                int n1,n2;
                n1= i1.getValue();
                n2 = i2.getValue();
                if (operand ==1) return new IntValue(n1+n2);
                if (operand ==2) return new IntValue(n1-n2);
                if(operand ==3) return new IntValue(n1*n2);
                if(operand ==4)
                    if(n2==0) throw new MyException("division by zero");
                    else return new IntValue(n1/n2);
            }
            else
                throw new MyException("second operand is not an integer");
        }
        else
            throw new MyException("first operand is not an integer");
        return null;
    }

    @Override
    public String toString()
    {
        String s = "";
        if (operand == 1) s = "+";
        if (operand == 2) s = "-";
        if (operand == 3) s = "*";
        if (operand == 4) s = "/";
        return expression1.toString() + s + expression2.toString();
    }

    @Override
    public InterfaceExpression deepCopy()
    {
        return new ArithmeticExpression((char)this.operand, expression1.deepCopy(), expression2.deepCopy());
    }

    @Override
    public InterfaceType typeCheck(InterfaceDictionary<String,InterfaceType> typeEnv) throws MyException
    {
        InterfaceType typ1, typ2;
        typ1 = expression1.typeCheck(typeEnv);
        typ2 = expression2.typeCheck(typeEnv);
        if( typ1.equals(new IntType()))
        {
            if(typ2.equals(new IntType()))
                return new IntType();
            else
                throw new MyException("second operand is not an int");

        }
        else
            throw new MyException("first operand is not an int");

    }

}
