package org.kohsuke.rngom.ast.builder;

import java.util.Enumeration;

import org.relaxng.datatype.ValidationContext;

public interface Context extends ValidationContext {
  Enumeration prefixes();
  Context copy();
}
