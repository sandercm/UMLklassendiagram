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

    public PageBox addAtt() {
        if (boxModel.getAttributes() != null) {
            String attributes = "";
            for (Attribute att : boxModel.getAttributes()
                    ) {
                if (att.getVisibility().equals("private")){
                    vis = "-";
                }
                else{
                    vis = "+";
                }
                attributes += vis +att.getName() + " : " +att.getType() + "\n";


            }
            Label label = new Label(attributes);
            label.setId("attributes");
            vBox.getChildren().add(label);
        }
        else{
            Label label = new Label("");
            vBox.getChildren().add(label);
            label.setId("attributes");
        }
        return vBox;
    }
}
