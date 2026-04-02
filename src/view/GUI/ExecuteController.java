package view.GUI;

import Controller.Controller;
import Module.Statements.InterfaceStatement;
import Module.Values.InterfaceValue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ExecuteController
{
    private Controller prgController;
    @FXML
    private Scene scene;
    @FXML
    private Pane selectPrgPane;
    @FXML
    private TextArea nrOfPrgStates;
    @FXML
    private TableView<Map.Entry<Integer,String>> heapTable;
    @FXML
    private TableColumn<Map.Entry<Integer, String>, Integer> heapAddressColumn;
    @FXML
    private TableColumn<Map.Entry<Integer, String>, String> heapValueColumn;
    @FXML
    private ListView<InterfaceValue> out;
    @FXML
    private ListView<Map.Entry<String, String>> fileTable;
    @FXML
    private ListView<Integer> prgStatesIds;
    @FXML
    private TableView<Map.Entry<String, String>> symTable;
    @FXML
    private TableColumn<Map.Entry<String, String>, String>symTableName;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> symTableValue;
    @FXML
    private ListView<InterfaceStatement> exeStack;
    @FXML
    private Text exceptions;
    @FXML
    private Button runOneStepBtn;


    public void setup()
    {
        runOneStepBtn.setText("Run One Step");

        exceptions.setText("");

        this.nrOfPrgStates.setText("\n\nNr. of Program\nStates: " + prgController.getNrOfPrgStates());

        this.prgStatesIds.setItems(FXCollections.observableArrayList(prgController.getPrgStateIds()));
        this.prgStatesIds.getSelectionModel().selectFirst();
        this.prgStatesIds.getSelectionModel().selectedItemProperty().addListener(
                (v, oldV, newV) -> updateSymTableExeStk(newV));

        heapAddressColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        heapValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));


        this.heapTable.setItems(FXCollections.observableArrayList(prgController.getHeapTable()));
        this.heapTable.refresh();

        symTableName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        symTableValue.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));

        this.symTable.setItems(FXCollections.observableArrayList(prgController.getSymTable(
                this.prgStatesIds.getSelectionModel().getSelectedItem())));
        this.symTable.refresh();

        this.fileTable.setItems(FXCollections.observableList(prgController.getFileTable()));

        this.out.setItems(FXCollections.observableList(prgController.getOut()));

        this.exeStack.setItems(FXCollections.observableArrayList(prgController.getExeStack(
                this.prgStatesIds.getSelectionModel().getSelectedItem())));

    }


    public void backToSelectPane(ActionEvent actionEvent) {
        scene.setRoot(selectPrgPane);
    }


    void updateSymTableExeStk(Integer newV)
    {
        if (newV == null) return;
        this.symTable.setItems(FXCollections.observableArrayList(prgController.getSymTable(
                this.prgStatesIds.getSelectionModel().getSelectedItem())));
        ArrayList<InterfaceStatement> exeStk = prgController.getExeStack(this.prgStatesIds.getSelectionModel().getSelectedItem());
        Collections.reverse(exeStk);
        this.exeStack.setItems(FXCollections.observableArrayList(exeStk));
    }

    @FXML
    void runOneStep()
    {
        this.prgController.oneStep();
        this.nrOfPrgStates.setText("\n\nNr. of Program\nStates: " + prgController.getNrOfPrgStates());

        if(prgController.getNrOfPrgStates() >0)
        {
            this.prgStatesIds.setItems(FXCollections.observableArrayList(prgController.getPrgStateIds()));
            if(this.prgStatesIds.getSelectionModel().getSelectedItem() == null)
                this.prgStatesIds.getSelectionModel().selectFirst();
            this.symTable.setItems(FXCollections.observableArrayList(prgController.getSymTable(this.prgStatesIds.getSelectionModel().getSelectedItem())));
            this.heapTable.setItems(FXCollections.observableArrayList(prgController.getHeapTable()));
            this.out.setItems(FXCollections.observableArrayList(prgController.getOut()));

            this.fileTable.setItems(FXCollections.observableList(prgController.getFileTable()));
            ArrayList<InterfaceStatement> exeStk = prgController.getExeStack(this.prgStatesIds.getSelectionModel().getSelectedItem());
            Collections.reverse(exeStk);
            this.exeStack.setItems(FXCollections.observableArrayList(exeStk));
        }
        else
        {
            exceptions.setText("The execution Stack\n is empty!");
        }
    }
    @FXML
    void backToSelectPane() {
        scene.setRoot(selectPrgPane);
    }
    public void setScene(Scene scene)
    {
        this.scene = scene;
        selectPrgPane = (Pane) scene.getRoot();
    }

    public void setPrgController(Controller prgController) {
        this.prgController = prgController;
    }
}
