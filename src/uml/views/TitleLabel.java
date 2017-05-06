package uml.views;

import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * Created by Sander on 6/05/2017.
 */
public class TitleLabel extends Label{



    public TitleLabel(String text) {
        super(text);
    }

    public TitleLabel(){}

    public TitleLabel(String text, Node graphic) {
        super(text, graphic);
    }

}
