package org.kohsuke.rngom.ast.builder;

import org.kohsuke.rngom.ast.om.Location;


public interface CommentList {
  void addComment(String value, Location loc) throws BuildException;
}
