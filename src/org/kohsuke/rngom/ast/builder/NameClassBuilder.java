package org.kohsuke.rngom.ast.builder;

import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedElementAnnotation;
import org.kohsuke.rngom.ast.om.ParsedNameClass;


/**
 * 
 * @author
 *      Kohsuke Kawaguchi (kk@kohsuke.org)
 */
public interface NameClassBuilder {
    ParsedNameClass annotate(ParsedNameClass nc, Annotations anno) throws BuildException;
    ParsedNameClass annotateAfter(ParsedNameClass nc, ParsedElementAnnotation e) throws BuildException;
    ParsedNameClass commentAfter(ParsedNameClass nc, CommentList comments) throws BuildException;
    ParsedNameClass makeChoice(ParsedNameClass[] nameClasses, int nNameClasses, Location loc, Annotations anno);

// should be handled by parser - KK
//    static final String INHERIT_NS = new String("#inherit");

// similarly, xmlns:* attribute should be rejected by the parser -KK
    
    ParsedNameClass makeName(String ns, String localName, String prefix, Location loc, Annotations anno);
    ParsedNameClass makeNsName(String ns, Location loc, Annotations anno);
    /**
     * Caller must enforce constraints on except.
     */
    ParsedNameClass makeNsName(String ns, ParsedNameClass except, Location loc, Annotations anno);
    ParsedNameClass makeAnyName(Location loc, Annotations anno);
    /**
     * Caller must enforce constraints on except.
     */
    ParsedNameClass makeAnyName(ParsedNameClass except, Location loc, Annotations anno);

    ParsedNameClass makeErrorNameClass();
}
