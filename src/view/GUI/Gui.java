package view.GUI;

import Controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Gui extends Application
{

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("SelectView.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("ExecuteView.fxml"));

        Scene scene = new Scene(fxmlLoader1.load(), 850, 450);

        stage.setMinWidth(750);
        stage.setMinHeight(470);

        ((SelectController)fxmlLoader1.getController()).setScene(scene);
        ((SelectController)fxmlLoader1.getController()).setExecutePrgPane(fxmlLoader2.load());
        ((SelectController)fxmlLoader1.getController()).setExecuteController(fxmlLoader2.getController());


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

}







