package com.magd.hack;


import java.util.*;
import java.io.*;

class Node3 {
    Node3 left;
    Node3 right;
    int data;

    Node3(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class BinarySearchTreeLowestCommonAncestor {

    /*
    class Node
        int data;
        Node left;
        Node right;
    */
    public static Node3 lca(Node3 root, int v1, int v2) {
        // Write your code here.
        Node3 arc = root;

        if (arc.data == v1 || arc.data == v2) return arc ;
        if (arc.left!=null && arc.data>v1 && arc.data >v2) return lca(arc.left, v1, v2);
        if (arc.right!=null && arc.data<v1 && arc.data<v2) return lca(arc.right, v1, v2);
        return arc;

    }

//   public static Node3 lca(Node3 root,int v1,int v2)
//    {
//        //Decide if you have to call rekursively
//        //Sammer than both
//        if(root.data < v1 && root.data < v2){
//            return lca(root.right,v1,v2);
//        }
//        //Bigger than both
//        if(root.data > v1 && root.data > v2){
//            return lca(root.left,v1,v2);
//        }
//
//        //Else solution already found
//        return root;
//    }

    public static Node3 insert(Node3 root, int data) {
        if(root == null) {
            return new Node3(data);
        } else {
            Node3 cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node3 root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        Node3 ans = lca(root,v1,v2);
        System.out.println(ans.data);
    }
}
