package org.kohsuke.rngom.ast.builder;

import org.kohsuke.rngom.ast.om.Location;

import org.kohsuke.rngom.parse.IllegalSchemaException;
import org.kohsuke.rngom.parse.Parseable;

public interface Include extends GrammarSection {
    /**
     * @param current
     *      The current document we are parsing.
     *      This is the document that contains an include.
     */
  void endInclude(Parseable current, String uri, String ns,
                  Location loc, Annotations anno) throws BuildException, IllegalSchemaException;
}
