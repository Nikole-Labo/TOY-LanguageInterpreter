package Module.Types;

import Module.Values.InterfaceValue;
import Module.Values.IntValue;

import java.util.Objects;

public class IntType implements InterfaceType
{
    @Override
    public InterfaceValue defaultValue()
    {
        return new IntValue(0);
    }

    @Override
    public boolean equals(Object another)
    {
        return (another instanceof IntType);
    }

    @Override
    public String toString()
    {
        return "int";
    }
}
