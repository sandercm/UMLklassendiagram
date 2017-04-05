package uml;

import javafx.scene.control.Label;

/**
 * Created by sander on 04/04/17.
 * this class controls the attribute view
 */
public class AttributeView {
    private PageBox vBox;
    private VBoxModel boxModel;
    private String vis;

    public AttributeView(PageBox vBox, VBoxModel boxModel) {
        this.vBox = vBox;
        this.boxModel = boxModel;
    }
    public AttributeView(Operation operation){

    }

    public PageBox addAtt() {
        if (boxModel.getAttributes() != null) {
            for (Attribute att : boxModel.getAttributes()
                    ) {
                if (att.getVisibility().equals("private")){
                    vis = "-";
                }
                else{
                    vis = "+";
                }
                vBox.getChildren().add(new Label(vis +att.getName() + " : " +att.getType()));
            }
        }
        return vBox;
    }
}
