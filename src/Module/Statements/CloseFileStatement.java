package Module.Statements;

import Exceptions.CloseRFileException;
import Exceptions.DictionaryException;
import Exceptions.MyException;
import Module.ADT.InterfaceDictionary;
import Module.Expressions.InterfaceExpression;
import Module.Types.InterfaceType;
import Module.Types.StringType;
import Module.Values.StringValue;
import Module.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStatement implements InterfaceStatement
{
    private InterfaceExpression expression;
    public CloseFileStatement(InterfaceExpression e) {
        this.expression = e;
    }

    public ProgramState execute(ProgramState state) throws MyException
    {
        if(this.expression.evaluate(state.getSymbolTable(), state.getHeap())instanceof StringValue str)
        {
            try
            {
                BufferedReader bufferedReader=state.getFileTable().lookUp((StringValue)this.expression.evaluate(state.getSymbolTable(), state.getHeap()));

                if(bufferedReader==null)
                    throw new MyException("File not opened");
                try
                {
                    bufferedReader.close();
                    state.getFileTable().removeElement(str);
                    return null;

                }
                catch(IOException e)
                {
                    throw new MyException("Error closing file");
                }
            }
            catch (DictionaryException e)
            {
                throw new MyException("File not opened");
            }
        }
        else throw new CloseRFileException("Incorrect arg type");
    }

    @Override
    public String toString() {
        return "CloseRFile(" + expression.toString() + ")";
    }

    @Override
    public InterfaceStatement deepCopy() {
        return new CloseFileStatement(expression.deepCopy());
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
