package Module.Statements;

import Exceptions.DictionaryException;
import Exceptions.MyException;
import Exceptions.ReadFileException;
import Module.ADT.InterfaceDictionary;
import Module.Expressions.InterfaceExpression;
import Module.Types.IntType;
import Module.Types.InterfaceType;
import Module.Types.StringType;
import Module.Values.IntValue;
import Module.Values.InterfaceValue;
import Module.Values.StringValue;
import Module.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements InterfaceStatement
{
    private InterfaceExpression expression;
    private String varName;

    public ReadFileStatement(InterfaceExpression expr, String var)
    {
        expression = expr;
        varName = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        try
        {
            InterfaceValue value = state.getSymbolTable().getElement(varName);
            if(value.getType() instanceof IntType)
            {
                if (expression.evaluate(state.getSymbolTable(), state.getHeap()) instanceof StringValue str)
                {
                    try
                    {
                        BufferedReader bufferedReader = state.getFileTable().lookUp(str);
                        if(bufferedReader == null) throw new ReadFileException("There are no opened files with this name!");
                        String line = bufferedReader.readLine();
                        int val;
                        if(line==null)
                            val=0;
                        else
                            val = Integer.parseInt(line);
                        state.getSymbolTable().update(varName, new IntValue(val));
                        return null;
                    }
                    catch (IOException ioException)
                    {
                        throw new ReadFileException("Error reading line!");
                    }
                }
                else throw new ReadFileException("First argument must be a StringValue!");
            }
            else throw new ReadFileException("Second argument isn't IntType!");
        }
        catch (DictionaryException dr)
        {
            throw new ReadFileException("Second argument is not declared!");
        }
    }

    @Override
    public String toString() {
        return "ReadFile(" + expression + ", " + varName + ")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new ReadFileStatement(expression.deepCopy(), varName);
    }

    @Override
    public InterfaceDictionary<String, InterfaceType> typeCheck(InterfaceDictionary<String, InterfaceType> typeEnv) throws MyException
    {
        if(typeEnv.lookUp(varName).equals( new IntType()))
            if (expression.typeCheck(typeEnv).equals(new StringType()))
                return typeEnv;
            else throw new ReadFileException("The second parameter must pe of type String!");
        else throw new ReadFileException("The first parameter must pe of type Int!");
    }
}
