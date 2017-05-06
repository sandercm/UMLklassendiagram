package uml;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import sun.java2d.pipe.SpanShapeRenderer;
import uml.FXML.Attribute;
import uml.FXML.Box;
import uml.FXML.Operation;
import uml.FXML.Relation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sander on 01/04/17.
 */
public class VBoxModel implements Observable {

    //boxes -> relations, attributes and operations
    private SimpleStringProperty name;
    private List<Attribute> attributes;
    private List<Relation> relations;
    private List<Operation> operations;
    private SimpleFloatProperty row;
    private SimpleFloatProperty col;
    private SimpleFloatProperty width;
    private SimpleDoubleProperty height;


    public VBoxModel(Box box) {
        this.name = new SimpleStringProperty(box.getName()) ;
        this.attributes = box.getAttributes();
        this.operations = box.getOperations();
        this.relations = box.getRelations();
        this.row = new SimpleFloatProperty(box.getRow());
        this.col = new SimpleFloatProperty(box.getCol());
        this.width = new SimpleFloatProperty(box.getWidth());
    }


    //getters and setters
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public double getRow() {
        return row.doubleValue();
    }

    public void setRow(Float row) {
        this.row = new SimpleFloatProperty(row);
    }

    public double getCol() {
        return col.doubleValue();
    }

    public void setCol(Float col) {
        this.col = new SimpleFloatProperty(col);
    }

    public Double getWidth() {
        return width.doubleValue();
    }

    public void setWidth(Float width) {
        this.width = new SimpleFloatProperty(width);
    }

    public List<InvalidationListener> getListenerList() {
        return listenerList;
    }

    public void setListenerList(List<InvalidationListener> listenerList) {
        this.listenerList = listenerList;
    }
    /**
     *     private Float row;
     //distance to the right of the Box
     private Float col;
     //width of the Box
     private Float width;
     */
    /**
     * Lijst van geregistreerde luisteraars.
     */
    public void setName(Box box) {
        this.name = new SimpleStringProperty(box.getName());
    }

    public void setAttributes(Box box) {
        this.attributes = box.getAttributes();
    }

    public void setOperations(Box box) {
        this.operations = box.getOperations();
    }


    private List<InvalidationListener> listenerList = new ArrayList<>();


    private void fireInvalidationEvent() {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

    public Double getHeight() {
        return height.get();
    }

    public void setHeight(Double height) {
        this.height = new SimpleDoubleProperty(height);
    }

    @Override
    public String toString() {
        return "VBoxModel{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                ", relations=" + relations +
                ", operations=" + operations +
                ", row=" + row +
                ", col=" + col +
                ", width=" + width +
                ", listenerList=" + listenerList +
                '}';
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listenerList.remove(listener);
    }
}