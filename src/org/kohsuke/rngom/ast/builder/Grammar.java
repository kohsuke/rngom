package org.kohsuke.rngom.ast.builder;

import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedPattern;

/**
 * {@link Scope} for &lt;grammar> element that serves as a container
 * of &lt;define>s.
 */
public interface Grammar extends GrammarSection, Scope {
    ParsedPattern endGrammar(Location loc, Annotations anno) throws BuildException;
}
