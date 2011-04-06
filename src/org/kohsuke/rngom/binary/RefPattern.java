/*
 * Copyright (C) 2004-2011
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.kohsuke.rngom.binary;

import org.kohsuke.rngom.binary.visitor.PatternFunction;
import org.kohsuke.rngom.binary.visitor.PatternVisitor;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class RefPattern extends Pattern {
  private Pattern p;
  private Locator refLoc;
  private String name;
  private int checkRecursionDepth = -1;
  private boolean combineImplicit = false;
  private byte combineType = COMBINE_NONE;
  private byte replacementStatus = REPLACEMENT_KEEP;
  private boolean expanded = false;

  static final byte REPLACEMENT_KEEP = 0;
  static final byte REPLACEMENT_REQUIRE = 1;
  static final byte REPLACEMENT_IGNORE = 2;

  static final byte COMBINE_NONE = 0;
  static final byte COMBINE_CHOICE = 1;
  static final byte COMBINE_INTERLEAVE = 2;

  RefPattern(String name) {
    this.name = name;
  }

  Pattern getPattern() {
    return p;
  }
  
  void setPattern(Pattern p) {
    this.p = p;
  }

  Locator getRefLocator() {
    return refLoc;
  }
  
  void setRefLocator(Locator loc) {
    this.refLoc = loc;
  }
  
    @Override
  void checkRecursion(int depth) throws SAXException {
    if (checkRecursionDepth == -1) {
      checkRecursionDepth = depth;
      p.checkRecursion(depth);
      checkRecursionDepth = -2;
    }
    else if (depth == checkRecursionDepth)
      // XXX try to recover from this?
      throw new SAXParseException(SchemaBuilderImpl.localizer.message("recursive_reference", name),
				  refLoc);
  }

    @Override
  Pattern expand(SchemaPatternBuilder b) {
    if (!expanded) {
      p = p.expand(b);
      expanded = true;
    }
    return p;
  }

  boolean samePattern(Pattern other) {
    return false;
  }

  public void accept(PatternVisitor visitor) {
    p.accept(visitor);
  }

  public Object apply(PatternFunction f) {
    return f.caseRef(this);
  }

  byte getReplacementStatus() {
    return replacementStatus;
  }
  
  void setReplacementStatus(byte replacementStatus) {
    this.replacementStatus = replacementStatus;
  }

  boolean isCombineImplicit() {
    return combineImplicit;
  }

  void setCombineImplicit() {
    combineImplicit = true;
  }

  byte getCombineType() {
    return combineType;
  }

  void setCombineType(byte combineType) {
    this.combineType = combineType;
  }

  String getName() {
    return name;
  }
}

