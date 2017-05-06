package uml.views;

import javafx.scene.Node;
import javafx.scene.control.Label;


/**
 * Created by Sander on 6/05/2017.
 */
public class AttributeLabel extends Label {
    public AttributeLabel(String text) {
        super(text);
    }

    public AttributeLabel(){}

    public AttributeLabel(String text, Node graphic) {
        super(text, graphic);
    }
}
