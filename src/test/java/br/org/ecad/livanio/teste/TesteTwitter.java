package br.org.ecad.livanio.teste;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TesteTwitter extends TestCase{
	
	 /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TesteTwitter( String texto )
    {
        super( texto );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TesteTwitter.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

}
