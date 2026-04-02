package Module.Statements;

import Exceptions.MyException;
import Exceptions.OpenRFileException;
import Module.ADT.InterfaceDictionary;
import Module.ProgramState;
import Module.Types.InterfaceType;
import Module.Types.StringType;
import Module.Values.StringValue;
import Module.Expressions.InterfaceExpression;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStatement implements  InterfaceStatement
{
    private InterfaceExpression expression;

    public OpenRFileStatement(InterfaceExpression expr)
    {
        this.expression = expr;
    }

    @Override
    public ProgramState execute(ProgramState prgState) throws MyException
    {
        System.out.println("OPENING FILE");
        if (expression.evaluate(prgState.getSymbolTable(), prgState.getHeap()) instanceof StringValue str)
        {
            if(prgState.getFileTable().lookUp(str)!=null)
            {
                throw new OpenRFileException("File already opened");
            }
            else
            {
                try
                {
                    BufferedReader br = new BufferedReader(new FileReader(str.getValue()));
                    prgState.getFileTable().put(str, br);
                    System.out.println("ADDED TO FILE TABLE");
                    return null;
                }
                catch (FileNotFoundException e2)
                {
                    throw new OpenRFileException("File does not exist");
                }
            }
        }
        else throw new OpenRFileException("Expression is not a string");
    }

    @Override
    public String toString()
    {
        return "OpenRFile("+ expression.toString()+")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new OpenRFileStatement(this.expression.deepCopy());
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws MyException
    {
        if (expression.typeCheck(typeEnv).equals(new StringType()))
        {
            return typeEnv;
        }
        else throw new MyException("The parameter must pe of type String!");
    }


}
