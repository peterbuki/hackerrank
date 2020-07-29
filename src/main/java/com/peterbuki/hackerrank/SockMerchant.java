package com.peterbuki.hackerrank;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Hello world!
 */
public class SockMerchant {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = new SockMerchant().doThePairing(n, ar);

        System.out.println(String.valueOf(result));

        scanner.close();
    }

    private int doThePairing(int n, int[] socks) {
        Set<Integer> socksWithoutPair = new HashSet<>();
        int numberOfSocksWithPair = 0;
        for (int sock : socks) {
            if (socksWithoutPair.remove(sock)) {
                numberOfSocksWithPair++;
            } else {
                socksWithoutPair.add(sock);
            }
        }
        return numberOfSocksWithPair;
    }
}
