package UMLloader;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class Companion {
    public BorderPane borderpane;
    public AnchorPane anchorpane;
    public VBoxModel model;
    private Unmarshaller unmarshaller;
    private Diagram diagram;


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
        unmarshaller = new Unmarshaller();
        diagram = unmarshaller.unmarshall();
        //BoxController boxController = new BoxController(diagram, anchorpane);
        //boxController.setBoxes();
        //model = new VBoxModel();
        for (Box box : diagram.getList()
                ) {
            model = new VBoxModel(box);
            PageBox newVbox = new PageBox();
            newVbox.setModel(model);
            newVbox.getChildren().add(new Label(model.getName()));
            addToPlane(newVbox, (double) model.getRow(), (double) model.getCol());
            //fix anchorpane covering menubar
        }
    }

    public void addToPlane(VBox vbox, Double row, Double col) {
        anchorpane.getChildren().addAll(vbox);
        AnchorPane.setTopAnchor(vbox, row);
        AnchorPane.setLeftAnchor(vbox, col);
    }


    public void closeImage(ActionEvent actionEvent) {
        anchorpane.getChildren().clear();
    }

    public void closeProgram(ActionEvent actionEvent) {
        Platform.exit();
    }

}