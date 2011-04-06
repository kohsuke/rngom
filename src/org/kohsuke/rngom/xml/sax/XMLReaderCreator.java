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

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * A factory for <code>XMLReader</code>s.  Thread-safety is determined by each particular
 * implementation of this interface.
 *
 * @author <a href="mailto:jjc@jclark.com">James Clark</a>
 */
public interface XMLReaderCreator {
  /**
   * Creates a new <code>XMLReader</code>.
   *
   * @return a new <code>XMLReader</code>; never <code>null</code>
   * @throws org.xml.sax.SAXException If an <code>XMLReader</code> cannot be created for any reason
   */
  XMLReader createXMLReader() throws SAXException;
}
