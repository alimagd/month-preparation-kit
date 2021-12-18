package com.magd.hack;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
/*
 * Complete the 'equalStacks' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER_ARRAY h1
 *  2. INTEGER_ARRAY h2
 *  3. INTEGER_ARRAY h3
 */

public class EqualStacks {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n1 = Integer.parseInt(firstMultipleInput[0]);

        int n2 = Integer.parseInt(firstMultipleInput[1]);

        int n3 = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> h1 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> h2 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> h3 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = equalStacks(h1, h2, h3);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3){
        // Write your code here
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int[] sum = new int[3];
        map.put(0, h1);
        map.put(1, h2);
        map.put(2, h3);

        sum[0] = h1.stream().mapToInt(integer -> integer).sum();
        sum[1] = h2.stream().mapToInt(integer -> integer).sum();
        sum[2] = h3.stream().mapToInt(integer -> integer).sum();

        int best, j;

        while (!((sum[0] == sum[1]) && (sum[1] == sum[2]))) {
            best = Math.max(Math.max(sum[0], sum[1]), sum[2]);
            if (best == sum[0])
                j = 0;
            else if (best == sum[1])
                j = 1;
            else j = 2;
          sum[j] -= map.get(j).get(0);
          map.get(j).remove(0);
        }
        return sum[0];
    }
}
