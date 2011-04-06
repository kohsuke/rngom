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
package org.kohsuke.rngom.xml.sax;

import org.kohsuke.rngom.util.Uri;
import org.xml.sax.Locator;

public class XmlBaseHandler {
  private int depth = 0;
  private Locator loc;
  private Entry stack = null;

  private static class Entry {
    private Entry parent;
    private String attValue;
    private String systemId;
    private int depth;
  }

  public void setLocator(Locator loc) {
    this.loc = loc;
  }

  public void startElement() {
    ++depth;
  }

  public void endElement() {
    if (stack != null && stack.depth == depth)
      stack = stack.parent;
    --depth;
  }

  public void xmlBaseAttribute(String value) {
    Entry entry = new Entry();
    entry.parent = stack;
    stack = entry;
    entry.attValue = Uri.escapeDisallowedChars(value);
    entry.systemId = getSystemId();
    entry.depth = depth;
  }

  private String getSystemId() {
    return loc == null ? null : loc.getSystemId();
  }

  public String getBaseUri() {
    return getBaseUri1(getSystemId(), stack);
  }

  private static String getBaseUri1(String baseUri, Entry stack) {
    if (stack == null
	|| (baseUri != null && !baseUri.equals(stack.systemId)))
      return baseUri;
    baseUri = stack.attValue;
    if (Uri.isAbsolute(baseUri))
      return baseUri;
    return Uri.resolve(getBaseUri1(stack.systemId, stack.parent), baseUri);
  }
}
