package uml;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sander on 04/04/17.
 * this class controls the relation view
 */
public class RelationView {
    private final AnchorPane anchorpane;
    private final AnchorPane arrowPane;
    private final AnchorPane headpane;

    public RelationView(AnchorPane anchorpane, AnchorPane arrowPane, AnchorPane headpane){
        this.anchorpane = anchorpane;
        this.arrowPane = arrowPane;
        this.headpane = headpane;
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
            /**
             * target will have the origin of the arrow
             * while the boxModel will have the arrow head
             */
            if (boxModel.getRelations() != null) {
                for (Relation relation : boxModel.getRelations()
                        ) {
                    Line line = new Line();
                    double x1 = boxModel.getCol();
                    line.setStartX(x1 + boxModel.getWidth() / 2);
                    double y1 = boxModel.getRow();
                    line.setStartY(y1 + boxModel.getHeight() / 2);
                    for (PageBox target : boxes
                            ) {
                        if (relation.getWith().equals(target.getModel().getName())) {
                            double x2 = target.getModel().getCol();
                            line.setEndX(x2 + target.getModel().getWidth() / 2);
                            double y2 = target.getModel().getRow();
                            line.setEndY(y2 + target.getModel().getHeight() / 2);
                            //works in circle
                            if (x2 < x1 && y2>=y1) {
                                placeArrowHead(target.getModel(), boxModel);
                            }
                            //works in circle
                            /**
                             * TODO:
                             * make the value evaluate for
                              * absolute.
                             * fix for non squares
                             * true for values where m would go above
                             * the corner etc
                             */
                            if (x2 > x1 && y2 > y1 && Math.abs((y2-y1)/(x2-x1))>1){
                                placeArrowHead2(boxModel, target.getModel());
                            }

                            if (x2 > x1 && y2 >= y1 && ((y2-y1)/(x2-x1))<1){
                                placeArrowHead3(boxModel, target.getModel());
                            }

                            if (x2 > x1 && y2 < y1 && ((y2-y1)/(x2-x1))<1){}

                            if (x2 < x1 && y2 < y1 && ((y2-y1)/(x2-x1))>1){}

                            if (x2 < x1 && y2 < y1 && ((y2-y1)/(x2-x1))<1){}

                        }
                    }
                    line.setId(relation.getType().toLowerCase());
                    arrowPane.getChildren().add(line);
                }
            }
        }
    }
    //option 1 is bad
    private void placeArrowHead(VBoxModel oorsprong, VBoxModel staart){
        Circle circle = new Circle();
        circle.setRadius(5);
        double x1 = oorsprong.getCol()+oorsprong.getWidth()/2;
        double x2 = staart.getCol() + staart.getWidth()/2;
        double y1 = oorsprong.getRow() + oorsprong.getHeight()/2;
        double y2 = staart.getRow() + staart.getHeight()/2;
        double w = oorsprong.getWidth();
        double m = ((y2-y1)/(x2-x1));
        double x = x1 + (w/2);
        double y = ((x-x1)*m) + y1;
        circle.setCenterY(y);
        circle.setCenterX(x);
        headpane.getChildren().add(circle);
    }
    //x2 > x1 && y2 > y1
    private void placeArrowHead2(VBoxModel oorspong, VBoxModel staart){
        Circle circle = new Circle();
        circle.setRadius(5);
        double x1 = oorspong.getCol() + oorspong.getWidth()/2;
        double x2 = staart.getCol() + staart.getWidth()/2;
        double y1 = oorspong.getRow() + oorspong.getHeight()/2;
        double y2 = staart.getRow() + staart.getHeight()/2;
        //y is juist
        double y = staart.getRow();
        double m = ((y2-y1)/(x2-x1));
        double x = ((y - y1)/m) + x1;
        circle.setCenterX(x);
        circle.setCenterY(y);
        headpane.getChildren().add(circle);
    }
    private void placeArrowHead3(VBoxModel oorspong, VBoxModel staart){
        //oorspring = x1 y1
        //staart = x2 y2
        //x2 > x1 && y2 < y1 && ((y2-y1)/(x2-x1))<1
        double x1 = oorspong.getCol() + oorspong.getWidth()/2;
        double x2 = staart.getCol() + staart.getWidth()/2;
        double y1 = oorspong.getRow() + oorspong.getHeight()/2;
        double y2 = staart.getRow() + staart.getHeight()/2;
        double m  = ((y2-y1)/(x2-x1));

    }
}
