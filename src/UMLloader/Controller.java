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
    public void open(ActionEvent actionEvent) throws FileNotFoundException{
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files", "*.xml"));
        File file = chooser.showOpenDialog(borderpane.getScene().getWindow());
        Unmarshal un = new Unmarshal();
        //unmarshalling copypasta
        Diagram diagram = null;
        try {
            diagram = un.unmarshaller(file);
        } catch (JAXBException e) {
            System.out.println("JAXB, unmarshalling error");;
        }
        setBoxes(diagram);
    }
    /*TODO
       * create the other objects and link them
               * create the arrow
               * create a border around the boxers
               * create lines for the attributes
     */
    private void setBoxes(Diagram diagram){
        for (int i = 0; i < diagram.getList().size(); i++){
            System.out.println(diagram.getList().get(i));
            //creats Vbox objectr
            VBox newBox = new VBox();
            //creates lavel for the Vbox
            Label label = new Label(diagram.getList().get(i).getName());
            //adds the label to the Vbox
            newBox.getChildren().add(label);
            newBox.setPrefWidth((double)diagram.getList().get(i).getWidth());
            //String containing the css to add a border
            //should find a better way to do this
            String cssLayout = "-fx-border-color: black;\n" +
                    "-fx-border-insets: 5;\n" +
                    "-fx-border-width: 3;\n";
            //links the css to the box+
            newBox.setStyle(cssLayout);
            //adds the box to the anchorpane
            anchorpane.getChildren().add(newBox);
            //sets the location for the box on the plane
            AnchorPane.setTopAnchor(newBox, (double)diagram.getList().get(i).getRow());
            AnchorPane.setLeftAnchor(newBox, (double)diagram.getList().get(i).getCol());
        }
    }
}