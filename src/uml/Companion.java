package uml;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Companion {
    public BorderPane borderpane;
    public AnchorPane anchorpane;
    public AnchorPane arrowPane;
    public Pane containerpane;

    public File file = null;
    public void setFile(File file){
        this.file = file;
    }


    public File getFile() {
        return file;
    }

    /**
     * @throws FileNotFoundException
     * @throws JAXBException         program stucture
     *                               main: Diagram -> boxes
     *                               boxes -> relations, attributes and operations
     *                               operations -> attributes
     *                               attributes no list of subclasses
     *                               relations no list of subclasses
     */

    public void open(ActionEvent actionEvent) {
        //TODO: clean this up and implement argument passing
        anchorpane.getChildren().clear();

        arrowPane.getChildren().clear();
        anchorpane.getStylesheets().add("uml/uml.css");
        arrowPane.getStylesheets().add("uml/uml.css");

        Unmarshaller unmarshaller = new Unmarshaller();
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files", "*.xml"));
        File file = chooser.showOpenDialog(null);

        Diagram diagram = unmarshaller.unmarshall(file);
        BoxView boxview = new BoxView(anchorpane);
        boxview.setBoxes(diagram);
        //this passes the anchorpane with vboxes in it to the relationview
        RelationView relationView = new RelationView(anchorpane, arrowPane);
        relationView.placeArrows();
        borderpane.toFront();
    }

    public void closeImage(ActionEvent actionEvent) {
        anchorpane.getChildren().clear();
        arrowPane.getChildren().clear();
    }

    public void closeProgram(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void drawArg(){
        anchorpane.getStylesheets().add("uml/uml.css");
        arrowPane.getStylesheets().add("uml/uml.css");
        Unmarshaller unmarshaller = new Unmarshaller();
        Diagram diagram = unmarshaller.unmarshall(file);
        BoxView boxview = new BoxView(anchorpane);
        boxview.setBoxes(diagram);
        //this passes the anchorpane with vboxes in it to the relationview
        RelationView relationView = new RelationView(anchorpane, arrowPane);
        relationView.placeArrows();

    }
    @FXML
    public void takeScreenshot(String path) throws IOException {
        containerpane.applyCss();
        containerpane.layout();
        WritableImage snapshot = containerpane.getScene().snapshot(null);
        BufferedImage outputImage = SwingFXUtils.fromFXImage(snapshot, null);
        File outFile = new File(path + "/" + "screenshot.png");
        ImageIO.write(outputImage,"png", outFile);

    }


}