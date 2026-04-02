package view.GUI;

import Controller.Controller;
import Examples.Examples;
import Exceptions.MyException;
import Module.ADT.*;
import Module.ProgramState;
import Module.Statements.InterfaceStatement;
import Repository.InterfaceRepository;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


public class SelectController implements Initializable
{
    private ExecuteController executeController;
    @FXML
    private Scene scene;
    @FXML
    private Pane executePrgPane;
    @FXML
    private ListView<InterfaceStatement> programs;
    @FXML
    private Text typerchecker;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        programs.setItems(FXCollections.observableArrayList(getPrgStmt()));
    }

    @FXML
    void execute()
    {
        int index=programs.getSelectionModel().getSelectedIndex();

        if(index < 0)
            return;

        try
        {
            programs.getItems().get(index).typeCheck(new Dictionary<>());
            ProgramState prg = new ProgramState(new ExecutionStack(), new SymbolTable(), new Dictionary<>(),
                    new MyList<>(), new Heap<>(),programs.getItems().get(index));
            InterfaceRepository repo = new Repository(prg, "log" + (index+1) + ".txt");
            Controller prgController = new Controller(repo);
            executeController.setPrgController(prgController);
            typerchecker.setText("");
            scene.setRoot(executePrgPane);
            executeController.setup();
        }
        catch (MyException e)
        {
            typerchecker.setText(e.getMessage());
        }
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setExecutePrgPane(Pane execPane) {
        executePrgPane = execPane;
    }

    public void setExecuteController(ExecuteController ctrl)
    {
        this.executeController = ctrl;
        executeController.setScene(scene);
    }

    private List<InterfaceStatement> getPrgStmt()
    {
        InterfaceStatement ex1 = Examples.example1();
        InterfaceStatement ex2 = Examples.example2();
        InterfaceStatement ex3 = Examples.example3();
        InterfaceStatement ex5 = Examples.example5();
        InterfaceStatement ex6 = Examples.example6();
        InterfaceStatement ex7 = Examples.example7();
        InterfaceStatement ex8 = Examples.example8();
        InterfaceStatement ex9 = Examples.example9();
        InterfaceStatement ex10 = Examples.example10();
        InterfaceStatement ex11 = Examples.example11();
        InterfaceStatement ex12 = Examples.example12();
        InterfaceStatement exampleConditional = Examples.exampleConditionalAssigment();

        return new ArrayList<>(Arrays.asList(ex1, ex2, ex3,ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12, exampleConditional));
    }

}
