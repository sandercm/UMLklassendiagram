package uml;

import javafx.scene.shape.Line;

/**
 * Created by Sander on 9/04/2017.
 */
public class Arrow extends Line {
    String type;
    public Arrow(double originX, double originY, double targetX, double targetY, String type){
        super(originX,originY,targetX,targetY);
        this.type = type;
    }
}
