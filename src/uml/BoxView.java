package uml;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Created by sander on 04/04/17.
 * this file is the view for the boxes
 */
public class BoxView {
    private AnchorPane anchorpane;

    public BoxView(AnchorPane anchorpane) {
        this.anchorpane = anchorpane;
    }

    public void setBoxes() {
        Unmarshaller unmarshaller = new Unmarshaller();
        Diagram diagram = unmarshaller.unmarshall();
        for (Box box : diagram.getList()
                ) {
            //sets the model for the box
            VBoxModel model = new VBoxModel(box);
            PageBox newVbox = new PageBox();
            newVbox.setModel(model);
            //adds the name to the box and adds a seperator below the name
            newVbox.getChildren().add(new Label(model.getName()));
            newVbox.getChildren().add(new Separator());
            //adds the attributes and adds a seperator below the attributes
            newVbox.getStyleClass().add("uml/uml.css");
            newVbox.setId("VBox");
            AttributeView attributeView = new AttributeView(newVbox, model);
            addToPlane(attributeView.addAtt(), model.getRow(), model.getCol());
            newVbox.getChildren().add(new Separator());
            //adds the final operations in the bottom of the vbox
            OperationView operationView = new OperationView(newVbox, model);
            operationView.addOperations();
        }
    }

    private void addToPlane(VBox vbox, Double row, Double col) {
        anchorpane.getChildren().addAll(vbox);
        AnchorPane.setTopAnchor(vbox, row);
        AnchorPane.setLeftAnchor(vbox, col);
    }
}
