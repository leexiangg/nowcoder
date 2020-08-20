package com.limouren;

import java.util.*;

/**
 * 地牢逃脱
 * 给定一个 n 行 m 列的地牢，其中 '.' 表示可以通行的位置，'X' 表示不可通行的障碍，牛牛从 (x0 , y0 ) 位置出发，遍历这个地牢，和一般的游戏所不同的是，他每一步只能按照一些指定的步长遍历地牢，要求每一步都不可以超过地牢的边界，也不能到达障碍上。地牢的出口可能在任意某个可以通行的位置上。牛牛想知道最坏情况下，他需要多少步才可以离开这个地牢。
 * https://www.nowcoder.com/practice/0385945b7d834a99bc0010e67f892e38?tpId=182&&tqId=34281&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：***
 */
public class Q7_Dungeon {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            char[][] points = new char[n][m];
            for (int i = 0; i < n; i++) {
                points[i] = scan.next().toCharArray();
            }
            Point start = new Point(scan.nextInt(), scan.nextInt());
            int k = scan.nextInt();
            int[][] step = new int[k][2];
            for (int i = 0; i < k; i++) {
                step[i][0] = scan.nextInt();
                step[i][1] = scan.nextInt();
            }
            System.out.println(calc(points, start, step));
        }
    }

    public static String calc(char[][] points, Point start, int[][] step) {
        // 记录已经搜索过的点，没搜索一级，多记录一个数
        int[][] tmp = new int[points.length][points[0].length];
        // 起始点已经搜索过
        tmp[start.x][start.y] = 1;
        // 记录最长步长
        int max = 0;
        // 遍历队列
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        // 开始搜索
        while(queue.size() > 0) {
            // 从队列头取出一个点
            Point point = queue.remove();
            // 从这个点出发能到什么地方
            for (int i = 0; i < step.length; i++) {
                int x = point.x + step[i][0];
                int y = point.y + step[i][1];
                // 如果能到这个点，且这个点没被走过，且这个点是“.”，则把这个点加到遍历队列，为这个点做标记
                if(x >= 0 && x < tmp.length
                        && y >= 0 &&  y < tmp[0].length
                        && tmp[x][y] == 0
                        && points[x][y] == '.') {
                    queue.add(new Point(x, y));
                    tmp[x][y] = tmp[point.x][point.y] + 1;
                }
            }
        }
        // 检查是否有0的“.”，如果有，则返回-1，如果没有，则返回步数最大的-1，因为起始点也记录了1步
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[0].length; j++) {
                if(tmp[i][j] == 0 && points[i][j] == '.') {
                    max = -1;
                    break;
                } else if(tmp[i][j] > max) {
                    max = tmp[i][j] - 1;
                }
            }
        }
        return "" + max;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}

