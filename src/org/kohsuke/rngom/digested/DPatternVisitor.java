package org.kohsuke.rngom.digested;



/**
 * @author Kohsuke Kawaguchi (kk@kohsuke.org)
 */
public interface DPatternVisitor {
    Object onAttribute( DAttributePattern p );
    Object onChoice( DChoicePattern p );
    Object onData( DDataPattern p );
    Object onElement( DElementPattern p );
    Object onEmpty( DEmptyPattern p );
    Object onGrammar( DGrammarPattern p );
    Object onGroup( DGroupPattern p );
    Object onInterleave( DInterleavePattern p );
    Object onList( DListPattern p );
    Object onMixed( DMixedPattern p );
    Object onNotAllowed( DNotAllowedPattern p );
    Object onOneOrMore( DOneOrMorePattern p );
    Object onOptional( DOptionalPattern p );
    Object onRef( DRefPattern p );
    Object onText( DTextPattern p );
    Object onValue( DValuePattern p );
    Object onZeroOrMore( DZeroOrMorePattern p );
}
