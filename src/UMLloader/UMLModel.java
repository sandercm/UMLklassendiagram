package UMLloader;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sander on 01/04/17.
 */
public class UMLModel implements Observable {



    /**
     * Lijst van geregistreerde luisteraars.
     */
    private List<InvalidationListener> listenerList = new ArrayList<>();

    private void fireInvalidationEvent () {
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
