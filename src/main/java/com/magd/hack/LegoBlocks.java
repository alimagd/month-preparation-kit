package com.magd.hack;


import java.io.*;
import java.util.stream.IntStream;

/*
 * Complete the 'legoBlocks' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER n
 *  2. INTEGER m
 */
public class LegoBlocks {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int result = legoBlocks(n, m);
                System.out.println(result);

//                bufferedWriter.write(String.valueOf(result));
//                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
//        bufferedWriter.close();
    }

    static int mod = 1000000007;

    public static long power(long num, int p) {

        if (p == 0) return 1;
        if (p == 1) return num;

        long number = num;
        for (int i = 2; i <= p; i++) {
            num *= number;
            num %= mod;
        }
        return num;
    }

    public static int legoBlocks(int n, int m) {

        long[] T = new long[1001];
        long[] S = new long[1001];
        long[] P = new long[1001];

        T[0] = T[1] = 1;
        T[2] = 2;
        T[3] = 4;
        T[4] = 8;

        P[0] = P[1] = 1;

        for (int i = 5; i <= 1000; i++)
            T[i] = (T[i - 1] + T[i - 2] + T[i - 3] + T[i - 4]) % mod;

        S[0] = 1;
        S[1] = 1;

        int sum;

        for (int i = 0; i <= m; i++)
            P[i] = power(T[i], n);

        for (int i = 2; i <= m; i++) {
            sum = 0;
            for (int j = 1; j < i; j++) {
                sum += (S[j] * P[i - j]) % mod;
                sum %= mod;
            }
            S[i] = (P[i] - sum);
            S[i] = S[i] % mod;
        }
        while (S[m] < 0)
            S[m] += mod;
        return (int) S[m];
    }

}
