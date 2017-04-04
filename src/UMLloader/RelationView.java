package UMLloader;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by sander on 04/04/17.
 * this class controls the relation view
 */
public class RelationView {

    public RelationView(AnchorPane anchorpane){
        List<PageBox> boxes = new ArrayList<>();
        for (Object obj: anchorpane.getChildren().toArray()
             ) {
            if (obj instanceof PageBox){
                boxes.add((PageBox) obj);
            }
        }
        for (PageBox box: boxes
             ) {
            System.out.println(box.getModel().getRelations());
        }
    }


}
