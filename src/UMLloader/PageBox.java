package UMLloader;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Created by sander on 01/04/17.
 */
public class PageBox extends VBox {
    private int xCor;
    private int yCor;
    private AnchorPane anchorPane;
    private VBoxModel model;

    public int getxCor() {
        return xCor;
    }

    public void setxCor(int xCor) {
        this.xCor = xCor;
    }

    public int getyCor() {
        return yCor;
    }

    public void setyCor(int yCor) {
        this.yCor = yCor;
    }

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
