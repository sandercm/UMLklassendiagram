package uml;

import javafx.scene.control.Label;

/**
 * Created by sander on 04/04/17.
 * this class controls the attribute view
 */
public class AttributeView {
    private final PageBox vBox;
    private final VBoxModel boxModel;

    public AttributeView(PageBox vBox, VBoxModel boxModel) {
        this.vBox = vBox;
        this.boxModel = boxModel;
    }


    public PageBox addAtt() {
        if (boxModel.getAttributes() != null) {
            StringBuilder attributes = new StringBuilder();
            for (Attribute att : boxModel.getAttributes()
                    ) {
                /**
                 * #	Protected
                 * /	Derived (can be combined with one of the others)
                 * ~	Package
                 */
                String vis = BoxView.getVis(att.getVisibility());
                attributes.append(vis).append(att.getName()).append(" : ").append(att.getType()).append("\n");


            }
            Label label = new Label(attributes.toString());
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
