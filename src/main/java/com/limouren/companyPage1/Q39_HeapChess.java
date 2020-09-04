package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 堆棋子
 * 小易将n个棋子摆放在一张无限大的棋盘上。第i个棋子放在第x[i]行y[i]列。同一个格子允许放置多个棋子。每一次操作小易可以把一个棋子拿起并将其移动到原格子的上、下、左、右的任意一个格子中。小易想知道要让棋盘上出现有一个格子中至少有i(1 ≤ i ≤ n)个棋子所需要的最少操作次数.
 * https://www.nowcoder.com/practice/27f3672f17f94a289f3de86b69f8a25b?tpId=182&&tqId=34313&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：****
 */
public class Q39_HeapChess {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] xs = br.readLine().split(" ");
        String[] ys = br.readLine().split(" ");
        int[] x = new int[n], y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(xs[i]);
            y[i] = Integer.parseInt(ys[i]);
        }
        System.out.println(calc(n, x, y));
    }

    /**
     * 固定一个位置，计算其他棋子过来的最少步数，把步数先排序，取前 n 个
     * n 个点的最近距离，一定是在这几个点的横竖的这几条线上
     * calc1里面的步骤是通过选取其中 n 个点，会出现计算量过大
     * @param n
     * @param x
     * @param y
     * @return
     */
    public static String calc(int n, int[] x, int[] y) {
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = Integer.MAX_VALUE;
        }
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                int[] tmp = new int[n];
                for (int i = 0; i < n; i++) {
                    tmp[i] = Math.abs(x[i] - x[k]) + Math.abs(y[i] - y[l]);
                }
                Arrays.sort(tmp);
                int s = 0;
                for (int i = 0; i < n; i++) {
                    s += tmp[i];
                    sum[i] = Math.min(s, sum[i]);
                }
            }
        }
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < n; i++) {
            ret.append(sum[i]).append(" ");
        }
        return ret.toString().trim();
    }

    /**
     * 固定一个位置，让其他的棋子过来找这个棋子，找出最少步数
     * 1、计算出 min(x),min(y) ~ max(x),max(y) 区域内，xy点到任一点的距离
     * 2、找 i 个距离的和最小的
     * 复杂度非常高
     * 10
     * 1 6 11 16 21 26 31 36 41 46
     * 46 41 36 31 26 21 16 11 6 1
     * 这个案例大约 2 秒
     * 点数更多一些，或者范围更大一些，就无能为力了
     * @param n
     * @param x
     * @param y
     * @return
     */
    public static String calc1(int n, int[] x, int[] y) {
        StringBuilder ret = new StringBuilder();
        ret.append("0 ");
        // 找到 min(x),min(y) ~ max(x),max(y)
        int minx = Arrays.stream(x).min().getAsInt();
        int maxx = Arrays.stream(x).max().getAsInt();
        int miny = Arrays.stream(y).min().getAsInt();
        int maxy = Arrays.stream(y).max().getAsInt();
        int[][][] xy = new int[maxx - minx + 1][maxy - miny + 1][n];
        for (int k = minx; k <= maxx; k++) {
            for (int l = miny; l <= maxy; l++) {
                for (int i = 0; i < n; i++) {
                    xy[k - minx][l - miny][i] = Math.abs(x[i] - k) + Math.abs(y[i] - l);
                }
            }
        }
        // 每i颗棋子的距离和
        for (int i = 2; i < n + 1; i++) {
            int sum = Integer.MAX_VALUE;
            for (int j = 0; j < xy.length; j++) {
                for (int k = 0; k < xy[j].length; k++) {
                    // 找出 xy 中所有 i 个数的组合方式
                    int[][] r = combination(xy[j][k], i);
                    // 找出这些组合方式中最小的距离
                    for (int l = 0; l < r.length; l++) {
                        sum = Math.min(Arrays.stream(r[l]).sum(), sum);
                    }
                }
            }
            ret.append(sum).append(" ");
        }
        return ret.toString().trim();
    }

    /**
     * 从不重复的数组 a 中找 n 个数的组合
     * @param a
     * @param n
     * @return
     */
    public static int[][] combination(int[] a, int n) {
        int count = 1;
        for (int i = 1; i <= n; i++) {
            count = count * (a.length - i + 1) / i;
        }
        int[][] ret = new int[count][n];
        int index = 0;
        // 如果 n 只有 1 ，则把 a 队列铺平返回
        if(n == 1) {
            for (int i = 0; i < a.length; i++) {
                ret[i][0] = a[i];
            }
            return ret;
        }
        // 从剩下的里面取一个
        for (int i = 0; i < a.length; i++) {
            int[] combination = new int[a.length - i - 1];
            System.arraycopy(a, i + 1, combination, 0, a.length - i - 1);
            // 把这个后面的继续做组合
            int[][] tmp = combination(combination, n - 1);
            // 把取出来的这一个合并到所有组合的开头
            for (int j = 0; j < tmp.length; j++) {
                int[] inner = new int[tmp[j].length + 1];
                inner[0] = a[i];
                System.arraycopy(tmp[j], 0, inner, 1, tmp[j].length);
                ret[index ++] = inner;
            }
        }
        return ret;
    }
}
