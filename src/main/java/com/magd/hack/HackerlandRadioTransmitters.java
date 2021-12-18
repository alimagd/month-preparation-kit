package com.magd.hack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
    /*
     * Complete the 'hackerlandRadioTransmitters' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY x
     *  2. INTEGER k
     */


public class HackerlandRadioTransmitters {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = hackerlandRadioTransmitters(x, k);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }

    public static int hackerlandRadioTransmitters(List<Integer> x, int k) {
        // Write your code here

        Collections.sort(x);
        int trans = 0;
        int mid = 0;
        int end = 0;

        for (int i = 0; i < x.size(); ) {

            mid = x.get(i) + k;
            while (i<x.size() && mid >= x.get(i)) i++;
            trans += 1;

            if (i < x.size()) {
                end = x.get(i -= 1) + k;
                while (i < x.size() && x.get(i) <= end) i++;
            }
        }
        return trans;
    }

}

