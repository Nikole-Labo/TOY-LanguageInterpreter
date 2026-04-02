package Module.Types;
import Module.Values.InterfaceValue;
public interface InterfaceType
{

    InterfaceValue defaultValue();
    boolean equals(Object another);
    String toString();
}
