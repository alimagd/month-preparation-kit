package com.magd.hack;


import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
    /*
     * Complete the 'roadsAndLibraries' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER c_lib
     *  3. INTEGER c_road
     *  4. 2D_INTEGER_ARRAY cities
     */
public class RoadsLibraries {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int c_lib = Integer.parseInt(firstMultipleInput[2]);

                int c_road = Integer.parseInt(firstMultipleInput[3]);

                List<List<Integer>> cities = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        cities.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                long result = roadsAndLibraries(n, c_lib, c_road, cities);
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
        public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
            // Write your code here

            long cost = 0;
            if (n==1) return c_lib;

            for (int i = 0; i < cities.size() - 1; i++) {

                for (int j = i + 1; j < cities.size(); j++) {
                    if (hasShare(cities.get(i), cities.get(j))) {
                        cities.get(i).addAll(cities.get(j));
                        cities.get(j).clear();
                    }
                }
            }
            int rs = 0; // numbers of connectable areas of cities
            Set<Integer> set = new HashSet<>();
            for (List<Integer> list : cities) {
                if (!list.isEmpty()){
                    rs++;
                    set.addAll(list);
                }
            }
            int cits = n - set.size(); // numbers of cities are alone(without roads)

            System.out.println("cit = " + cits);


            if (c_lib < c_road) {
                cost = (long) n * c_lib;
            }else{

                long roadsCost = (long) c_road * rs;
//                cost = (rs + cit)*c_lib + (n-(rs-1))
//
//
//                cost = (long) (num+1 ) *c_lib + (long) (n-num-1) * c_road;

            }
            return cost;
        }

        static boolean hasShare(List<Integer> l1, List<Integer> l2) {
            for (Integer in : l1) {
                if (l2.contains(in)) return true;
            }
            return false;
        }
}

