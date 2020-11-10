package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 数轴
 * 牛牛非常喜欢和朋友们一起玩。
 * 牛牛有n个朋友当前在一根数轴上,每个朋友当前在整数x[i]坐标位置。
 * 牛牛向他们发出一个移动的信号,每个朋友就向左或者向右移动s距离(每个朋友的选择是独立的,都可以选择向左或者向右)。
 * 为了在一起玩耍方便,牛牛希望移动之后最左边的朋友和最右边的朋友距离最近,牛牛想知道最近距离为多少。
 * 例如牛牛有三个朋友分别所在数轴坐标为-7, 4, 7, s = 5
 * 那么第一个朋友-7向右移动s,变为-2
 * 第二个朋友4向左移动s,变为-1
 * 第三个朋友7向左移动s,变为2。
 * 现在最左和最右的朋友距离是4,没有比这个更优的方案了。
 * https://www.nowcoder.com/practice/ff0e55dcb75b45b09164c56f87cdf737?tpId=182&&tqId=34333&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：****
 */
public class Q59_NumberAxis {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ns = br.readLine().split(" ");
        int n = Integer.parseInt(ns[0]);
        int s = Integer.parseInt(ns[1]);
        String[] x_str = br.readLine().split(" ");
        int[] x = new int[x_str.length];
        for (int i = 0; i < x_str.length; i++) {
            x[i] = Integer.parseInt(x_str[i]);
        }
        System.out.println(calc(n, s, x));
    }

    /**
     * @param s
     * @param x
     * @return
     */
    public static int calc(int n, int s, int[] x) {
        Arrays.sort(x);
        int  left, right, dist = x[n - 1] - x[0];
        for (int i = 0; i < n - 1; i++) {
            right = Math.max(x[i] + s, x[n - 1] - s);
            left = Math.min(x[i + 1] - s, x[0] + s);
            dist = Math.min(dist, right - left);
        }
        return dist;
    }
}
