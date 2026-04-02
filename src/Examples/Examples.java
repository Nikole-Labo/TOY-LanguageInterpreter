package Examples;

import Module.Expressions.*;
import Module.Statements.*;
import Module.Types.BoolType;
import Module.Types.IntType;
import Module.Types.ReferenceType;
import Module.Types.StringType;
import Module.Values.BoolValue;
import Module.Values.IntValue;
import Module.Values.InterfaceValue;
import Module.Values.StringValue;
import Module.Statements.CompoundStatement;
import Module.Statements.InterfaceStatement;
import Module.Statements.VariableDeclarationStatement;

public class Examples
{
    public static InterfaceStatement example1()
    {
        // int v;v=2;print(v)
        return new CompoundStatement(new VariableDeclarationStatement("v", new StringType()), new CompoundStatement(new AssigneStatement("v",
                new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
    }

    public static InterfaceStatement example2()
    {
        return new CompoundStatement(new VariableDeclarationStatement("a", new IntType()), new CompoundStatement(new VariableDeclarationStatement("b",
                new IntType()), new CompoundStatement(new AssigneStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(2)),
                new ArithmeticExpression('*', new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                new CompoundStatement(new AssigneStatement("b", new ArithmeticExpression('+', new VariableExpression("a"),
                        new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
    }

    public static InterfaceStatement example3()
    {
        // bool a; int v; a=true;  if (a): v=2; else: v=3; print(v)
        return new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()), new CompoundStatement(new VariableDeclarationStatement("v", new IntType())
                , new CompoundStatement(new AssigneStatement("a", new ValueExpression(new BoolValue(true))),
                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssigneStatement("v", new ValueExpression(new IntValue(2))),
                        new AssigneStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new VariableExpression("v"))))));
    }

    public static InterfaceStatement example5()
    {
        // string varf; varf="test.in"; openRFile(varf); int varc; readFile(varf,varc); print(varc);
        // readFile(varf,varc); print(varc); closeRFile(varf)
        return new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()), new CompoundStatement(new AssigneStatement("varf",
                new ValueExpression(new StringValue("test.in"))), new CompoundStatement(new OpenRFileStatement(new VariableExpression("varf")),
                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()), new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"),
                        "varc"), new CompoundStatement(new PrintStatement(new VariableExpression("varc")), new CompoundStatement(
                        new ReadFileStatement(new VariableExpression("varf"), "varc"), new CompoundStatement(
                        new PrintStatement(new VariableExpression("varc")), new CloseFileStatement(new VariableExpression("varf"))))))))));
    }

    public static InterfaceStatement example6()
    {
        // Ref int v; new (v,20); print(rH(v)); wH(v, 30); print(rH(v) + 5);
        return new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())), new CompoundStatement(new AllocateHeapStatement("v",
                 new ValueExpression(new IntValue(20))),new CompoundStatement( new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                new CompoundStatement(new HeapWritingStatement("v", new ValueExpression(new IntValue(30))), new PrintStatement(new ArithmeticExpression(
                        '+', new ReadHeapExpression(new VariableExpression("v")),new ValueExpression(new IntValue(5))))))));
    }

    public static InterfaceStatement example8()
    {
        // Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); print(rH(rH(a)))
        return new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),new CompoundStatement(new AllocateHeapStatement("v",
                new ValueExpression(new IntValue(20))),new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                new CompoundStatement(new AllocateHeapStatement("a", new VariableExpression("v")),new CompoundStatement(new AllocateHeapStatement("v", new ValueExpression(
                        new IntValue(30))), new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))));
    }

    public static InterfaceStatement example7()
    {
        // int v; v = 4; while(v>0) { print(v); v=v-1; }; print(v)
        return new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssigneStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">"),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssigneStatement("v", new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new IntValue(1)))))
                                ), new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new NopStatement()))));
    }

    public static InterfaceStatement example9()
    {
        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        return new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(new AllocateHeapStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new AllocateHeapStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new CompoundStatement(new PrintStatement(// print(rH(rH(a)) + 5)
                                                                new ArithmeticExpression('+', new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)))
                                                        ),
                                                        new NopStatement()
                                                )
                                        )
                                )
                        )
                )
        );
    }

    public static InterfaceStatement example10()
    {
        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        return new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(new AllocateHeapStatement("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new AllocateHeapStatement("a",new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("a")),
                                                        new NopStatement()))))));
    }

    public static InterfaceStatement example11()
    {
        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        return new CompoundStatement(
                new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new AllocateHeapStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new AllocateHeapStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new AllocateHeapStatement("v", new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(
                                                        new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))),
                                                        new NopStatement()))))));
    }

    public static InterfaceStatement example12()
    {
        // int v; Ref int a; v=10;new(a,22); fork(wH(a,30);v=32;print(v); print(rH(a)));
        // print(v); print(rH(a))
        return new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new AssigneStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new AllocateHeapStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new HeapWritingStatement("a", new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(new AssigneStatement("v", new ValueExpression(new IntValue(32))), new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),new PrintStatement(new ReadHeapExpression(
                                                        new VariableExpression("a"))))))), new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));
    }

    public static InterfaceStatement exampleConditionalAssigment()
    {
        return new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                new CompoundStatement(new VariableDeclarationStatement("b", new ReferenceType(new IntType())),
                        new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(new AllocateHeapStatement("a", new ValueExpression(new IntValue(0))),
                                        new CompoundStatement(new AllocateHeapStatement("b", new ValueExpression(new IntValue(0))),
                                                new CompoundStatement(new HeapWritingStatement("a", new ValueExpression(new IntValue(1))),
                                                        new CompoundStatement(new HeapWritingStatement("b", new ValueExpression(new IntValue(2))),
                                                                new CompoundStatement(new ConditionalAssignement("v",
                                                                                new RelationalExpression(new ReadHeapExpression(new VariableExpression("a")),
                                                                                        new ReadHeapExpression(new VariableExpression("b")),
                                                                                        "<"),
                                                                                new ValueExpression(new IntValue(100)),
                                                                                new ValueExpression(new IntValue(200))
                                                                        ),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("v")),
                                                                                new CompoundStatement(new ConditionalAssignement("v",
                                                                                                new RelationalExpression(new ArithmeticExpression('-',
                                                                                                                new ReadHeapExpression(new VariableExpression("b")),
                                                                                                                new ValueExpression(new IntValue(2))), //b - 2
                                                                                                        new ReadHeapExpression(new VariableExpression("a")),
                                                                                                        ">"
                                                                                                ),
                                                                                                new ValueExpression(new IntValue(100)),
                                                                                                new ValueExpression(new IntValue(200))),

                                                                                new PrintStatement(new VariableExpression("v"))))))))))));


    }
}
