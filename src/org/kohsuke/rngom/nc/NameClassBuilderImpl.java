package org.kohsuke.rngom.nc;

import org.kohsuke.rngom.ast.builder.Annotations;
import org.kohsuke.rngom.ast.builder.BuildException;
import org.kohsuke.rngom.ast.builder.CommentList;
import org.kohsuke.rngom.ast.builder.NameClassBuilder;
import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedElementAnnotation;
import org.kohsuke.rngom.ast.om.ParsedNameClass;


/**
 * 
 * @author
 *      Kohsuke Kawaguchi (kk@kohsuke.org)
 */
public class NameClassBuilderImpl implements NameClassBuilder {
    public ParsedNameClass makeChoice(ParsedNameClass[] nameClasses, int nNameClasses, Location loc, Annotations anno) {
      if (nNameClasses <= 0)
        throw new IllegalArgumentException();
      NameClass result = (NameClass)nameClasses[0];
      for (int i = 1; i < nNameClasses; i++)
        result = new ChoiceNameClass(result, (NameClass)nameClasses[i]);
      return result;
    }

    public ParsedNameClass makeName(String ns, String localName, String prefix, Location loc, Annotations anno) {
      return new SimpleNameClass(ns, localName);
    }

    public ParsedNameClass makeNsName(String ns, Location loc, Annotations anno) {
      return new NsNameClass(ns);
    }

    public ParsedNameClass makeNsName(String ns, ParsedNameClass except, Location loc, Annotations anno) {
      return new NsNameExceptNameClass(ns, (NameClass)except);
    }

    public ParsedNameClass makeAnyName(Location loc, Annotations anno) {
      return NameClass.ANY;
    }

    public ParsedNameClass makeAnyName(ParsedNameClass except, Location loc, Annotations anno) {
      return new AnyNameExceptNameClass((NameClass)except);
    }

    public ParsedNameClass makeErrorNameClass() {
        return NameClass.NULL;
    }
    
    public ParsedNameClass annotate(ParsedNameClass nc, Annotations anno) throws BuildException {
      return nc;
    }
    
    public ParsedNameClass annotateAfter(ParsedNameClass nc, ParsedElementAnnotation e) throws BuildException {
      return nc;
    }
    
    public ParsedNameClass commentAfter(ParsedNameClass nc, CommentList comments) throws BuildException {
      return nc;
    }

}
