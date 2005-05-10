package org.kohsuke.rngom.digested;

import org.xml.sax.Locator;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Annotation.
 *
 * @author Kohsuke Kawaguchi (kk@kohsuke.org)
 */
public class DAnnotation {

    /**
     * Instance reserved to be empty.
     */
    static final DAnnotation EMPTY = new DAnnotation();

    /**
     * Keyed by QName.
     */
    final Map<QName,Attribute> attributes = new HashMap<QName,Attribute>();

    /**
     * List of nested elements.
     */
    final List<Element> contents = new ArrayList<Element>();

    /**
     * Attribute.
     */
    public static class Attribute {
        private final String ns;
        private final String localName;
        private final String prefix;

        private String value;
        private Locator loc;

        public Attribute(String ns, String localName, String prefix) {
            this.ns = ns;
            this.localName = localName;
            this.prefix = prefix;
        }

        public Attribute(String ns, String localName, String prefix, String value, Locator loc) {
            this.ns = ns;
            this.localName = localName;
            this.prefix = prefix;
            this.value = value;
            this.loc = loc;
        }

        public String getNs() {
            return ns;
        }

        public String getLocalName() {
            return localName;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getValue() {
            return value;
        }

        public Locator getLoc() {
            return loc;
        }
    }


    public Attribute getAttribute( String nsUri, String localName ) {
        return getAttribute(new QName(nsUri,localName));
    }

    public Attribute getAttribute( QName n ) {
        return attributes.get(n);
    }
}
