package com.magd.hack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {
    /*
     * Complete the 'solve' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY arr
     *  2. INTEGER_ARRAY queries
     */
    public static int minMax(List<Integer> arr, int d) {
        Deque<Integer> deque = new ArrayDeque<>(d);
        // Initial filling of the deque
        for (int i=0; i<d; i++) {
            addNewElement(deque, arr.get(i));
        }
        int min = deque.peekFirst();

        for (int i=d; i<arr.size(); i++) {
            if (Objects.equals(arr.get(i - d), deque.peekFirst())) deque.removeFirst();

            addNewElement(deque, arr.get(i));

            int max = deque.peekFirst();
            if (max < min) min = max;
        }

        return min;
    }
    // Always put el at the end because it might be the most important el for the series that starts with el itself.
    // Remove all previous el's from the queue that were smaller.
    private static void addNewElement(Deque<Integer> deque, int newEl) {
        while (!deque.isEmpty() && deque.peekLast() < newEl) {
            deque.removeLast();
        }
        deque.offerLast(newEl);
    }
    public static List<Integer> solve(List<Integer> arr, List<Integer> queries) {
        // Write your code here
        List<Integer> res = new ArrayList<>();
        for (Integer q : queries) {
            res.add(minMax(arr, q));
        }
        return res;
    }

}

public class QueriesFixedLength {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> queries = IntStream.range(0, q).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.solve(arr, queries);
        System.out.println(result);

//        bufferedWriter.write(
//                result.stream()
//                        .map(Object::toString)
//                        .collect(joining("\n"))
//                        + "\n"
//        );

        bufferedReader.close();
//        bufferedWriter.close();
    }
}

