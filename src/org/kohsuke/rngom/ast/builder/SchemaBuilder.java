package org.kohsuke.rngom.ast.builder;

import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedElementAnnotation;
import org.kohsuke.rngom.ast.om.ParsedNameClass;
import org.kohsuke.rngom.ast.om.ParsedPattern;
import org.kohsuke.rngom.parse.*;
import org.kohsuke.rngom.parse.IllegalSchemaException;
import org.kohsuke.rngom.parse.Parseable;

// TODO: define combine error check should be done by the parser.
public interface SchemaBuilder<
    N extends ParsedNameClass,
    P extends ParsedPattern,
    E extends ParsedElementAnnotation,
    L extends Location,
    A extends Annotations<E,L,CL>,
    CL extends CommentList<L>> {

    /**
     * Returns the {@link NameClassBuilder}, which is used to build name
     * classes for this {@link SchemaBuilder}. The
     * {@link org.kohsuke.rngom.nc.NameClass}es that are built will then be
     * fed into this {@link SchemaBuilder}to further build RELAX NG patterns.
     * 
     * @return always return a non-null valid object. This method can (and
     *         probably should) always return the same object.
     */
    NameClassBuilder<N,E,L,A,CL> getNameClassBuilder() throws BuildException;

    P makeChoice(P[] patterns, int nPatterns, L loc, A anno) throws BuildException;

    P makeInterleave(P[] patterns, int nPatterns, L loc, A anno) throws BuildException;

    P makeGroup(P[] patterns, int nPatterns, L loc, A anno) throws BuildException;

    P makeOneOrMore(P p, L loc, A anno) throws BuildException;

    P makeZeroOrMore(P p, L loc, A anno) throws BuildException;

    P makeOptional(P p, L loc, A anno) throws BuildException;

    P makeList(P p, L loc, A anno) throws BuildException;

    P makeMixed(P p, L loc, A anno) throws BuildException;

    P makeEmpty(L loc, A anno);

    P makeNotAllowed(L loc, A anno);

    P makeText(L loc, A anno);

    P makeAttribute(N nc, P p, L loc, A anno) throws BuildException;

    P makeElement(N nc, P p, L loc, A anno) throws BuildException;

    DataPatternBuilder makeDataPatternBuilder(String datatypeLibrary, String type, L loc) throws BuildException;

    P makeValue(String datatypeLibrary, String type, String value,
            Context c, String ns, L loc, A anno) throws BuildException;

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
    Grammar<P,E,L,A,CL> makeGrammar(Scope<P,E,L,A,CL> parent);

    P annotate(P p, A anno) throws BuildException;

    P annotateAfter(P p, E e) throws BuildException;

    P commentAfter(P p, CL comments) throws BuildException;

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
    P makeExternalRef(Parseable current, String uri, String ns, Scope<P,E,L,A,CL> scope,
        L loc, A anno) throws BuildException, IllegalSchemaException;

    L makeLocation(String systemId, int lineNumber, int columnNumber);

    A makeAnnotations(CL comments, Context context);

    ElementAnnotationBuilder<P,E,L,A,CL> makeElementAnnotationBuilder(String ns,
        String localName, String prefix, L loc, CL comments,
        Context context);

    CL makeCommentList();

    P makeErrorPattern();

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
    P expandPattern( P p ) throws BuildException, IllegalSchemaException;
}
