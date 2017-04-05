package uml;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sander on 04/04/17.
 * this file is the view for the boxes
 */
public class BoxView {
    private AnchorPane anchorpane;
    public BoxView(File file){}
    public BoxView(AnchorPane anchorpane) {
        this.anchorpane = anchorpane;
    }


    public void setBoxes(Diagram diagram) {

        for (Box box : diagram.getList()
                ) {
            //sets the model for the box
            VBoxModel model = new VBoxModel(box);
            PageBox newVbox = new PageBox(4);
            newVbox.setModel(model);
            newVbox.setPrefWidth(model.getWidth());
            newVbox.setId("VBox");


            //adds the name to the box and adds a seperator below the name
            setTitle(newVbox, model);
            addSeperator(newVbox);

            //adds the attributes and adds a seperator below the attributes

            AttributeView attributeView = new AttributeView(newVbox, model);
            addToPlane(attributeView.addAtt(), model.getRow(), model.getCol());

            //adds a separator
            addSeperator(newVbox);


            //adds the final operations in the bottom of the vbox
            OperationView operationView = new OperationView(newVbox, model);
            operationView.addOperations();
            //fix this call somehow

        }
    }
    private void setTitle(PageBox newVbox, VBoxModel model){
        Label title = new Label(model.getName());
        newVbox.getChildren().add(title);
        title.setPrefWidth(model.getWidth());
        title.setId("topTitle");

    }

    private void addSeperator(PageBox newVbox){
        Separator separator = new Separator();
        separator.setId("separator");
        newVbox.getChildren().add(separator);
    }

    private void addToPlane(PageBox vbox, Double row, Double col) {
        anchorpane.getChildren().add(vbox);
        AnchorPane.setTopAnchor(vbox, row);
        AnchorPane.setLeftAnchor(vbox, col);
    }
    public static String getVis(String string){
        Map<String, String> visibilty = new HashMap<>();
        visibilty.put("private", "-");
        visibilty.put("public", "+");
        visibilty.put("protected", "#");
        visibilty.put("derived", "/");
        visibilty.put("package", "~");
        return visibilty.get(string);
    }
}
