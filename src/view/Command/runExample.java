package view.Command;

import Controller.Controller;
import Exceptions.MyException;

public class runExample extends Command
{
    private Controller controller;
    public runExample(String key,String desc,Controller ctr)
    {
        super(key,desc);
        this.controller=ctr;
    }
    @Override
    public void execute()
    {
        this.controller.allSteps();
    }
}
