package org.kohsuke.rngom.parse;

import org.kohsuke.rngom.ast.builder.*;
import org.kohsuke.rngom.ast.om.*;

/**
 * An input that can be turned into a RELAX NG pattern.
 * 
 * <p>
 * This is either a RELAX NG schema in the XML format, or a RELAX NG
 * schema in the compact syntax.
 */
public interface Parseable {
    /**
     * Parses this {@link Parseable} object into a RELAX NG pattern.
     * 
     * @param sb
     *      The builder of the schema object model. This object
     *      dictates how the actual pattern is constructed.
     * 
     * @return
     *      a parsed object. Always returns a non-null valid object.
     */
    ParsedPattern parse(SchemaBuilder sb) throws BuildException, IllegalSchemaException;

    /**
     * Called from {@link Include} in response to
     * {@link Include#endInclude(Parseable, String, String, Location, Annotations)}
     * to parse the included grammar.
     *
     * @param g
     *      receives the events from the included grammar.
     */
    ParsedPattern parseInclude(String uri, SchemaBuilder f, IncludedGrammar g, String inheritedNs)
        throws BuildException, IllegalSchemaException;

    /**
     * Called from {@link SchemaBuilder} in response to
     * {@link SchemaBuilder#makeExternalRef(Parseable, String, String, Scope, Location, Annotations)}
     * to parse the referenced grammar.
     *
     * @param f
     *      receives the events from the referenced grammar.
     */
    ParsedPattern parseExternal(String uri, SchemaBuilder f, Scope s, String inheritedNs)
        throws BuildException, IllegalSchemaException;
}
