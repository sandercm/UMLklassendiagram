package UMLloader;

import javafx.stage.FileChooser;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by sander on 01/04/17.
 */
public class Unmarshaller {
    public Diagram unmarshall() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files", "*.xml"));
        File file = chooser.showOpenDialog(null);
        Unmarshal un = new Unmarshal();
        //unmarshalling copypasta
        Diagram diagram = null;
        try {
            diagram = un.unmarshaller(file);
        } catch (JAXBException e) {
            System.out.println("JAXB, unmarshalling error");
        }
        return diagram;
    }
}
