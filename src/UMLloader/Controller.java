package UMLloader;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.stage.FileChooser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    public BorderPane borderpane;
    public AnchorPane anchorpane;

    public void closeProgram(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void closeImage(ActionEvent actionEvent) {
    }

    /**
     *
     * @param actionEvent
     * @throws FileNotFoundException
     * @throws JAXBException
     * program stucture
     * main: Diagram -> boxes
     * boxes -> relations, attributes and operations
     * operations -> attributes
     * attributes no list of subclasses
     * relations no list of subclasses
     */
    public void open(ActionEvent actionEvent) throws FileNotFoundException, JAXBException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files", "*.xml"));
        File file = chooser.showOpenDialog(borderpane.getScene().getWindow());
        Unmarshal un = new Unmarshal();
        Diagram diagram = un.unmarshaller(file);
        for (int i = 0; i < diagram.getList().size(); i++){
            System.out.println(diagram.getList().get(i));
            VBox newBox = new VBox();
            Label label = new Label("this is a test");
            newBox.getChildren().add(label);
            anchorpane.getChildren().add(newBox);
            anchorpane.setTopAnchor(newBox, (double)diagram.getList().get(i).getRow());
            anchorpane.setLeftAnchor(newBox, (double)diagram.getList().get(i).getCol());
        }
    }
}