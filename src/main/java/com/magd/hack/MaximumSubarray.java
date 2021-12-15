package com.magd.hack;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'maxSubarray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> maxSubarray(List<Integer> arr) {
        // Write your code here
        List<Integer> result = new ArrayList<>();
        int max = arr.stream().max(Comparator.naturalOrder()).get();

        List<Integer> res= new ArrayList<>();

        int max_sum = arr.get(0);
        int temp_sum = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            temp_sum += arr.get(i);
            if (temp_sum > max_sum) max_sum = temp_sum;
            else {
                if (temp_sum < 0)
                    temp_sum = 0;
            }
        }
        result.add(max_sum);


        List<Integer> sums = new ArrayList<>();
        sums.add(0, arr.get(0));
        for (int i = 1; i < arr.size(); i++) {
            sums.add(i, Math.max(sums.get(i - 1), Math.max(arr.get(i), sums.get(i - 1) + arr.get(i))));
        }
        result.add(sums.get(arr.size() - 1));

        if (arr.size() == 1 || max <= 0) {
            res.add(max);
            res.add(max);
            return res;
        }else
            return result;
    }
}


public class MaximumSubarray {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<Integer> result = Result.maxSubarray(arr);
                System.out.println(result);

//                bufferedWriter.write(
//                        result.stream()
//                                .map(Object::toString)
//                                .collect(joining(" "))
//                                + "\n"
//                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
