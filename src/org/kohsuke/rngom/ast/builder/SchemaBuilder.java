package org.kohsuke.rngom.ast.builder;

import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedElementAnnotation;
import org.kohsuke.rngom.ast.om.ParsedNameClass;
import org.kohsuke.rngom.ast.om.ParsedPattern;
import org.kohsuke.rngom.parse.*;
import org.kohsuke.rngom.parse.IllegalSchemaException;
import org.kohsuke.rngom.parse.Parseable;

// TODO: define combine error check should be done by the parser.
public interface SchemaBuilder {
    /**
     * Returns the {@link NameClassBuilder}, which is used to build name
     * classes for this {@link SchemaBuilder}. The
     * {@link org.kohsuke.rngom.nc.NameClass}es that are built will then be
     * fed into this {@link SchemaBuilder}to further build RELAX NG patterns.
     * 
     * @return always return a non-null valid object. This method can (and
     *         probably should) always return the same object.
     */
    NameClassBuilder getNameClassBuilder() throws BuildException;

    ParsedPattern makeChoice(ParsedPattern[] patterns, int nPatterns,
        Location loc, Annotations anno) throws BuildException;

    ParsedPattern makeInterleave(ParsedPattern[] patterns, int nPatterns,
        Location loc, Annotations anno) throws BuildException;

    ParsedPattern makeGroup(ParsedPattern[] patterns, int nPatterns,
        Location loc, Annotations anno) throws BuildException;

    ParsedPattern makeOneOrMore(ParsedPattern p, Location loc, Annotations anno)
        throws BuildException;

    ParsedPattern makeZeroOrMore(ParsedPattern p, Location loc, Annotations anno)
        throws BuildException;

    ParsedPattern makeOptional(ParsedPattern p, Location loc, Annotations anno)
        throws BuildException;

    ParsedPattern makeList(ParsedPattern p, Location loc, Annotations anno)
        throws BuildException;

    ParsedPattern makeMixed(ParsedPattern p, Location loc, Annotations anno)
        throws BuildException;

    ParsedPattern makeEmpty(Location loc, Annotations anno);

    ParsedPattern makeNotAllowed(Location loc, Annotations anno);

    ParsedPattern makeText(Location loc, Annotations anno);

    ParsedPattern makeAttribute(ParsedNameClass nc, ParsedPattern p,
        Location loc, Annotations anno) throws BuildException;

    ParsedPattern makeElement(ParsedNameClass nc, ParsedPattern p,
        Location loc, Annotations anno) throws BuildException;

    DataPatternBuilder makeDataPatternBuilder(String datatypeLibrary,
        String type, Location loc) throws BuildException;

    ParsedPattern makeValue(String datatypeLibrary, String type, String value,
        Context c, String ns, Location loc, Annotations anno)
        throws BuildException;

    /**
     * 
     * @param parent
     *      The parent scope. null if there's no parent scope.
     *      For example, if the complete document looks like the following:
     *      <pre><xmp>
     *      <grammar>
     *        <start><element name="root"><empty/></element></start>
     *      </grammar>
     *      </xmp></pre>
     *      Then when the outer-most {@link Grammar} is created, it will
     *      receive the <tt>null</tt> parent.
     */
    Grammar makeGrammar(Scope parent);

    ParsedPattern annotate(ParsedPattern p, Annotations anno)
        throws BuildException;

    ParsedPattern annotateAfter(ParsedPattern p, ParsedElementAnnotation e)
        throws BuildException;

    ParsedPattern commentAfter(ParsedPattern p, CommentList comments)
        throws BuildException;

    /**
     * 
     * @param current
     *      Current grammar that we are parsing. This is what contains
     *      externalRef.
     * @param scope
     *      The parent scope. null if there's no parent scope.
     *      See {@link #makeGrammar(Scope)} for more details about
     *      when this parameter can be null.
     */
    ParsedPattern makeExternalRef(Parseable current, String uri, String ns, Scope scope,
        Location loc, Annotations anno) throws BuildException,
        IllegalSchemaException;

    Location makeLocation(String systemId, int lineNumber, int columnNumber);

    Annotations makeAnnotations(CommentList comments, Context context);

    ElementAnnotationBuilder makeElementAnnotationBuilder(String ns,
        String localName, String prefix, Location loc, CommentList comments,
        Context context);

    CommentList makeCommentList();

    ParsedPattern makeErrorPattern();

    /**
     * If this {@link SchemaBuilder}is interested in actually parsing
     * comments, this method returns true.
     * <p>
     * Returning false allows the schema parser to speed up the processing by
     * skiping comment-related handlings.
     */
    boolean usesComments();
    
    /**
     * Called after all the parsing is done.
     * 
     * <p>
     * This hook typically allows as {@link SchemaBuilder} to expand
     * notAllowed (if it's following the simplification as in the spec.)
     */
    ParsedPattern expandPattern( ParsedPattern p ) throws BuildException, IllegalSchemaException;
}
