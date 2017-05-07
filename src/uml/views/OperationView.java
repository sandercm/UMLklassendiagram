package uml.views;

import uml.FXML.Attribute;
import uml.FXML.Operation;
import uml.VBoxModel;

/**
 * Created by sander on 04/04/17.
 * controls the view for the operations
 */
public class OperationView {
    private final PageBox vBox;
    private final VBoxModel boxModel;

    public OperationView(PageBox vBox, VBoxModel boxModel) {
        this.vBox = vBox;
        this.boxModel = boxModel;

    }

    public PageBox addOperations() {
        if (boxModel.getOperations() != null) {
            StringBuilder attributestring = new StringBuilder();
            for (Operation opt : boxModel.getOperations()
                    ) {

                String vis = BoxView.getVis(opt.getVisibility());

                if (opt.getAttributes() != null){
                    for (Attribute att:opt.getAttributes()
                         ) {
                        String attribute = "(" + att.getName() + " : " + att.getType() + ")";
                        attributestring.append(vis).append(opt.getName()).append(attribute).append(" : ").append(opt.getType()).append("\n");
                    }

                }
                else {

                    attributestring.append(vis).append(opt.getName()).append(" : ").append(opt.getType()).append("\n");
                }
            }
            OperationLabel label = new OperationLabel(attributestring.toString());
            vBox.getChildren().add(label);
            label.setId("operation");
        }
        else {
            OperationLabel label = new OperationLabel("");
            vBox.getChildren().add(label);
            label.setId("operation");
        }

        return vBox;
    }
}
