package Module.Types;

import Module.Values.ReferenceValue;
import Module.Values.InterfaceValue;

public class ReferenceType implements InterfaceType
{
    private InterfaceType inner;

    public ReferenceType(InterfaceType in)
    {
        this.inner=in;
    }

    public InterfaceType getInner(){
        return this.inner;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof ReferenceType) return inner.equals(((ReferenceType) o).getInner());
        else return false;
    }

    @Override
    public InterfaceValue defaultValue()
    {
        return new ReferenceValue(0,inner);
    }

    public String toString()
    {
        return "Ref("+inner.toString()+")";
    }

}
