package UMLloader;

import javafx.scene.control.Label;

/**
 * Created by sander on 04/04/17.
 */
public class AttributeView {
    private PageBox vBox;
    private VBoxModel boxModel;

    public AttributeView(PageBox vBox, VBoxModel boxModel) {
        this.vBox = vBox;
        this.boxModel = boxModel;
    }

    public PageBox addAtt() {
        if (boxModel.getAttributes() != null) {
            for (Attribute att : boxModel.getAttributes()
                    ) {
                vBox.getChildren().add(new Label(att.getName()));
            }
        }
        return vBox;
    }
}
