package UMLloader;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import javax.smartcardio.ATR;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Companion {
    public BorderPane borderpane;
    public AnchorPane anchorpane;

    public void closeProgram(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void closeImage(ActionEvent actionEvent) {
        for (int i =0; i <anchorpane.getChildren().toArray().length; i++){

            anchorpane.getChildren().remove(0, anchorpane.getChildren().toArray().length);
        }
    }

    /**
     * @param actionEvent
     * @throws FileNotFoundException
     * @throws JAXBException         program stucture
     *                               main: Diagram -> boxes
     *                               boxes -> relations, attributes and operations
     *                               operations -> attributes
     *                               attributes no list of subclasses
     *                               relations no list of subclasses
     */
    public void open(ActionEvent actionEvent) throws FileNotFoundException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files", "*.xml"));
        File file = chooser.showOpenDialog(borderpane.getScene().getWindow());
        Unmarshal un = new Unmarshal();
        //unmarshalling copypasta
        Diagram diagram = null;
        try {
            diagram = un.unmarshaller(file);
        } catch (JAXBException e) {
            System.out.println("JAXB, unmarshalling error");
        }
        BoxController boxController = new BoxController(diagram, anchorpane);
        boxController.setBoxes();
    }

    /*TODO
       * create the other objects and link them
               * create the arrow
               * create a border around the boxers
               * create lines for the attributes
     */

}