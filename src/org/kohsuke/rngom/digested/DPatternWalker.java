package org.kohsuke.rngom.digested;



/**
 * @author Kohsuke Kawaguchi (kk@kohsuke.org)
 */
public class DPatternWalker implements DPatternVisitor {
    public Object onAttribute(DAttributePattern p) {
        return onXmlToken(p);
    }

    protected Object onXmlToken(DXmlTokenPattern p) {
        return onUnary(p);
    }

    public Object onChoice(DChoicePattern p) {
        return onContainer(p);
    }

    protected Object onContainer(DContainerPattern p) {
        for( DPattern c=p.firstChild(); c!=null; c=c.next() )
            c.accept(this);
        return null;
    }

    public Object onData(DDataPattern p) {
        return null;
    }

    public Object onElement(DElementPattern p) {
        return onXmlToken(p);
    }

    public Object onEmpty(DEmptyPattern p) {
        return null;
    }

    public Object onGrammar(DGrammarPattern p) {
        return p.getStart().accept(this);
    }

    public Object onGroup(DGroupPattern p) {
        return onContainer(p);
    }

    public Object onInterleave(DInterleavePattern p) {
        return onContainer(p);
    }

    public Object onList(DListPattern p) {
        return onUnary(p);
    }

    public Object onMixed(DMixedPattern p) {
        return onUnary(p);
    }

    public Object onNotAllowed(DNotAllowedPattern p) {
        return null;
    }

    public Object onOneOrMore(DOneOrMorePattern p) {
        return onUnary(p);
    }

    public Object onOptional(DOptionalPattern p) {
        return onUnary(p);
    }

    public Object onRef(DRefPattern p) {
        return p.getTarget().getPattern().accept(this);
    }

    public Object onText(DTextPattern p) {
        return null;
    }

    public Object onValue(DValuePattern p) {
        return null;
    }

    public Object onZeroOrMore(DZeroOrMorePattern p) {
        return onUnary(p);
    }

    protected Object onUnary(DUnaryPattern p) {
        return p.getChild().accept(this);
    }
}
