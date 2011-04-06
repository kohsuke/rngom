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
package org.kohsuke.rngom.parse.host;

import org.kohsuke.rngom.ast.builder.Annotations;
import org.kohsuke.rngom.ast.builder.BuildException;
import org.kohsuke.rngom.ast.builder.CommentList;
import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedElementAnnotation;

/**
 * 
 * @author
 *      Kohsuke Kawaguchi (kk@kohsuke.org)
 */
class AnnotationsHost extends Base implements Annotations {
    final Annotations lhs;
    final Annotations rhs;
    
    AnnotationsHost( Annotations lhs, Annotations rhs ) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    public void addAttribute(String ns, String localName, String prefix,
        String value, Location _loc) throws BuildException {
        LocationHost loc = cast(_loc);
        lhs.addAttribute(ns, localName, prefix, value, loc.lhs);
        rhs.addAttribute(ns, localName, prefix, value, loc.rhs);
    }
    
    public void addComment(CommentList _comments) throws BuildException {
        CommentListHost comments = (CommentListHost) _comments;
        lhs.addComment(comments==null?null:comments.lhs);
        rhs.addComment(comments==null?null:comments.rhs);
    }
    
    public void addElement(ParsedElementAnnotation _ea) throws BuildException {
        ParsedElementAnnotationHost ea = (ParsedElementAnnotationHost) _ea;
        lhs.addElement(ea.lhs);
        rhs.addElement(ea.rhs);
    }
    
    public void addLeadingComment(CommentList _comments) throws BuildException {
        CommentListHost comments = (CommentListHost) _comments;
        lhs.addLeadingComment(comments.lhs);
        rhs.addLeadingComment(comments.rhs);
    }
}
