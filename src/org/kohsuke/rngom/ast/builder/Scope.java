package org.kohsuke.rngom.ast.builder;

import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedPattern;

public interface Scope extends GrammarSection {
    ParsedPattern makeParentRef(String name, Location loc, Annotations anno)
        throws BuildException;

    ParsedPattern makeRef(String name, Location loc, Annotations anno)
        throws BuildException;
}
