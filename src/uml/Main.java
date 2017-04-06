package uml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main extends Application {

    public File file;
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("uml.fxml"));
        Parent root = loader.load();
        Companion comp = loader.getController();
        Parameters params = getParameters();
        List<String> list = params.getRaw();
        if (list.size() == 1){
            file = new File(list.get(0));
            comp.setFile(file);
            comp.drawArg();
        }
        if (list.size() == 2){
            file = new File(list.get(0));
            comp.setFile(file);
            comp.drawArg();
        }
        primaryStage.setTitle("UML creator");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
