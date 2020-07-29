package com.peterbuki.hackerrank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit test for simple SockMerchant.
 */
public class InputOutputIntegrationTest
{
    private ByteArrayInputStream input;
    private ByteArrayOutputStream output;

    @Before
    public void setup() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @Test
    public void testSockMerchant() throws Exception
    {
        // Given that the expected string is
        setInputString("9\n" +
                "10 20 20 10 10 30 50 10 20");

        // When application is started
        SockMerchant.main(new String[0]);

        // Then the output is the following
        assertEquals("3\r\n", output.toString());
    }

    private void setInputString(String testInput) {
        input = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(input);
    }
}
