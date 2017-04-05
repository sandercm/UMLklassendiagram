package uml;

import javafx.stage.FileChooser;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by sander on 01/04/17.
 */
public class Unmarshaller {
    public Diagram unmarshall(File file) {
        Unmarshal un = new Unmarshal();
        try {
            return un.unmarshaller(file);
        } catch (JAXBException e) {
            System.out.println("JAXB, unmarshalling error");
        }
        return null;
    }
}
