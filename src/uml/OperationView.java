package uml;

import javafx.scene.control.Label;

/**
 * Created by sander on 04/04/17.
 * controls the view for the operations
 */
public class OperationView {
    private PageBox vBox;
    private VBoxModel boxModel;

    public OperationView(PageBox vBox, VBoxModel boxModel) {
        this.vBox = vBox;
        this.boxModel = boxModel;
    }

    public PageBox addOperations() {
        if (boxModel.getOperations() != null) {
            for (Operation opt : boxModel.getOperations()
                    ) {
                String vis;
                if (opt.getVisibility().equals("private")){
                    vis = "-";
                }
                else{
                    vis = "+";
                }
                vBox.getChildren().add(new Label(vis + opt.getName() + " : " + opt.getType()));
            }
        }
        return vBox;
    }
}
