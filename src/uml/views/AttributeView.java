package uml.views;

import uml.FXML.Attribute;
import uml.VBoxModel;

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
            AttributeLabel label = new AttributeLabel(attributes.toString());
            label.setId("attributes");
            vBox.getChildren().add(label);
        }
        else{
            AttributeLabel label = new AttributeLabel("");
            vBox.getChildren().add(label);
            label.setId("attributes");
        }
        return vBox;
    }
}
