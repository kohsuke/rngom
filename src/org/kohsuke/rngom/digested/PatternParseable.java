package org.kohsuke.rngom.digested;

import org.kohsuke.rngom.ast.builder.Annotations;
import org.kohsuke.rngom.ast.builder.BuildException;
import org.kohsuke.rngom.ast.builder.IncludedGrammar;
import org.kohsuke.rngom.ast.builder.SchemaBuilder;
import org.kohsuke.rngom.ast.builder.Scope;
import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedNameClass;
import org.kohsuke.rngom.ast.om.ParsedPattern;
import org.kohsuke.rngom.nc.NameClass;
import org.kohsuke.rngom.parse.IllegalSchemaException;
import org.kohsuke.rngom.parse.Parseable;
import org.xml.sax.Locator;

/**
 * @author Kohsuke Kawaguchi (kk@kohsuke.org)
 */
final class PatternParseable implements Parseable {
    private final DPattern pattern;

    public PatternParseable(DPattern p) {
        this.pattern = p;
    }

    public ParsedPattern parse(SchemaBuilder sb) throws BuildException, IllegalSchemaException {
        return (ParsedPattern)pattern.accept(new Parser(sb));
    }

    public ParsedPattern parseInclude(String uri, SchemaBuilder f, IncludedGrammar g, String inheritedNs) throws BuildException, IllegalSchemaException {
        throw new UnsupportedOperationException();
    }

    public ParsedPattern parseExternal(String uri, SchemaBuilder f, Scope s, String inheritedNs) throws BuildException, IllegalSchemaException {
        throw new UnsupportedOperationException();
    }


    private static class Parser implements DPatternVisitor {
        private final SchemaBuilder sb;

        public Parser(SchemaBuilder sb) {
            this.sb = sb;
        }

        private Annotations parseAnnotation(DPattern p) {
            // TODO
            return null;
        }

        private Location parseLocation(DPattern p) {
            Locator l = p.getLocation();
            return sb.makeLocation(l.getSystemId(),l.getLineNumber(),l.getColumnNumber());
        }

        private ParsedNameClass parseNameClass(NameClass name) {
            // TODO: reparse the name class
            return name;
        }



        public Object onAttribute(DAttributePattern p) {
            return sb.makeAttribute(
                parseNameClass(p.getName()),
                (ParsedPattern)p.getChild().accept(this),
                parseLocation(p),
                parseAnnotation(p) );
        }

        public Object onChoice(DChoicePattern p) {
            ParsedPattern[] kids = new ParsedPattern[p.countChildren()];
            int i=0;
            for( DPattern c=p.firstChild(); c!=null; c=c.next(),i++ )
                kids[i] = (ParsedPattern)c.accept(this);
            return sb.makeChoice(kids,i,parseLocation(p),null);
        }

        public Object onData(DDataPattern p) {
            // TODO
            return null;
        }

        public Object onElement(DElementPattern p) {
            return sb.makeElement(
                parseNameClass(p.getName()),
                (ParsedPattern)p.getChild().accept(this),
                parseLocation(p),
                parseAnnotation(p) );
        }

        public Object onEmpty(DEmptyPattern p) {
            return sb.makeEmpty(
                parseLocation(p),
                parseAnnotation(p) );
        }

        public Object onGrammar(DGrammarPattern p) {
            // TODO
            return null;
        }

        public Object onGroup(DGroupPattern p) {
            ParsedPattern[] kids = new ParsedPattern[p.countChildren()];
            int i=0;
            for( DPattern c=p.firstChild(); c!=null; c=c.next(),i++ )
                kids[i] = (ParsedPattern)c.accept(this);
            return sb.makeGroup(kids,i,parseLocation(p),null);
        }

        public Object onInterleave(DInterleavePattern p) {
            ParsedPattern[] kids = new ParsedPattern[p.countChildren()];
            int i=0;
            for( DPattern c=p.firstChild(); c!=null; c=c.next(),i++ )
                kids[i] = (ParsedPattern)c.accept(this);
            return sb.makeInterleave(kids,i,parseLocation(p),null);
        }

        public Object onList(DListPattern p) {
            return sb.makeList(
                (ParsedPattern)p.getChild().accept(this),
                parseLocation(p),
                parseAnnotation(p) );
        }

        public Object onMixed(DMixedPattern p) {
            return sb.makeMixed(
                (ParsedPattern)p.getChild().accept(this),
                parseLocation(p),
                parseAnnotation(p) );
        }

        public Object onNotAllowed(DNotAllowedPattern p) {
            return sb.makeNotAllowed(
                parseLocation(p),
                parseAnnotation(p) );
        }

        public Object onOneOrMore(DOneOrMorePattern p) {
            return sb.makeOneOrMore(
                (ParsedPattern)p.getChild().accept(this),
                parseLocation(p),
                parseAnnotation(p) );
        }

        public Object onOptional(DOptionalPattern p) {
            return sb.makeOptional(
                (ParsedPattern)p.getChild().accept(this),
                parseLocation(p),
                parseAnnotation(p) );
        }

        public Object onRef(DRefPattern p) {
            // TODO
            return null;
        }

        public Object onText(DTextPattern p) {
            return sb.makeText(
                parseLocation(p),
                parseAnnotation(p) );
        }

        public Object onValue(DValuePattern p) {
            return sb.makeValue(
                p.getDatatypeLibrary(),
                p.getType(),
                p.getValue(),
                p.getContext(),
                p.getNs(),
                parseLocation(p),
                parseAnnotation(p) );
        }

        public Object onZeroOrMore(DZeroOrMorePattern p) {
            return sb.makeZeroOrMore(
                (ParsedPattern)p.getChild().accept(this),
                parseLocation(p),
                parseAnnotation(p) );
        }
    }
}
