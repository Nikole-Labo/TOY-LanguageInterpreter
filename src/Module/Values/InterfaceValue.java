package Module.Values;
import Module.Types.InterfaceType;
public interface InterfaceValue
{
    InterfaceType getType();
    String toString();
    boolean equals(Object another);
    InterfaceValue defaultValue();
    InterfaceValue deepCopy();
}
