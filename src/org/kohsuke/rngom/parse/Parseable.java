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
     * Parses this {@link Parsable} object into a RELAX NG pattern.
     * 
     * @param sb
     *      The builder of the schema object model. This object
     *      dictates how the actual pattern is constructed.
     * 
     * @return
     *      a parsed object. Always returns a non-null valid object.
     */
    ParsedPattern parse(SchemaBuilder sb) throws BuildException, IllegalSchemaException;

    ParsedPattern parseInclude(String uri, SchemaBuilder f, IncludedGrammar g, String inheritedNs)
        throws BuildException, IllegalSchemaException;
    
    ParsedPattern parseExternal(String uri, SchemaBuilder f, Scope s, String inheritedNs)
        throws BuildException, IllegalSchemaException;
}
