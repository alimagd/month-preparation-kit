package com.magd.hack;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

/*
 * Complete the 'minimumMoves' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. STRING_ARRAY grid
 *  2. INTEGER startX
 *  3. INTEGER startY
 *  4. INTEGER goalX
 *  5. INTEGER goalY
 */
public class CastleGrid {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> grid = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int startX = Integer.parseInt(firstMultipleInput[0]);

        int startY = Integer.parseInt(firstMultipleInput[1]);

        int goalX = Integer.parseInt(firstMultipleInput[2]);

        int goalY = Integer.parseInt(firstMultipleInput[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }

    public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {
        // Write your code here
        final int[] X_OFFSETS = { -1, 0, 1, 0 };
        final int[] Y_OFFSETS = { 0, 1, 0, -1 };

        if (startX == goalX && startY == goalY) {
            return 0;
        }

        int size = grid.size();

        int[][] moves = new int[size][size];
        IntStream.range(0, size).forEach(x -> Arrays.fill(moves[x], -1));

        moves[startX][startY] = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY));

        while (true) {
            Point head = queue.poll();
            for (int i = 0; i < X_OFFSETS.length; i++) {
                assert head != null;
                int nextX = head.x;
                int nextY = head.y;

                while (isOpen(grid, nextX + X_OFFSETS[i], nextY + Y_OFFSETS[i])) {
                    nextX += X_OFFSETS[i];
                    nextY += Y_OFFSETS[i];

                    if (nextX == goalX && nextY == goalY) {
                        return moves[head.x][head.y] + 1;
                    }

                    if (moves[nextX][nextY] < 0) {
                        moves[nextX][nextY] = moves[head.x][head.y] + 1;
                        queue.offer(new Point(nextX, nextY));
                    }
                }
            }
        }
    }

    static boolean isOpen(List<String> grid, int x, int y) {
        return x >= 0 && x < grid.size() && y >= 0 && y < grid.size() && grid.get(x).charAt(y) == '.';
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

