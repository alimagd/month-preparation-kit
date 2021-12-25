package com.magd.hack;


import java.util.PriorityQueue;
import java.util.Scanner;

public class QHEAP1 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int Q = scanner.nextInt();
        PriorityQueue<Integer> heapQueue = new PriorityQueue<>();
        while (Q > 0) {
            int query = scanner.nextInt();
            int minimum;
            switch (query) {
                case 1:
                    //insert data
                    heapQueue.add(scanner.nextInt());
                    break;
                case 2:
                    //delete data
                    heapQueue.remove((Object)scanner.nextInt());
                    break;
                case 3:
                    if (!heapQueue.isEmpty()) {
                        minimum = heapQueue.peek();
                        System.out.println(minimum);
                    }
                    break;
            }
            Q--;
        }
    }
}
