package Module.ADT;

import Exceptions.ListException;

import java.util.ArrayList;

public class MyList<T> implements InterfaceList<T>
{
    private ArrayList<T> list;

    public MyList()
    {
        this.list = new ArrayList<>();
    }

    @Override
    public T getElement(int position) throws ListException
    {
        if(position < 0 || position >= this.list.size())
            throw new ListException("Invalid position!");
        return this.list.get(position);
    }

    @Override
    public int getPosition(T element) throws ListException
    {
        if(! this.list.contains(element))
            throw new ListException("The element is not in the list!");
        return this.list.indexOf(element);
    }

    @Override
    public boolean isEmpty() {
        return this.size()==0;
    }

    @Override
    public int size()
    {
        return this.list.size();
    }

    @Override
    public void insert(T element, int position) throws ListException
    {
        if(position < 0 || position > list.size())
            throw new ListException("Invalid position!");
        this.list.add(position, element);
    }

    @Override
    public void removeByPosition(int position) throws ListException
    {
        if(position < 0 || position >= this.list.size())
            throw new ListException("Invalid position!");
        this.list.remove(position);
    }

    @Override
    public void removeByElement(T element) throws ListException
    {
        if(!this.list.contains(element))
            throw new ListException("The element is not in the list!");

        this.list.remove(element);
    }

    @Override
    public String toString(){
        return this.list.toString();
    }

    @Override
    public void add(T v)
    {
        list.add(v);
    }

    @Override
    public InterfaceList<T> deepCopy()
    {
        MyList<T> newList = new MyList<>();
        newList.list.addAll(this.list);
        return newList;
    }

    @Override
    public ArrayList<T> values(){
        return (ArrayList<T>) this.list.clone();
    }

}

