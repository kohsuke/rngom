package org.kohsuke.rngom.ast.builder;

import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedElementAnnotation;


public interface ElementAnnotationBuilder extends Annotations {
  void addText(String value, Location loc, CommentList comments) throws BuildException;
  ParsedElementAnnotation makeElementAnnotation() throws BuildException;
}
