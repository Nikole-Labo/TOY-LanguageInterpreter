package Module.Values;

import Module.Types.StringType;
import Module.Types.InterfaceType;

import java.util.Objects;

public class StringValue implements InterfaceValue
{

    private String string;

    public StringValue(String s)
    {
        this.string=s;
    }

    public String getValue()
    {
        return this.string;
    }

    @Override
    public int hashCode()
    {
        return string.hashCode();
    }

    @Override
    public InterfaceType getType() {
        return new StringType();
    }

    @Override
    public InterfaceValue deepCopy() {
        return new StringValue(this.string);
    }

    @Override
    public boolean equals(Object another)
    {
        if(another instanceof StringValue sv)
            return Objects.equals(sv.getValue(),this.string);
        return false;
    }

    @Override
    public String toString()
    {
        return this.string;
    }

    @Override
    public InterfaceValue defaultValue()
    {
        return new StringValue("");
    }
}
