package org.kohsuke.rngom.ast.builder;

import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedElementAnnotation;
import org.kohsuke.rngom.ast.om.ParsedPattern;

/**
 * The container that can have &lt;define> elements.
 * <p>
 * {@link Div}, {@link Grammar}, {@link Include}, or {@link IncludedGrammar}.
 */
public interface GrammarSection {

    static final class Combine {
        private final String name;
        private Combine(String name) {
            this.name = name;
        }
        final public String toString() {
            return name;
        }
    }

    static final Combine COMBINE_CHOICE = new Combine("choice");
    static final Combine COMBINE_INTERLEAVE = new Combine("interleave");

    static final String START = new String("#start");

    /**
     * Called when a pattern is defined.
     *
     * @param name
     *      Name of the pattern. For the definition by a &lt;start/> element,
     *      this parameter is the same as {@link #START}. (IOW, use <tt>name==START</tt>}
     *      to test if it's a named pattern definition or the start pattern definition.
     * @param combine
     *      null or {@link #COMBINE_CHOICE} or {@link #COMBINE_INTERLEAVE} depending
     *      on the value of the combine attribute.
     * @param pattern
     *      The pattern to be defined.
     */
    void define(
        String name,
        Combine combine,
        ParsedPattern pattern,
        Location loc,
        Annotations anno)
        throws BuildException;
    void topLevelAnnotation(ParsedElementAnnotation ea) throws BuildException;
    void topLevelComment(CommentList comments) throws BuildException;
    Div makeDiv();
    /**
     * Returns null if already in an include.
     */
    Include makeInclude();
}
