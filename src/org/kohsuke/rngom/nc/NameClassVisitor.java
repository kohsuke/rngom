package org.kohsuke.rngom.nc;

import javax.xml.namespace.QName;

public interface NameClassVisitor {
    void visitChoice(NameClass nc1, NameClass nc2);
    void visitNsName(String ns);
    void visitNsNameExcept(String ns, NameClass nc);
    void visitAnyName();
    void visitAnyNameExcept(NameClass nc);
    void visitName(QName name);
    void visitNull();
    void visitError();
}
