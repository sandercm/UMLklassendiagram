package uml.views;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import uml.FXML.Diagram;
import uml.FXML.Unmarshaller;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Companion {
    public BorderPane borderpane;
    public AnchorPane anchorpane;
    public AnchorPane arrowPane;
    public Pane containerpane;
    public AnchorPane headpane;

    private File file = null;

    public void setFile(File file) {
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
        //TODO: clean this up
        anchorpane.getChildren().clear();
        headpane.getChildren().clear();
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
        RelationView relationView = new RelationView(anchorpane, arrowPane, headpane);
        relationView.placeArrows();
        borderpane.toFront();
    }

    public void closeImage(ActionEvent actionEvent) {
        anchorpane.getChildren().clear();
        arrowPane.getChildren().clear();
        headpane.getChildren().clear();
    }

    public void closeProgram(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void drawArg() {
        anchorpane.getStylesheets().add("uml/uml.css");
        arrowPane.getStylesheets().add("uml/uml.css");
        Unmarshaller unmarshaller = new Unmarshaller();
        Diagram diagram = unmarshaller.unmarshall(file);
        BoxView boxview = new BoxView(anchorpane);
        boxview.setBoxes(diagram);
        //this passes the anchorpane with vboxes in it to the relationview
        RelationView relationView = new RelationView(anchorpane, arrowPane, headpane);
        relationView.placeArrows();

    }

    @FXML
    public void takeScreenshot(String path) throws IOException {
        containerpane.applyCss();
        containerpane.layout();
        WritableImage snapshot = containerpane.getScene().snapshot(null);
        BufferedImage outputImage = SwingFXUtils.fromFXImage(snapshot, null);
        File outFile = new File(path + "/" + "screenshot.png");
        ImageIO.write(outputImage, "png", outFile);

    }


    @FXML
    public static void updateName(PageBox pageBox) {


        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText(null);
        inputDialog.setTitle("new name");
        inputDialog.setContentText("enter new name: ");
        Optional<String> string = inputDialog.showAndWait();
        string.ifPresent(name -> {
            for (Node node: pageBox.getChildren()
                    ) {
                if(node instanceof TitleLabel){
                    ((TitleLabel) node).setText(name);
                }
            }
        });
    }
    @FXML
    static void updateAttributes(PageBox pageBox){
        StringBuilder attribute = new StringBuilder();
        List<String> choices = new ArrayList<>();
        choices.add("public");
        choices.add("private");
        choices.add("protected");
        choices.add("package");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("public", choices);
        dialog.setTitle("scope");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose your scope:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(letter -> {
            String vis = BoxView.getVis(letter);
            attribute.append(vis);
        });
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setHeaderText(null);
        nameDialog.setTitle("new name");
        nameDialog.setContentText("Choose your name: ");
        Optional<String> string = nameDialog.showAndWait();
        string.ifPresent(name -> attribute.append(name).append(" : "));
        TextInputDialog typeDialog = new TextInputDialog();
        typeDialog.setHeaderText(null);
        typeDialog.setTitle("new type");
        typeDialog.setContentText("Choose your type: ");
        Optional<String> type = typeDialog.showAndWait();
        type.ifPresent(text -> {
            attribute.append(text).append("\n");
        });
        //attributes.append(vis).append(att.getName()).append(" : ").append(att.getType()).append("\n")
        for (Node node : pageBox.getChildren()
             ) {
            if(node instanceof AttributeLabel){
                ((AttributeLabel) node).setText(((AttributeLabel) node).getText() + attribute);
            }
        }
    }
    @FXML
    static void updateOperations(PageBox pageBox){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Does the operations have sub attributes");
        alert.setHeaderText(null);
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("yes");
        ButtonType buttonTypeTwo = new ButtonType("no");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent( bool -> {
            if ("yes".equals(bool.getText())){
                System.out.println("kek");
                //TODO:IMPLEMENT THIS CRAP
            }
            else if ("no".equals(bool.getText())){
                StringBuilder operation = new StringBuilder();
                List<String> choices = new ArrayList<>();
                choices.add("public");
                choices.add("private");
                choices.add("protected");
                choices.add("package");
                ChoiceDialog<String> dialog = new ChoiceDialog<>("public", choices);
                dialog.setTitle("scope");
                dialog.setHeaderText(null);
                dialog.setContentText("Choose your scope:");
                Optional<String> op = dialog.showAndWait();
                op.ifPresent(letter -> {
                    String vis = BoxView.getVis(letter);
                    operation.append(vis);
                });
                TextInputDialog nameDialog = new TextInputDialog();
                nameDialog.setHeaderText(null);
                nameDialog.setTitle("new name");
                nameDialog.setContentText("Choose your name: ");
                Optional<String> string = nameDialog.showAndWait();
                string.ifPresent(name -> operation.append(name).append(" : "));
                TextInputDialog typeDialog = new TextInputDialog();
                typeDialog.setHeaderText(null);
                typeDialog.setTitle("new type");
                typeDialog.setContentText("Choose your type: ");
                Optional<String> type = typeDialog.showAndWait();
                type.ifPresent(text -> {
                    operation.append(text).append("\n");
                });
                for (Node node : pageBox.getChildren()
                        ) {
                    if(node instanceof OperationLabel){
                        ((OperationLabel) node).setText(((OperationLabel) node).getText() + operation);
                    }
                }

            }
        });
    }
}