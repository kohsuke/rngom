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

public class ChoicePattern extends BinaryPattern {
  ChoicePattern(Pattern p1, Pattern p2) {
    super(p1.isNullable() || p2.isNullable(),
	  combineHashCode(CHOICE_HASH_CODE, p1.hashCode(), p2.hashCode()),
	  p1,
	  p2);
  }
  Pattern expand(SchemaPatternBuilder b) {
    Pattern ep1 = p1.expand(b);
    Pattern ep2 = p2.expand(b);
    if (ep1 != p1 || ep2 != p2)
      return b.makeChoice(ep1, ep2);
    else
      return this;
  }

  boolean containsChoice(Pattern p) {
    return p1.containsChoice(p) || p2.containsChoice(p);
  }

  public void accept(PatternVisitor visitor) {
    visitor.visitChoice(p1, p2);
  }

  public Object apply(PatternFunction f) {
    return f.caseChoice(this);
  }

  void checkRestrictions(int context, DuplicateAttributeDetector dad, Alphabet alpha)
    throws RestrictionViolationException {
    if (dad != null)
      dad.startChoice();
    p1.checkRestrictions(context, dad, alpha);
    if (dad != null)
      dad.alternative();
    p2.checkRestrictions(context, dad, alpha);
    if (dad != null)
      dad.endChoice();
  }

}

