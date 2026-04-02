package Module.Values;
import Module.Types.IntType;
import Module.Types.InterfaceType;

public class IntValue implements InterfaceValue
{
    private int value;

    public IntValue(int value)
    {
        this.value =value;
    }

    public int getValue()
    {
        return this.value;
    }

    @Override
    public String toString()
    {
        return Integer.toString(value);
    }

    @Override
    public InterfaceType getType()
    {
        return new IntType();
    }

    @Override
    public boolean equals(Object another)
    {
        if (another instanceof IntValue iv)
            return iv.getValue()==this.value;
        return false;
    }

    @Override
    public InterfaceValue deepCopy()
    {
        return new IntValue(this.value);
    }

    @Override
    public InterfaceValue defaultValue()
    {
        return new IntValue(0);
    }
}
