package org.kohsuke.rngom.digested;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Kohsuke Kawaguchi (kk@kohsuke.org)
 */
public class DGrammarPattern extends DPattern {
    private final Map patterns = new HashMap();

    DPattern start;

    /**
     * Gets the start pattern.
     */
    public DPattern getStart() {
        return start;
    }

    /**
     * Gets the named pattern by its name.
     *
     * @return
     *      null if not found.
     */
    public DDefine get( String name ) {
        return (DDefine)patterns.get(name);
    }

    DDefine getOrAdd( String name ) {
        if(patterns.containsKey(name)) {
            return get(name);
        } else {
            DDefine d = new DDefine(name);
            patterns.put(name,d);
            return d;
        }
    }

    /**
     * Iterates all the {@link DDefine}s in this grammar.
     */
    public Iterator iterator() {
        return patterns.values().iterator();
    }

    public boolean isNullable() {
        return start.isNullable();
    }

    public Object accept( DPatternVisitor visitor ) {
        return visitor.onGrammar(this);
    }
}
