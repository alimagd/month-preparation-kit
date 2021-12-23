package com.magd.hack;

import java.util.Scanner;

public class MyTest {


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String s1 = scan.nextLine();
        String s2 = scan.nextLine();

        for (int i = 0; i < s1.length(); i++) {
            int a = s1.charAt(i) ^ s2.charAt(i);
            System.out.print(a);
        }
    }
}
