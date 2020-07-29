package com.peterbuki.hackerrank;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static org.junit.Assert.assertEquals;

public class FileBasedIntegrationTest {

    public static final String TESTFILE = System.getenv("OUTPUT_PATH");
    private ByteArrayInputStream input;

    @Before
    public void setup() {
    }

    @Test
    public void testCountingValleys() throws Exception {
        // OUTPUT_PATH is set in running environment
        setInputString("8\n" +
                "UDDDUDUU");

        CountingValleys.main(new String[0]);

        checkFileForResult("1");
    }

    @Ignore
    public void testJumpingOnClouds() throws Exception {
        setInputString("7\n" +
                "0 0 1 0 0 1 0");

        JumpingOnClouds.main(new String[0]);

        checkFileForResult("4");
    }


    @Test
    public void testJumpingOnCloudsSecond() throws Exception {
        setInputString("6\n" +
                "0 0 0 0 1 0");

        JumpingOnClouds.main(new String[0]);

        checkFileForResult("3");
    }


    @After
    public void deleteFile() {
        try {
            Files.delete(Paths.get(TESTFILE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkFileForResult(String expected) throws IOException {

        String result = new String(Files.readAllBytes(Paths.get(TESTFILE)));
        assertEquals(expected + System.getProperty("line.separator"), result);
    }

    private void setInputString(String testInput) {
        input = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(input);
    }
}
