package org.kohsuke.rngom.nc;

import java.io.Serializable;

import javax.xml.namespace.QName;

import org.kohsuke.rngom.ast.om.ParsedNameClass;

/**
 * Name class is a set of {@link QName}s.
 */
public abstract class NameClass implements ParsedNameClass, Serializable {
    static final int SPECIFICITY_NONE = -1;
    static final int SPECIFICITY_ANY_NAME = 0;
    static final int SPECIFICITY_NS_NAME = 1;
    static final int SPECIFICITY_NAME = 2;
    
    /**
     * Returns true if the given {@link QName} is a valid name
     * for this QName.
     */
    public abstract boolean contains(QName name);
    
    public abstract int containsSpecificity(QName name);
    
    /**
     * Visitor pattern support.
     */
    public abstract void accept(NameClassVisitor visitor);
    
    /**
     * Returns true if the name class accepts infinite number of
     * {@link QName}s.
     * 
     * <p>
     * Intuitively, this method returns true if the name class is
     * some sort of wildcard.
     */
    public abstract boolean isOpen();
    
    
    /**
     * Returns true if the intersection between this name class
     * and the specified name class is non-empty.
     */
    public final boolean hasOverlapWith( NameClass nc2 ) {
        return OverlapDetector.overlap(this,nc2);
    }

    
    /** Sigleton instance that represents "anyName". */
    public static final NameClass ANY = new AnyNameClass();
    
    /** Sigleton instance that accepts no name. */
    public static final NameClass NULL = new NullNameClass();
}
