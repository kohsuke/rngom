package org.kohsuke.rngom.digested;




/**
 * @author Kohsuke Kawaguchi (kk@kohsuke.org)
 */
public abstract class DContainerPattern extends DPattern {
    private DPattern head;
    private DPattern tail;

    public DPattern firstChild() {
        return head;
    }

    public DPattern lastChild() {
        return tail;
    }

    public int countChildren() {
        int i=0;
        for( DPattern p=firstChild(); p!=null; p=p.next())
            i++;
        return i;
    }

    void add( DPattern child ) {
        if(tail==null) {
            child.prev = child.next = null;
            head = tail = child;
        } else {
            child.prev = tail;
            tail.next = child;
            child.next = null;
            tail = child;
        }
    }
}
