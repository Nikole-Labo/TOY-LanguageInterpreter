package Module.Values;

import Module.Types.BoolType;
import Module.Types.InterfaceType;

import java.util.Objects;

public class BoolValue implements InterfaceValue{
    private boolean bool;

    public BoolValue(boolean b)
    {
        this.bool=b;
    }

    public boolean getValue()
    {
        return bool;
    }

    @Override
    public boolean equals(Object another)
    {
        if(another instanceof BoolValue bv)
            return Objects.equals(bv.getValue(),this.bool);
        return false;
    }

    @Override
    public InterfaceType getType()
    {
        return new BoolType();
    }

    @Override
    public InterfaceValue deepCopy()
    {
        return new BoolValue(this.bool);
    }

    @Override
    public String toString()
    {
        return String.valueOf(bool);
    }

    @Override
    public InterfaceValue defaultValue()
    {
        return new BoolValue(false);
    }

}
