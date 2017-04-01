package UMLloader;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Created by sander on 31/03/17.
 */
public class BoxController {
    public Diagram diagram;
    public AnchorPane anchorPane;
    public BoxController(Diagram diagram, AnchorPane anchorPane) {
        this.diagram = diagram;
        this.anchorPane = anchorPane;
    }
    public void setBoxes() {
        for (int i = 0; i < diagram.getList().size(); i++) {
            System.out.println(diagram.getList().get(i));
            Box box = diagram.getList().get(i);
            //creats Vbox objectr
            VBox newBox = new VBox();
            //creates lavel for the Vbox
            Label label = new Label(box.getName());
            //creates the label list with attributes
            //adds the label to the Vbox
            newBox.getChildren().add(label);
            newBox.setPrefWidth((double) box.getWidth());
            //gets the attributes for the Box
            List<Attribute> attributes = box.getAttributes();
            if (box.getAttributes() != null) {
                setAttributes(attributes, newBox);
            }
            setCSS(newBox);
            //adds the Box to the anchorpane
            addToPlane(newBox, (double) box.getRow(), (double) box.getCol());
        }
    }
    private void addToPlane(VBox vbox, Double row, Double col) {
        anchorPane.getChildren().addAll(vbox);
        AnchorPane.setTopAnchor(vbox, row);
        AnchorPane.setLeftAnchor(vbox, col);
    }
    private void setCSS(VBox vbox) {
        /**TODO
         * link this css to a file instaid of hardcoded in line
         */

        String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n";
        vbox.setStyle(cssLayout);
    }
    private void setAttributes(List<Attribute> attributes, VBox vBox) {
        for (int i = 0; i < attributes.size(); i++) {
            Label label = new Label(attributes.get(i).getName());
            vBox.getChildren().add(label);
        }
    }
}
