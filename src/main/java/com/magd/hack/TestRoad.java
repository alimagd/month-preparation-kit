package com.magd.hack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

public class TestRoad {
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

        Graph graph = new Graph(n);

        for (List<Integer> list : cities) {
            graph.addEdge(list.get(0), list.get(1));
        }

        if (c_lib <= c_road || cities.size() == 0) {
            System.out.println(c_lib * n);
            return (long) c_lib * n;
        } else {
            boolean[] visited = new boolean[n];
            long cost = 0;

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int roads = graph.dfs(i, visited) - 1;

                    cost += (long) roads * c_road;
                    cost += c_lib;
                }
            }
            return cost;
        }

    }
    static class Graph{
        List<List<Integer>> vertices;

        public Graph(int count){
            vertices = new ArrayList<>(count);

            for(int i = 0; i < count; i++){
                vertices.add(i, new ArrayList<>());
            }
        }

        public void addEdge(int source, int destination){
            vertices.get(source).add(destination);
            vertices.get(destination).add(source);
        }

        public int dfs(int source, boolean[] visited){
            visited[source] = true;
            int roads = 1;

            //System.out.print(source+" ");

            for(List<Integer> vertex: vertices){
                if(!visited[vertex.get(0)]){
                    roads += dfs(vertex.get(1), visited);
                }
            }

            return roads;
        }
    }


}
