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
                    //boxmodel is the origin of the arrow
                    Line line = new Line();
                    double x1 = boxModel.getCol();
                    line.setStartX(x1 + boxModel.getWidth() / 2);
                    double y1 = boxModel.getRow();
                    line.setStartY(y1 + boxModel.getHeight() / 2);
                    for (PageBox target : boxes
                            ) {
                        if (relation.getWith().equals(target.getModel().getName())) {
                            double x2 = target.getModel().getCol();
                            double y2 = target.getModel().getRow();

                            /**
                             * first it takes the increase per box width
                             * then it compares the cor of the 2 boxes
                             * then it looks of the increase is higher or lower than the boxRatio
                             * to determine if it goes above or below the corner
                             */
                            //TODO:PUT THIS IN A FACTORY STRUCTURE
                            double boxRatio = target.getModel().getHeight()/target.getModel().getWidth();

                            if (x2 < x1 && y2>=y1) {
                                PointTuple punten = placeArrowHead(target.getModel(), boxModel);
                                line.setEndX(punten.getX());
                                line.setEndY(punten.getY());
                            }
                            if (x2 > x1 && y2 >= y1 && Math.abs((y2-y1)/(x2-x1))>boxRatio){
                                PointTuple punten = placeArrowHead2(boxModel, target.getModel());
                                line.setEndX(punten.getX());
                                line.setEndY(punten.getY());
                            }

                            if (x2 > x1 && y2 >= y1 && Math.abs(((y2-y1)/(x2-x1)))<boxRatio){
                                PointTuple punten = placeArrowHead3(boxModel, target.getModel());
                                line.setEndX(punten.getX());
                                line.setEndY(punten.getY());
                            }

                            if (x2 > x1 && y2 < y1 && Math.abs(((y2-y1)/(x2-x1)))<boxRatio){
                                PointTuple punten = placeArrowHead4(boxModel, target.getModel());
                                line.setEndX(punten.getX());
                                line.setEndY(punten.getY());
                            }

                            if (x2 < x1 && y2 < y1 && Math.abs(((y2-y1)/(x2-x1)))>boxRatio){
                                PointTuple punten = placeArrowHead5(boxModel, target.getModel());
                                line.setEndX(punten.getX());
                                line.setEndY(punten.getY());
                            }

                            if (x2 < x1 && y2 < y1 && Math.abs(((y2-y1)/(x2-x1)))<boxRatio){
                                PointTuple punten = placeArrowHead6(boxModel, target.getModel());
                                line.setEndX(punten.getX());
                                line.setEndY(punten.getY());
                            }
                            if (x2 > x1 && y2 < y1 && Math.abs(((y2-y1)/(x2-x1)))>boxRatio){
                                PointTuple punten = placeArrowHead5(boxModel, target.getModel());
                                line.setEndX(punten.getX());
                                line.setEndY(punten.getY());
                            }

                        }
                    }
                    line.setId(relation.getType().toLowerCase());
                    arrowPane.getChildren().add(line);
                }
            }
        }
    }

    /**
     *
     * @param oorsprong
     * where the arrow head appears
     * @param staart
     * the start of the line of the arrow
     * below you will find
     * ungodly math fuckery
     */
    private PointTuple placeArrowHead(VBoxModel oorsprong, VBoxModel staart){
        double x1 = oorsprong.getCol()+oorsprong.getWidth()/2;
        double x2 = staart.getCol() + staart.getWidth()/2;
        double y1 = oorsprong.getRow() + oorsprong.getHeight()/2;
        double y2 = staart.getRow() + staart.getHeight()/2;
        double w = oorsprong.getWidth();
        double m = ((y2-y1)/(x2-x1));
        double x = x1 + (w/2);
        double y = ((x-x1)*m) + y1;
        Circle circle = new Circle(x,y,5);
        headpane.getChildren().add(circle);
        PointTuple punten = new PointTuple(x, y);
        return punten;
    }
    //x2 > x1 && y2 > y1
    private PointTuple placeArrowHead2(VBoxModel oorspong, VBoxModel staart){

        double x1 = oorspong.getCol() + oorspong.getWidth()/2;
        double x2 = staart.getCol() + staart.getWidth()/2;
        double y1 = oorspong.getRow() + oorspong.getHeight()/2;
        double y2 = staart.getRow() + staart.getHeight()/2;
        //y is juist
        double y = staart.getRow();
        double m = ((y2-y1)/(x2-x1));
        double x = ((y - y1)/m) + x1;
        Circle circle = new Circle(x,y,5);
        headpane.getChildren().add(circle);
        PointTuple punten = new PointTuple(x, y);
        return punten;
    }
    private PointTuple placeArrowHead3(VBoxModel oorsprong, VBoxModel staart){
        //oorspring = x1 y1
        //staart = x2 y2
        //x2 > x1 && y2 < y1 && ((y2-y1)/(x2-x1))<1
        double x1 = oorsprong.getCol() + oorsprong.getWidth()/2;
        double x2 = staart.getCol() + staart.getWidth()/2;
        double y1 = oorsprong.getRow() + oorsprong.getHeight()/2;
        double y2 = staart.getRow() + staart.getHeight()/2;
        double m  = ((y2-y1)/(x2-x1));
        double x = staart.getCol();
        double y = ((m * x) - (m * x1)) + y1;
        Circle circle = new Circle(x, y ,5);
        headpane.getChildren().add(circle);
        PointTuple punten = new PointTuple(x, y);
        return punten;

    }
    private PointTuple placeArrowHead4(VBoxModel oorsprong, VBoxModel staart){
        //x2 > x1 && y2 < y1 && ((y2-y1)/(x2-x1))<1
        double x1 = oorsprong.getCol() + oorsprong.getWidth()/2;
        double x2 = staart.getCol() + staart.getWidth()/2;
        double y1 = oorsprong.getRow() + oorsprong.getHeight()/2;
        double y2 = staart.getRow() + staart.getHeight()/2;
        double m  = ((y2-y1)/(x2-x1));
        double x = staart.getCol();
        double y = m*x - m*x1 + y1;
        Circle circle = new Circle(x, y ,5);
        headpane.getChildren().add(circle);
        PointTuple punten = new PointTuple(x, y);
        return punten;
    }
    private PointTuple placeArrowHead5(VBoxModel oorsprong, VBoxModel staart){
        //x2 < x1 && y2 < y1 && ((y2-y1)/(x2-x1))>1
        double x1 = oorsprong.getCol() + oorsprong.getWidth()/2;
        double x2 = staart.getCol() + staart.getWidth()/2;
        double y1 = oorsprong.getRow() + oorsprong.getHeight()/2;
        double y2 = staart.getRow() + staart.getHeight()/2;
        double m  = ((y2-y1)/(x2-x1));
        double y = staart.getRow()+staart.getHeight();
        double x = (y - y1 + (m*x1))/m;
        Circle circle = new Circle(x, y ,5);
        headpane.getChildren().add(circle);
        PointTuple punten = new PointTuple(x, y);
        return punten;
    }
    private PointTuple placeArrowHead6(VBoxModel oorsprong, VBoxModel staart){
        //x2 < x1 && y2 < y1 && ((y2-y1)/(x2-x1))<1
        double x1 = oorsprong.getCol() + oorsprong.getWidth()/2;
        double x2 = staart.getCol() + staart.getWidth()/2;
        double y1 = oorsprong.getRow() + oorsprong.getHeight()/2;
        double y2 = staart.getRow() + staart.getHeight()/2;
        double m  = ((y2-y1)/(x2-x1));
        double x = staart.getCol()+staart.getWidth();
        double y = m*x - m*x1 + y1;
        Circle circle = new Circle(x, y ,5);
        headpane.getChildren().add(circle);
        PointTuple punten = new PointTuple(x, y);
        return punten;
    }
}
