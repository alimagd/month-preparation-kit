package com.magd.hack;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int c = in.nextInt();
            int r = in.nextInt();
            long cl = in.nextLong();
            long cr = in.nextLong();

            Graph graph = new Graph(c);

            for(int a1 = 0; a1 < r; a1++){
                int city_1 = in.nextInt();
                int city_2 = in.nextInt();

                // start index from 0
                graph.addEdge(city_1-1,city_2-1);
            }

            // if number of roads is 0 or cost of building a library is less than or equal to cost of building a road
            // minimum cost will be building libraries in all the cities
            if(cl <= cr || r == 0){
                System.out.println(cl*c);
            }
            else{
                boolean[] visited = new boolean[c];
                long cost = 0;

                // there may be more than one unconnected cluster
                for(int i = 0; i < c; i++){
                    if(!visited[i]){

                        //System.out.print("Cluster: ");

                        // number of roads will be one less than the vertices traversed during DFS
                        int roads = graph.dfs(i, visited)-1;

                        //System.out.println("");
                        //System.out.println("Roads: "+roads);

                        // cost will be building all the roads in the cluster and a library
                        cost += roads*cr;
                        cost += cl;
                    }
                }

                System.out.println(cost);
            }
        }
    }
}

class Graph{
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

