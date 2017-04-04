package UMLloader;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class Companion {
    public BorderPane borderpane;
    public AnchorPane anchorpane;


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

    public void open(ActionEvent actionEvent) {
        BoxView boxview = new BoxView(anchorpane);
        boxview.setBoxes();
        //this passes the anchorpane with vboxes in it to the relationview
        RelationView relationView = new RelationView(anchorpane);

    }

    public void closeImage(ActionEvent actionEvent) {
        anchorpane.getChildren().clear();
    }

    public void closeProgram(ActionEvent actionEvent) {
        Platform.exit();
    }

}