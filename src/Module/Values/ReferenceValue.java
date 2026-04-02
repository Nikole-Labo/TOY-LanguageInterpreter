package Module.Values;

import Module.Types.ReferenceType;
import Module.Types.InterfaceType;

public class ReferenceValue implements InterfaceValue
{
    int address;
    InterfaceType locationType;
    public ReferenceValue(int address,InterfaceType location)
    {
        this.address=address;
        this.locationType=location;

    }

    public int getAddress()
    {
        return this.address;
    }

    public InterfaceType getLocationType()
    {
        return this.locationType;
    }

    @Override
    public InterfaceType getType()
    {
        return new ReferenceType(locationType);
    }

    @Override
    public InterfaceValue deepCopy()
    {
        return new ReferenceValue(this.address,this.locationType);
    }

    @Override
    public String toString()
    {
        return "("+address+", "+locationType.toString()+")";
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof ReferenceValue && this.address == ((ReferenceValue) o).address){
            return this.locationType.equals(((ReferenceValue) o).locationType);
        }
        return false;
    }

    @Override
    public InterfaceValue defaultValue() {
        return null;
    }
}
