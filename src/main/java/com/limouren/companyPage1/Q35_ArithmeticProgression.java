package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 等差数列
 * 如果一个数列S满足对于所有的合法的i,都有S[i + 1] = S[i] + d, 这里的d也可以是负数和零,我们就称数列S为等差数列。
 * 小易现在有一个长度为n的数列x,小易想把x变为一个等差数列。小易允许在数列上做交换任意两个位置的数值的操作,并且交换操作允许交换多次。但是有些数列通过交换还是不能变成等差数列,小易需要判别一个数列是否能通过交换操作变成等差数列
 * https://www.nowcoder.com/practice/e11bc3a213d24fc1989b21a7c8b50c3f?tpId=182&&tqId=34309&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q35_ArithmeticProgression {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] xs = br.readLine().split(" ");
        final int[][] x = {new int[n]};
        for (int i = 0; i < n; i++) {
            x[0][i] = Integer.parseInt(xs[i]);
        }
        Arrays.sort(x[0]);
        int tmp = x[0][1] - x[0][0];
        for (int i = 2; i < n; i++) {
            if (tmp != x[0][i] - x[0][i - 1]) {
                System.out.println("Impossible");
                return;
            }
        }
        System.out.println("Possible");
    }
}
