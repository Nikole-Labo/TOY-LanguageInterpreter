package Module.Types;

import Module.Values.StringValue;
import Module.Values.InterfaceValue;

import java.util.Objects;

public class StringType implements InterfaceType
{
    @Override
    public InterfaceValue defaultValue() {
        return new StringValue("");
    }

    @Override
    public boolean equals(Object another)
    {
        return another instanceof StringType;
    }

    @Override
    public String toString()
    {
        return "string";
    }
}
