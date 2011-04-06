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
package org.kohsuke.rngom.xml.util;

import java.io.UnsupportedEncodingException;

public abstract class EncodingMap {
  private static final String[] aliases = {
    "UTF-8", "UTF8",
    "UTF-16", "Unicode",
    "UTF-16BE", "UnicodeBigUnmarked",
    "UTF-16LE", "UnicodeLittleUnmarked",
    "US-ASCII", "ASCII",
    "TIS-620", "TIS620"
  };
      
  static public String getJavaName(String enc) {
    try {
      "x".getBytes(enc);
    }
    catch (UnsupportedEncodingException e) {
      for (int i = 0; i < aliases.length; i += 2) {
	if (enc.equalsIgnoreCase(aliases[i])) {
	  try {
	    "x".getBytes(aliases[i + 1]);
	    return aliases[i + 1];
	  }
	  catch (UnsupportedEncodingException e2) {}
	}
      }
    }
    return enc;
  }

  static public void main(String[] args) {
    System.err.println(getJavaName(args[0]));
  }
}
  
