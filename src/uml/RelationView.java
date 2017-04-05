package uml;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sander on 04/04/17.
 * this class controls the relation view
 */
public class RelationView {
    AnchorPane anchorpane;
    public RelationView(AnchorPane anchorpane){
        this.anchorpane = anchorpane;
    }
    public void placeArrows(){
        List<PageBox> boxes = new ArrayList<>();
        //creates an array list of the boxes currently in the anchorplane
        //TODO: check if I can remove this loop
        anchorpane.applyCss();
        anchorpane.layout();
        for (Object obj: anchorpane.getChildren()
                ) {
            if (obj instanceof PageBox){
                boxes.add((PageBox) obj);

                ((PageBox) obj).getModel().setHeight(((PageBox) obj).getHeight());
            }
        }
        for (PageBox box: boxes
                ) {
            VBoxModel boxModel = box.getModel();
            if (boxModel.getRelations() != null) {
                for (Relation relation : boxModel.getRelations()
                        ) {
                    Line line = new Line();
                    line.setStartX(boxModel.getCol() + boxModel.getWidth()/2);
                    line.setStartY(boxModel.getRow() + boxModel.getHeight()/2);
                    for (PageBox target : boxes
                            ) {
                        if (relation.getWith().equals(target.getModel().getName())) {
                            line.setEndX(target.getModel().getCol() + boxModel.getWidth()/2);
                            line.setEndY(target.getModel().getRow() + target.getModel().getHeight()/2);
                        }
                    }
                    anchorpane.getChildren().add(line);
                    line.setId("relations");
                }
            }
        }
    }


}
