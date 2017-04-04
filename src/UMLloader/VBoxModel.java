package UMLloader;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sander on 01/04/17.
 */
public class VBoxModel implements Observable {

    //boxes -> relations, attributes and operations
    private String name;
    private List<Attribute> attributes = null;
    private List<UMLloader.Relation> relations = null;
    private List<Operation> operations = null;
    private Float row;
    private Float col;
    private Float width;


    public VBoxModel(Box box) {
        this.name = box.getName();
        this.attributes = box.getAttributes();
        this.operations = box.getOperations();
        this.relations = box.getRelations();
        this.row = box.getRow();
        this.col = box.getCol();
        this.width = box.getWidth();
    }


    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<UMLloader.Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<UMLloader.Relation> relations) {
        this.relations = relations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Float getRow() {
        return row;
    }

    public void setRow(Float row) {
        this.row = row;
    }

    public Float getCol() {
        return col;
    }

    public void setCol(Float col) {
        this.col = col;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
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
        this.name = box.getName();
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

    @Override
    public void addListener(InvalidationListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listenerList.remove(listener);
    }
}