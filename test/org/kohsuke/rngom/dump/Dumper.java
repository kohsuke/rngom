package org.kohsuke.rngom.dump;

import org.kohsuke.rngom.ast.builder.Annotations;
import org.kohsuke.rngom.ast.builder.BuildException;
import org.kohsuke.rngom.ast.builder.CommentList;
import org.kohsuke.rngom.ast.builder.Context;
import org.kohsuke.rngom.ast.builder.DataPatternBuilder;
import org.kohsuke.rngom.ast.builder.ElementAnnotationBuilder;
import org.kohsuke.rngom.ast.builder.Grammar;
import org.kohsuke.rngom.ast.builder.NameClassBuilder;
import org.kohsuke.rngom.ast.builder.SchemaBuilder;
import org.kohsuke.rngom.ast.builder.Scope;
import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedElementAnnotation;
import org.kohsuke.rngom.ast.om.ParsedNameClass;
import org.kohsuke.rngom.ast.om.ParsedPattern;
import org.kohsuke.rngom.parse.IllegalSchemaException;
import org.kohsuke.rngom.parse.Parseable;

/**
 * Dumps the callback invocations to an {@link OutputStream}.
 * 
 * @author
 *      Kohsuke Kawaguchi (kk@kohsuke.org)
 */
public class Dumper implements SchemaBuilder {
    
    private final Factory factory;
    private final Printer printer;
    private NameClassBuilder ncb;
    
    public Dumper() {
        this(new Factory(),new Printer(System.out));
    }
    
    public Dumper(Factory f,Printer p) {
        factory = f;
        printer = p;
    }
    
    public NameClassBuilder getNameClassBuilder() throws BuildException {
        if(ncb==null)
            ncb = factory.createNameClassBuilder(printer);
        return ncb;
    }

    public ParsedPattern makeChoice(ParsedPattern[] patterns, int nPatterns, Location loc, Annotations anno) throws BuildException {
        printer.name("makeChoice");
        for( int i=0; i<nPatterns; i++ )
            printer.param(patterns[i]);
        printer.param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeInterleave(ParsedPattern[] patterns, int nPatterns, Location loc, Annotations anno) throws BuildException {
        printer.name("makeInterleave");
        for( int i=0; i<nPatterns; i++ )
            printer.param(patterns[i]);
        printer.param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeGroup(ParsedPattern[] patterns, int nPatterns, Location loc, Annotations anno) throws BuildException {
        printer.name("makeGroup");
        for( int i=0; i<nPatterns; i++ )
            printer.param(patterns[i]);
        printer.param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeOneOrMore(ParsedPattern p, Location loc, Annotations anno) throws BuildException {
        printer.name("makeOneOrMore").param(p).param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeZeroOrMore(ParsedPattern p, Location loc, Annotations anno) throws BuildException {
        printer.name("makeZeroOrMore").param(p).param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeOptional(ParsedPattern p, Location loc, Annotations anno) throws BuildException {
        printer.name("makeOptional").param(p).param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeList(ParsedPattern p, Location loc, Annotations anno) throws BuildException {
        printer.name("makeList").param(p).param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeMixed(ParsedPattern p, Location loc, Annotations anno) throws BuildException {
        printer.name("makeMixed").param(p).param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeEmpty(Location loc, Annotations anno) {
        printer.name("makeEmpty").param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeNotAllowed(Location loc, Annotations anno) {
        printer.name("makeNotAllowed").param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeText(Location loc, Annotations anno) {
        printer.name("makeText").param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeAttribute(ParsedNameClass nc, ParsedPattern p, Location loc, Annotations anno) throws BuildException {
        printer.name("makeAttribute").param(nc).param(p).param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeElement(ParsedNameClass nc, ParsedPattern p, Location loc, Annotations anno) throws BuildException {
        printer.name("makeElement").param(nc).param(p).param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public DataPatternBuilder makeDataPatternBuilder(String datatypeLibrary, String type, Location loc) throws BuildException {
        printer.name("makeDataPatternBuilder")
            .param(datatypeLibrary).param(type).param(loc);
        return printer.result(factory.createDataPatternBuilder(printer));
    }

    public ParsedPattern makeValue(String datatypeLibrary, String type, String value, Context c, String ns, Location loc, Annotations anno) throws BuildException {
        printer.name("makeValue").param(datatypeLibrary).param(type).param(value).param(ns).param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public Grammar makeGrammar(Scope parent) {
        printer.name("makeGrammar").param(parent);
        return printer.result(factory.createGrammar(printer));
    }

    public ParsedPattern annotate(ParsedPattern p, Annotations anno) throws BuildException {
        printer.name("annotate").param(p).param(anno);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern annotateAfter(ParsedPattern p, ParsedElementAnnotation e) throws BuildException {
        printer.name("annotateAfter").param(p).param(e);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern commentAfter(ParsedPattern p, CommentList comments) throws BuildException {
        printer.name("commentAfter").param(p).param(comments);
        return printer.result(factory.createPattern());
    }

    public ParsedPattern makeExternalRef(Parseable current, String uri, String ns, Scope scope, Location loc, Annotations anno) throws BuildException, IllegalSchemaException {
        printer.name("makeExternalRef").param(uri).param(ns).param(scope).param(loc).param(anno);
        return printer.result(factory.createPattern());
    }

    public Location makeLocation(String systemId, int lineNumber, int columnNumber) {
        printer.name("makeLocation").param(systemId).param(lineNumber).param(columnNumber);
        return printer.result(factory.createLocation());
    }

    public Annotations makeAnnotations(CommentList comments, Context context) {
        printer.name("makeAnnotations").param(comments);
        return printer.result(factory.createAnnotations(printer));
    }

    public ElementAnnotationBuilder makeElementAnnotationBuilder(
        String ns, String localName, String prefix, Location loc, CommentList comments, Context context) {
        
        printer.name("makeElementAnnotationBuilder")
            .param(ns).param(localName).param(prefix).param(loc).param(comments);
        return printer.result(factory.createElementAnnotationBuilder(printer));
    }

    public CommentList makeCommentList() {
        printer.name("makeCommentList");
        return printer.result(factory.createCommentList(printer));
    }

    public ParsedPattern makeErrorPattern() {
        printer.name("makeErrorPattern");
        return printer.result(factory.createPattern());
    }

    public boolean usesComments() {
        return true;
    }

    public ParsedPattern expandPattern(ParsedPattern p) throws BuildException, IllegalSchemaException {
        printer.name("expandPattern").param(p);
        return printer.result(factory.createPattern());
    }
}
