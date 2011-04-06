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
package org.kohsuke.rngom.dt;

import org.relaxng.datatype.DatatypeLibraryFactory;
import org.relaxng.datatype.DatatypeLibrary;
import org.relaxng.datatype.Datatype;
import org.relaxng.datatype.DatatypeBuilder;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.ValidationContext;
import org.relaxng.datatype.DatatypeStreamingValidator;
import org.relaxng.datatype.helpers.StreamingValidatorImpl;

/**
 * {@link DatatypeLibraryFactory} implementation
 * that returns a dummy {@link Datatype}.
 *
 * @author Kohsuke Kawaguchi (kk@kohsuke.org)
 */
public final class DoNothingDatatypeLibraryFactoryImpl implements DatatypeLibraryFactory {
    public DatatypeLibrary createDatatypeLibrary(String s) {
        return new DatatypeLibrary() {

            public Datatype createDatatype(String s) throws DatatypeException {
                return createDatatypeBuilder(s).createDatatype();
            }

            public DatatypeBuilder createDatatypeBuilder(String s) throws DatatypeException {
                return new DatatypeBuilder() {
                    public void addParameter(String s, String s1, ValidationContext validationContext) throws DatatypeException {
                    }

                    public Datatype createDatatype() throws DatatypeException {
                        return new Datatype() {

                            public boolean isValid(String s, ValidationContext validationContext) {
                                return false;
                            }

                            public void checkValid(String s, ValidationContext validationContext) throws DatatypeException {
                            }

                            public DatatypeStreamingValidator createStreamingValidator(ValidationContext validationContext) {
                                return new StreamingValidatorImpl(this,validationContext);
                            }

                            public Object createValue(String s, ValidationContext validationContext) {
                                return null;
                            }

                            public boolean sameValue(Object o, Object o1) {
                                return false;
                            }

                            public int valueHashCode(Object o) {
                                return 0;
                            }

                            public int getIdType() {
                                return ID_TYPE_NULL;
                            }

                            public boolean isContextDependent() {
                                return false;
                            }
                        };
                    }
                };
            }
        };
    }
}
