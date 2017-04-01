package UMLloader;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by sander on 15/03/17.
 */
@XmlRootElement
public class Diagram {

    private List<UMLloader.Box> box;

    @XmlElement(name = "Box")
    public List<UMLloader.Box> getList(){ return box; }

    public void setList(List<UMLloader.Box> box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return "Diagram{" +
                "list=" + box +
                '}';
    }
}
