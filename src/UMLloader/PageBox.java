package UMLloader;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Created by sander on 01/04/17.
 */
public class PageBox extends VBox {
    public AnchorPane anchorPane;
    public PageBox(){}
    public PageBox(int spacing){
        super(spacing);
    }
    private VBoxModel model;
     public void setModel(VBoxModel model){
         this.model = model;
     }
     public VBoxModel getModel(){
         return model;
     }

}
