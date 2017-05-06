package uml.views;


import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import uml.FXML.Relation;
import uml.PageBox;
import uml.PointTuple;
import uml.VBoxModel;

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

                    double targetX = 0;
                    double targetY = 0;
                    double x1 = boxModel.getCol();
                    double y1 = boxModel.getRow();
                    double originX = x1 + boxModel.getWidth() / 2;
                    double originY = y1 + boxModel.getHeight() / 2;
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
                            PointTuple punten = new PointTuple(0,0);

                            if (x2 < x1 && y2>=y1) {
                                punten = placeArrowHead(target.getModel(), boxModel);
                                targetX = punten.getX();
                                targetY = punten.getY();
                            }
                            if (x2 > x1 && y2 >= y1 && Math.abs((y2-y1)/(x2-x1))>boxRatio){
                                punten = placeArrowHead2(boxModel, target.getModel());
                                targetX = punten.getX();
                                targetY = punten.getY();
                            }

                            if (x2 > x1 && y2 >= y1 && Math.abs(((y2-y1)/(x2-x1)))<boxRatio){
                                punten = placeArrowHead3(boxModel, target.getModel());
                                targetX = punten.getX();
                                targetY = punten.getY();
                            }

                            if (x2 > x1 && y2 < y1 && Math.abs(((y2-y1)/(x2-x1)))<boxRatio){
                                punten = placeArrowHead4(boxModel, target.getModel());
                                targetX = punten.getX();
                                targetY = punten.getY();
                            }

                            if (x2 < x1 && y2 < y1 && Math.abs(((y2-y1)/(x2-x1)))>boxRatio){
                                punten = placeArrowHead5(boxModel, target.getModel());
                                targetX = punten.getX();
                                targetY = punten.getY();
                            }

                            if (x2 < x1 && y2 < y1 && Math.abs(((y2-y1)/(x2-x1)))<boxRatio){
                                punten = placeArrowHead6(boxModel, target.getModel());
                                targetX = punten.getX();
                                targetY = punten.getY();
                            }
                            if (x2 > x1 && y2 < y1 && Math.abs(((y2-y1)/(x2-x1)))>boxRatio){
                                punten = placeArrowHead5(boxModel, target.getModel());
                                targetX = punten.getX();
                                targetY = punten.getY();
                            }
                        }
                    }
                    Path path = new Path();
                    Point2D begin = new Point2D(originX, originY);
                    Point2D ori = new Point2D(targetX, targetY);
                    //cor of the point 0.1 away from the end
                    //path.setId(relation.getType().toLowerCase());
                    path.getElements().add(new MoveTo(originX, originY));

                    createArrowHeadPath(begin, ori, relation.getType(), path);



                    //arrowPane.getChildren().add(polyline);

                    //arrowPane.getChildren().add(arrow);
                }
            }
        }
    }
    private void createArrowHeadPath(Point2D begin, Point2D ori, String style, Path path){
        /**
         * has to be moved to a factory structure
         * and I might need to replace all arrow path with polylines
         */
        Point2D endIndentCor = eval(begin, ori, 0.9);
        Point2D begincor = perpenIndent(begin,ori,endIndentCor,5);
        Point2D endcor = perpenIndent(begin,ori,endIndentCor,-5);
        Path arrow = new Path();
        if (style.equals("association")){
            arrow.getElements().add(new MoveTo(ori.getX(), ori.getY()));
            arrow.getElements().add(new LineTo(begincor.getX(), begincor.getY()));
            arrow.getElements().add(new MoveTo(ori.getX(), ori.getY()));
            arrow.getElements().add(new LineTo(endcor.getX(), endcor.getY()));
            path.getElements().add(new LineTo(ori.getX(), ori.getY()));
        }
        if (style.equals("inheritance")){
            arrow.getElements().add(new MoveTo(ori.getX(), ori.getY()));
            arrow.getElements().add(new LineTo(begincor.getX(), begincor.getY()));
            arrow.getElements().add(new LineTo(endcor.getX(), endcor.getY()));
            arrow.getElements().add(new LineTo(ori.getX(), ori.getY()));
            path.getElements().add(new LineTo(endIndentCor.getX(), endIndentCor.getY()));
        }
        if (style.equals("realization") || style.equals("implementation")){
            arrow.getElements().add(new MoveTo(ori.getX(), ori.getY()));
            arrow.getElements().add(new LineTo(begincor.getX(), begincor.getY()));
            arrow.getElements().add(new LineTo(endcor.getX(), endcor.getY()));
            arrow.getElements().add(new LineTo(ori.getX(), ori.getY()));
            path.setId("realization");
            path.getElements().add(new LineTo(endIndentCor.getX(), endIndentCor.getY()));
        }
        if (style.equals("dependency")){
            arrow.getElements().add(new MoveTo(ori.getX(), ori.getY()));
            arrow.getElements().add(new LineTo(begincor.getX(), begincor.getY()));
            arrow.getElements().add(new MoveTo(ori.getX(), ori.getY()));
            arrow.getElements().add(new LineTo(endcor.getX(), endcor.getY()));
            path.getElements().add(new LineTo(ori.getX(), ori.getY()));
            path.setId("dependency");
        }
        if (style.equals("aggregation")){
            Point2D backCor = eval(begin, ori, 0.8);
            arrow.getElements().add(new MoveTo(ori.getX(), ori.getY()));
            arrow.getElements().add(new LineTo(begincor.getX(), begincor.getY()));
            arrow.getElements().add(new LineTo(backCor.getX(), backCor.getY()));
            arrow.getElements().add(new LineTo(endcor.getX(), endcor.getY()));
            arrow.getElements().add(new LineTo(ori.getX(), ori.getY()));
            path.getElements().add(new LineTo(backCor.getX(), backCor.getY()));

        }
        if (style.equals("composition")){
            Point2D backCor = eval(begin, ori, 0.8);
            Polygon polygon = new Polygon();
            polygon.getPoints().addAll(ori.getX(), ori.getY(),begincor.getX(), begincor.getY(),
                    backCor.getX(), backCor.getY(), endcor.getX(), endcor.getY(),ori.getX(), ori.getY());
            path.getElements().add(new LineTo(backCor.getX(), backCor.getY()));
            arrowPane.getChildren().add(polygon);
        }

        arrowPane.getChildren().addAll(path, arrow);
    }

    /**
     *
     * @param begin
     * start cor of parametric line
     * @param end
     * end cor of parametric line
     * @param t
     * between 0 and 1
     * @return
     * returns the point
     */
    //Find the parametric equations for the line through the points (3,2) and (4,6) so that when t = 0 we are at the point (3,2) and when t = 1 we are at the point (4,6).
    private Point2D eval(Point2D begin, Point2D end, double t){
        double first = ((1 - t) * begin.getX()) + (end.getX() * t);
        double second = ((1 - t) * begin.getY()) + (end.getY() * t);
        return new Point2D(first, second);
    }
    private Point2D perpenIndent(Point2D begin, Point2D end, Point2D snijpunt, double t){
        double reciprocal = -1 *( 1/((end.getY()-begin.getY())/(end.getX()-begin.getX())));
        if (((end.getY()-begin.getY())/(end.getX()-begin.getX()) == 0)){
            return new Point2D( snijpunt.getX()+t, begin.getY() + t);
        }
        double b = snijpunt.getY() - reciprocal*snijpunt.getX();
        // loodrecht functie door snijpunt y = reciprocal * x + b
        // neem de waarde waar x = snijpunt + t
        double y = reciprocal * (snijpunt.getX()+t) + b;
        return new Point2D(snijpunt.getX()+t, y);
    }

    /**
     *
     * @param oorsprong
     * where the arrow head appears
     * @param staart
     * using the intersection of 2 functions to find the point where the line meets the box
     * need to change this to a factory
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


        PointTuple punten = new PointTuple(x, y);
        return punten;
    }
}
