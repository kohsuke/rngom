package org.kohsuke.rngom.nc;

import javax.xml.namespace.QName;

class OverlapDetector implements NameClassVisitor {
    private NameClass nc1;
    private NameClass nc2;
    private boolean overlaps = false;

    static final String IMPOSSIBLE = "\u0000";

    private OverlapDetector(NameClass nc1, NameClass nc2) {
        this.nc1 = nc1;
        this.nc2 = nc2;
        nc1.accept(this);
        nc2.accept(this);
    }

    private void probe(QName name) {
        if (nc1.contains(name) && nc2.contains(name))
            overlaps = true;
    }

    public void visitChoice(NameClass nc1, NameClass nc2) {
        nc1.accept(this);
        nc2.accept(this);
    }

    public void visitNsName(String ns) {
        probe(new QName(ns, IMPOSSIBLE));
    }

    public void visitNsNameExcept(String ns, NameClass ex) {
        probe(new QName(ns, IMPOSSIBLE));
        ex.accept(this);
    }

    public void visitAnyName() {
        probe(new QName(IMPOSSIBLE, IMPOSSIBLE));
    }

    public void visitAnyNameExcept(NameClass ex) {
        probe(new QName(IMPOSSIBLE, IMPOSSIBLE));
        ex.accept(this);
    }

    public void visitName(QName name) {
        probe(name);
    }

    public void visitNull() {
    }

    public void visitError() {
    }

    static boolean overlap(NameClass nc1, NameClass nc2) {
        if (nc2 instanceof SimpleNameClass) {
            SimpleNameClass snc = (SimpleNameClass) nc2;
            return nc1.contains(snc.name);
        }
        if (nc1 instanceof SimpleNameClass) {
            SimpleNameClass snc = (SimpleNameClass) nc1;
            return nc2.contains(snc.name);
        }
        return new OverlapDetector(nc1, nc2).overlaps;
    }
}
