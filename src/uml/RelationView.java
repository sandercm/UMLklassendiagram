package uml;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sander on 04/04/17.
 * this class controls the relation view
 */
public class RelationView {
    public AnchorPane anchorpane;
    public AnchorPane arrowPane;
    public BorderPane arrowborderpane;
    public RelationView(AnchorPane anchorpane){
        this.anchorpane = anchorpane;
    }
    public RelationView(AnchorPane anchorpane, AnchorPane arrowPane){
        this.anchorpane = anchorpane;
        this.arrowPane = arrowPane;
    }


    private List<PageBox> createObjList(AnchorPane anchorPane){

        List<PageBox> boxes = new ArrayList<>();
        anchorpane.applyCss();
        anchorpane.layout();
        for (Object obj: anchorpane.getChildren()
                ) {
            if (obj instanceof PageBox){
                boxes.add((PageBox) obj);

                ((PageBox) obj).getModel().setHeight(((PageBox) obj).getHeight());
            }
        }
        return boxes;
    }
    public void placeArrows(){
        //creates an array list of the boxes currently in the anchorplane
        //TODO: check if I can remove this loop
        List<PageBox> boxes = createObjList(anchorpane);
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
                    line.setId(relation.getType().toLowerCase());
                    arrowPane.getChildren().add(line);
                }
            }
        }
    }
}
