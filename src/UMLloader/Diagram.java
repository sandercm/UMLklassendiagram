package UMLloader;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by sander on 15/03/17.
 */
@XmlRootElement
public class Diagram {

    private List<box> box;

    @XmlElement(name = "box")
    public List<box> getList(){ return box; }

    public void setList(List<box> box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return "Diagram{" +
                "list=" + box +
                '}';
    }
}
