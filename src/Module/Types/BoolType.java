package Module.Types;
import Module.Values.InterfaceValue;
import Module.Values.BoolValue;
public class BoolType implements InterfaceType
{
    @Override
    public InterfaceValue defaultValue() {
        return new BoolValue(false);
    }
    @Override
    public String toString()
    {
        return "bool";
    }
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof BoolType;
    }
}
