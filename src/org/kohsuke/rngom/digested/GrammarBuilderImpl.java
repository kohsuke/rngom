package org.kohsuke.rngom.digested;

import org.kohsuke.rngom.ast.builder.Annotations;
import org.kohsuke.rngom.ast.builder.BuildException;
import org.kohsuke.rngom.ast.builder.CommentList;
import org.kohsuke.rngom.ast.builder.Div;
import org.kohsuke.rngom.ast.builder.Grammar;
import org.kohsuke.rngom.ast.builder.Include;
import org.kohsuke.rngom.ast.builder.Scope;
import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedElementAnnotation;
import org.kohsuke.rngom.ast.om.ParsedPattern;

/**
 * @author Kohsuke Kawaguchi (kk@kohsuke.org)
 */
class GrammarBuilderImpl implements Grammar, Div {

    private final DGrammarPattern p;

    private final Scope parent;

    public GrammarBuilderImpl( Scope parent ) {
        this.parent = parent;
        this.p = new DGrammarPattern();
    }

    public ParsedPattern endGrammar(Location loc, Annotations anno) throws BuildException {
        return p;
    }

    public void endDiv(Location loc, Annotations anno) throws BuildException {
    }

    public void define(String name, Combine combine, ParsedPattern pattern, Location loc, Annotations anno) throws BuildException {
        if(name==START)
            p.start = (DPattern)pattern;
        else {
            // TODO: handle combine
            DDefine d = p.getOrAdd(name);
            d.setPattern( (DPattern) pattern );
            if(anno!=null)
                d.annotation = ((Annotation)anno).getResult();
        }
    }

    public void topLevelAnnotation(ParsedElementAnnotation ea) throws BuildException {
        // TODO
    }

    public void topLevelComment(CommentList comments) throws BuildException {
        // TODO
    }

    public Div makeDiv() {
        return this;
    }

    public Include makeInclude() {
        // TODO
        return null;
    }

    public ParsedPattern makeParentRef(String name, Location loc, Annotations anno) throws BuildException {
        return parent.makeRef(name,loc,anno);
    }

    public ParsedPattern makeRef(String name, Location loc, Annotations anno) throws BuildException {
        return DSchemaBuilderImpl.wrap( new DRefPattern(p.getOrAdd(name)), loc, anno );
    }
}
