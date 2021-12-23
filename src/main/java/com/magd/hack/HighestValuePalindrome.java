package com.magd.hack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Complete the 'highestValuePalindrome' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. STRING s
 *  2. INTEGER n
 *  3. INTEGER k
 */

public class HighestValuePalindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String s = bufferedReader.readLine();

        String result = highestValuePalindrome(s, n, k);
        System.out.println(result);
//        bufferedWriter.write(result);
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }

    public static String highestValuePalindrome(String s, int n, int k) {
        // Write your code here

        int need = 0;
        int tmp = k;

        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) need++;
        }
        if (need > k) return "-1";

        List<Boolean> sbool = new ArrayList<>();
        for (char c : s.toCharArray()) sbool.add(false);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) < s.charAt(n - 1 - i)) {
                sb.append(s.charAt(n - 1 - i));
                tmp--;
            } else sb.append(s.charAt(i));
            if (s.charAt(i) != sb.charAt(i)) sbool.set(i, true);
        }
        if (tmp == 1 && n % 2 == 1) {
            sb.setCharAt(n / 2, '9');
            tmp--;
            sbool.set(n / 2, true);
        }

        for (int i = 0; i < n; i++) {
            if (tmp > 0 && (sb.charAt(i) != '9')) {
                sb.setCharAt(i, '9');

                if (!sbool.get(i)) {
                    tmp--;
                    sbool.set(i, true);

                }
                sb.setCharAt(n - 1 - i, '9');
                if (!sbool.get(n - 1 - i)) {
                    tmp--;
                    sbool.set(n - 1 - i, true);
                }
            }
        }
        return sb.toString();
    }

}