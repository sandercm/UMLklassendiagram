package UMLloader;

import UMLloader.Diagram;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by sander on 15/03/17.
 */
public class Unmarshal {
    public Diagram unmarshaller(File file) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Diagram.class);
        return (Diagram) jc.createUnmarshaller().unmarshal(file);
    }
}
