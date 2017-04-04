package UMLloader;

import javafx.scene.control.Label;
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
            VBoxModel model = new VBoxModel(box);
            PageBox newVbox = new PageBox();
            newVbox.setModel(model);

            newVbox.getChildren().add(new Label(model.getName()));
            //adds the attributes
            newVbox.getStyleClass().add("UMLloader/sample.css");
            newVbox.setId("VBox");
            AttributeView attributeView = new AttributeView(newVbox, model);
            addToPlane(attributeView.addAtt(), model.getRow(), model.getCol());
            //fix anchorpane covering menubar
        }
    }

    private void addToPlane(VBox vbox, Double row, Double col) {
        anchorpane.getChildren().addAll(vbox);
        AnchorPane.setTopAnchor(vbox, row);
        AnchorPane.setLeftAnchor(vbox, col);
    }
}
