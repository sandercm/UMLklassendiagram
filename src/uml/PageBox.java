package uml;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Created by sander on 01/04/17.
 */
public class PageBox extends VBox {
    private AnchorPane anchorPane;
    private VBoxModel model;

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public PageBox() {
    }

    public PageBox(int spacing) {
        super(spacing);
    }



    public void setModel(VBoxModel model) {
        this.model = model;
    }

    public VBoxModel getModel() {
        return model;
    }

}
