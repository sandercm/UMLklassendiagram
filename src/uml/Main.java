package uml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uml.views.Companion;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main extends Application {

    public File file;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("uml.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("error loading file");
        }
        Companion comp = loader.getController();
        Parameters params = getParameters();
        List<String> list = params.getRaw();
        /**
         * there is a bug with opening from command line where something
         * messes up the the cor of the boxes
         * TODO:fix this
         */
        if (list.size() == 1) {
            file = new File(list.get(0));
            comp.setFile(file);
            comp.drawArg();
        }
        primaryStage.setTitle("UML creator");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
        if (list.size() == 2) {
            file = new File(list.get(0));
            comp.setFile(file);
            comp.drawArg();
            comp.takeScreenshot(list.get(1));
            primaryStage.close();
        }


    }


}
