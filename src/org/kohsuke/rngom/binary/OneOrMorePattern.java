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
import org.xml.sax.SAXException;

public class OneOrMorePattern extends Pattern {
  Pattern p;

  OneOrMorePattern(Pattern p) {
    super(p.isNullable(),
	  p.getContentType(),
	  combineHashCode(ONE_OR_MORE_HASH_CODE, p.hashCode()));
    this.p = p;
  }

    @Override
  Pattern expand(SchemaPatternBuilder b) {
    Pattern ep = p.expand(b);
    if (ep != p)
      return b.makeOneOrMore(ep);
    else
      return this;
  }

    @Override
  void checkRecursion(int depth) throws SAXException {
    p.checkRecursion(depth);
  }

    @Override
  void checkRestrictions(int context, DuplicateAttributeDetector dad, Alphabet alpha)
    throws RestrictionViolationException {
    switch (context) {
    case START_CONTEXT:
      throw new RestrictionViolationException("start_contains_one_or_more");
    case DATA_EXCEPT_CONTEXT:
      throw new RestrictionViolationException("data_except_contains_one_or_more");
    }
    
    p.checkRestrictions(context == ELEMENT_CONTEXT
			? ELEMENT_REPEAT_CONTEXT
			: context,
			dad,
			alpha);
    if (context != LIST_CONTEXT
	&& !contentTypeGroupable(p.getContentType(), p.getContentType()))
      throw new RestrictionViolationException("one_or_more_string");
  }

  boolean samePattern(Pattern other) {
    return (other instanceof OneOrMorePattern
	    && p == ((OneOrMorePattern)other).p);
  }

  public void accept(PatternVisitor visitor) {
    visitor.visitOneOrMore(p);
  }

  public Object apply(PatternFunction f) {
    return f.caseOneOrMore(this);
  }

  Pattern getOperand() {
    return p;
  }
}
