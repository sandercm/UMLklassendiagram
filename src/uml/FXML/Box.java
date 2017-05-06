package uml.FXML;


import uml.FXML.Attribute;
import uml.FXML.Operation;
import uml.FXML.Relation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by sander on 15/03/17.
 * this file is used in unmarshalling for the box
 */
public class Box {

    private String name;
    //distance to the top of the Box
    private Float row;
    //distance to the right of the Box
    private Float col;
    //width of the Box
    private Float width;
    //list of relations to other boxes
    private List<Relation> relations;
    //list of attributes
    private List<Attribute> attributes;
    //list of operations
    private List<Operation> operations;

    public Float getRow() {
        return row;
    }

    public Float getCol() {
        return col;
    }

    public Float getWidth() {
        return width;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "row")
    public void setRow(Float row) {
        this.row = row;
    }

    @XmlAttribute(name = "col")
    public void setCol(Float col) {
        this.col = col;
    }

    @XmlAttribute(name = "width")
    public void setWidth(Float width) {
        this.width = width;
    }

    @XmlElement(name = "relation")
    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    @XmlElement(name = "attribute")
    public void setAttributes(List<Attribute> Attributes) {
        this.attributes = Attributes;
    }

    @XmlElement(name = "operation")
    public void setOperations(List<Operation> Operations) {
        this.operations = Operations;
    }


    @Override
    public String toString() {
        return "Box{" +
                "name='" + name + '\'' +
                ", row=" + row +
                ", col=" + col +
                ", width=" + width +
                ", relations=" + relations +
                ", attributes=" + attributes +
                ", operations=" + operations +
                '}';
    }


}
