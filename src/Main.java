import Controller.Controller;
import Examples.Examples;
import Module.Expressions.ArithmeticExpression;
import Module.Expressions.ValueExpression;
import Module.Expressions.VariableExpression;
import Module.Statements.*;
import Module.Types.IntType;
import Module.Values.IntValue;
import Module.ADT.*;
import Module.ProgramState;
import Module.Statements.CompoundStatement;
import Module.Statements.InterfaceStatement;
import Module.Statements.VariableDeclarationStatement;
import Repository.InterfaceRepository;
import Repository.Repository;
import view.Command.ExitCommand;
import view.Command.runExample;
import view.TextMenu;

public class Main
{
    public static void main(String args[])
    {
        TextMenu menu=new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));

        try
        {
            //System.out.println("1. int v;\n v=2; \nPrint(v);\n");
            InterfaceStatement ex1 = Examples.example1();
            ex1.typeCheck(new Dictionary<>());
            ProgramState prg1 = new ProgramState(new ExecutionStack(), new SymbolTable(), new Dictionary<>(), new MyList<>(), new Heap<>(), ex1);
            InterfaceRepository repo1 = new Repository(prg1, "log1.txt");
            Controller ctr1 = new Controller(repo1);
            menu.addCommand(new runExample("1", ex1.toString(), ctr1));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        try
        {
            InterfaceStatement ex5 = Examples.example5();
            ex5.typeCheck(new Dictionary<>());
            ProgramState prg5 = new ProgramState(new ExecutionStack(), new SymbolTable(), new Dictionary<>(), new MyList<>(), new Heap<>(), ex5);
            InterfaceRepository repo5 = new Repository(prg5,"log5.txt");

            Controller ctrl5=new Controller(repo5);
            menu.addCommand(new runExample("5",ex5.toString(),ctrl5));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            // int a; int b; a=2+3*5; b=a+1; print(b)
            InterfaceStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()), new CompoundStatement(new VariableDeclarationStatement("b",
                    new IntType()), new CompoundStatement(new AssigneStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)),
                    new ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                    new CompoundStatement(new AssigneStatement("b", new ArithmeticExpression('+', new VariableExpression("a"),
                            new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
            ex2.typeCheck(new Dictionary<>());
            ProgramState prg2 = new ProgramState(new ExecutionStack(), new SymbolTable(),new Dictionary<>(), new MyList<>(), new Heap<>(), ex2);
            InterfaceRepository repo2 = new Repository(prg2,"log2.txt");
            Controller ctrl2 = new Controller(repo2);
            menu.addCommand(new runExample("2",ex2.toString(),ctrl2));

        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        try
        {
            // bool a; int v; a=true;  if (a): v=2; else: v=3; print(v)
            InterfaceStatement ex3 = Examples.example3();
            ex3.typeCheck(new Dictionary<>());
            ProgramState prg3 = new ProgramState(new ExecutionStack(), new SymbolTable(), new Dictionary<>(), new MyList<>(), new Heap<>(), ex3);
            InterfaceRepository repo2 = new Repository(prg3, "log3.txt");
            Controller ctrl2 = new Controller(repo2);
            menu.addCommand(new runExample("3", ex3.toString(), ctrl2));
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        try
        {
            InterfaceStatement ex6 = Examples.example6();
            ProgramState prg6 = new ProgramState(new ExecutionStack(), new SymbolTable(), new Dictionary<>(), new MyList<>(), new Heap<>(), ex6);
            ex6.typeCheck(new Dictionary<>());
            InterfaceRepository repo6 = new Repository(prg6,"log6.txt");
            Controller c = new Controller(repo6);
            menu.addCommand(new runExample("6",ex6.toString(),c));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try
        {
            InterfaceStatement ex8 = Examples.example9();
            ex8.typeCheck(new Dictionary<>());
            ProgramState prg8 = new ProgramState(new ExecutionStack(), new SymbolTable(), new Dictionary<>(), new MyList<>(), new Heap<>(), ex8);
            InterfaceRepository repo8 = new Repository(prg8, "log8.txt");
            Controller c = new Controller(repo8);
            menu.addCommand(new runExample("8", ex8.toString(), c));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try
        {
            InterfaceStatement ex7 = Examples.example7();
            ex7.typeCheck(new Dictionary<>());
            ProgramState prg7 = new ProgramState(new ExecutionStack(), new SymbolTable(), new Dictionary<>(), new MyList<>(), new Heap<>(), ex7);
            InterfaceRepository repo7 = new Repository(prg7, "log7.txt");
            Controller c = new Controller(repo7);
            menu.addCommand(new runExample("7", ex7.toString(), c));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try
        {
            InterfaceStatement ex9 = Examples.example10();
            ex9.typeCheck(new Dictionary<>());
            ProgramState prg9 = new ProgramState(new ExecutionStack(), new SymbolTable(), new Dictionary<>(), new MyList<>(), new Heap<>(), ex9);
           InterfaceRepository repo7 = new Repository(prg9, "log9.txt");
            Controller c = new Controller(repo7);
            menu.addCommand(new runExample("9", ex9.toString(), c));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        try
        {
            InterfaceStatement ex10 = Examples.example11();
            ex10.typeCheck(new Dictionary<>());
            ProgramState prg9 = new ProgramState(new ExecutionStack(), new SymbolTable(), new Dictionary<>(), new MyList<>(), new Heap<>(), ex10);
            InterfaceRepository repo7 = new Repository(prg9, "log10.txt");
            Controller c = new Controller(repo7);
            menu.addCommand(new runExample("10", ex10.toString(), c));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try
        {
            InterfaceStatement ex11 = Examples.example12();
            ex11.typeCheck(new Dictionary<>());
            ProgramState prg9 = new ProgramState(new ExecutionStack(), new SymbolTable(), new Dictionary<>(), new MyList<>(), new Heap<>(), ex11);
            InterfaceRepository repo7 = new Repository(prg9, "log11.txt");
            Controller c = new Controller(repo7);
            menu.addCommand(new runExample("10", ex11.toString(), c));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        try
        {
            InterfaceStatement exampleConditional = Examples.exampleConditionalAssigment();
            exampleConditional.typeCheck(new Dictionary<>());
            ProgramState conditionalProgram = new ProgramState(new ExecutionStack(), new SymbolTable(), new Dictionary<>(), new MyList<>(), new Heap<>(), exampleConditional);
            InterfaceRepository repositoryConditional = new Repository(conditionalProgram, "logConditional.txt");
            Controller controllerConditional = new Controller(repositoryConditional);
            menu.addCommand(new runExample("ConditionalAssigment", exampleConditional.toString(), controllerConditional));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        menu.show();
    }
}

