package org.kohsuke.rngom.ast.builder;

import org.kohsuke.rngom.ast.om.Location;


public interface Div extends GrammarSection {
  void endDiv(Location loc, Annotations anno) throws BuildException;
}
