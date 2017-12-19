package com.zgx;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {
//        System.out.println(11  >> 2);
//        System.out.println(1  << 30);
//        Integer.valueOf("v",2).toString();
        System.out.println(Integer.valueOf("11111100",2).toString());
        /* int i =  -1;
         int j = i >> 3;
         System.out.println(j);
         int k = j& 129;
         System.out.println(k)*/;

    }
}
