package com.peterbuki.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class CountingValleys {

    static int countingValleys(int n, String s) {
        int level = 0;
        int valleys = 0;
        for (byte character : s.getBytes()) {
            if ((byte) 'U' == character) {
                level++;
                if (level == 0) {
                    valleys++;
                }
            } else if ((byte) 'D' == character) {
                level--;
            } else {
                System.out.println("Uh-oh, illegal character: " + character);
            }
        }
        return valleys;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
