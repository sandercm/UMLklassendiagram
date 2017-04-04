package uml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by sander on 15/03/17.
 * this file is used in unmarshalling for attributes
 */
public class Attribute {
    private String scope;
    //possible options public, private, protected, package
    private String visibility;
    private String name;
    private String type;

    @Override
    public String toString() {
        return "Attribute{" +
                "scope='" + scope + '\'' +
                ", visibility='" + visibility + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getScope() {

        return scope;
    }

    @XmlAttribute
    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getVisibility() {
        return visibility;
    }

    @XmlAttribute
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }
}
