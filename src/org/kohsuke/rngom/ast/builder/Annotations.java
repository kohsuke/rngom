package org.kohsuke.rngom.ast.builder;

import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedElementAnnotation;


/**
 * Includes attributes and child elements before any RELAX NG element.
 */
public interface Annotations {
  void addAttribute(String ns, String localName, String prefix, String value, Location loc)
          throws BuildException;
  void addElement(ParsedElementAnnotation ea) throws BuildException;
  /**
   * Adds comments following the last initial child element annotation.
   */
  void addComment(CommentList comments) throws BuildException;
  void addLeadingComment(CommentList comments) throws BuildException;
}
