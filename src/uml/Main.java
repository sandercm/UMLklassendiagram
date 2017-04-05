package uml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parameters params = getParameters();
        List<String> list = params.getRaw();
        if (list.size() == 1){
            File file = new File(list.get(0));
            Companion companion = new Companion();
            companion.argOpen(file);
        }
        Parent root = FXMLLoader.load(getClass().getResource("uml.fxml"));
        primaryStage.setTitle("UML creator");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
