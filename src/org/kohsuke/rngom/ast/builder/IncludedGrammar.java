package org.kohsuke.rngom.ast.builder;

import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedPattern;

/**
 * {@link Scope} for &lt;grammar> element of the &lt;include>d grammar.
 * <p>
 * This object builds &lt;define>s in the included grammar that
 * override the definitions in the original grammar.
 */
public interface IncludedGrammar extends GrammarSection, Scope {
  ParsedPattern endIncludedGrammar(Location loc, Annotations anno) throws BuildException;
}
