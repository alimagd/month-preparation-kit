package com.magd.hack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
/*
 * Complete the 'noPrefix' function below.
 *
 * The function accepts STRING_ARRAY words as parameter.
 */

public class NoPrefixSet {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> words = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        noPrefix(words);

        bufferedReader.close();
    }

    public static void noPrefix(List<String> words) {
        // Write your code here
        int d = words.size(), in1, in2;
        String res = "";
        boolean bad = true;
        String word1, word2;
        for (int i = 0; i < words.size() - 1; i++) {
            word1 = words.get(i);
            for (int j = i + 1; j < words.size(); j++) {
                word2 = words.get(j);
                if (word1.contains(word2) || word2.contains(word1)) {
                    in1 = words.indexOf(word1);
                    in2 = words.indexOf(word2);
                    if (Math.abs(in2 - in1) < d) {
                        d = in2 - in1;
                        res = word2;
                    }
                    bad = false;
                    break;
                }
//                if (word2.contains(word1)) {
//                    in1 = words.indexOf(word1);
//                    in2 = words.indexOf(word2);
//                    if (in2 - in1 < d) {
//                        d = in2 - in1;
//                        res = word2;
//                    }
//                    bad = false;
//                    break;
//                }
            }
        }
        if (bad) {
            System.out.println("GOOD SET");
        } else {
            System.out.println("BAD SET");
            System.out.println(res);
        }

    }
}

