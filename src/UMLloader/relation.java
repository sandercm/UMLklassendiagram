package UMLloader;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by sander on 15/03/17.
 */
public class relation {
    // gives the type of relation
    private String type;
    // gives who the Box is in a relation with
    private String with;

    public String getType() {
        return type;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    public String getWith() {
        return with;
    }

    @XmlAttribute
    public void setWith(String with) {
        this.with = with;
    }

    @Override
    public String toString() {
        return "relation{" +
                "type='" + type + '\'' +
                ", with='" + with + '\'' +
                '}';
    }
}
