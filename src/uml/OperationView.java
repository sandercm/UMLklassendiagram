package uml;

/**
 * Created by sander on 04/04/17.
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

            }
        }
        return vBox;
    }
}
