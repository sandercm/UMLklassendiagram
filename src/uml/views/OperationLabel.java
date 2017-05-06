package uml.views;


import javafx.scene.Node;
import javafx.scene.control.Label;
import uml.FXML.Operation;

/**
 * Created by Sander on 6/05/2017.
 */
public class OperationLabel extends Label {
    public OperationLabel(String text) {
        super(text);
    }

    public OperationLabel(){}

    public OperationLabel(String text, Node graphic) {
        super(text, graphic);
    }
}
