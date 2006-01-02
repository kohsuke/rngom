package org.kohsuke.rngom.digested;

import junit.framework.TestCase;
import org.custommonkey.xmlunit.XMLAssert;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class DXMLPrinterTest extends TestCase {
    protected void test(String resource) throws Exception {
        System.out.println(resource);
        String in = "src/test/" + getClass().getPackage().getName().replace('.', '/') + '/' + resource;
        String out = in + ".out";
        try {
            DXMLPrinter.main(new String[]{in, out});
            Reader input = new FileReader(in);
            Reader output = new FileReader(out);
            XMLAssert.assertXMLEqual(input, output);
            input.close();
            output.close();
            new File(out).delete();
        }
        catch (Exception e) {
            assertTrue("Unexpected exception", false);
        }
    }

    public void testGrammar() throws Exception {
        test("DXMLPrinterTest.rng");
    }

    public void testXmlNS() throws Exception {
        test("xmlns.rng");
    }

    public void testCombine() throws Exception {
        test("combine.rng");
    }
}
