package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 牛牛的背包问题
 * 牛牛准备参加学校组织的春游, 出发前牛牛准备往背包里装入一些零食, 牛牛的背包容量为w。
 * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]。
 * 牛牛想知道在总体积不超过背包容量的情况下,他一共有多少种零食放法(总体积为0也算一种放法)。
 * https://www.nowcoder.com/practice/bf877f837467488692be703735db84e6?tpId=182&&tqId=34326&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：****
 */
public class Q52_NiuBag {

    static int n;
    static long w;
    static int[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nw = br.readLine().split(" ");
        n = Integer.parseInt(nw[0]);
        w = Integer.parseInt(nw[1]);
        v = new int[n];
        long sum = 0;
        String[] v_str = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            v[i] = Integer.parseInt(v_str[i]);
            sum += v[i];
        }
        if(sum <= w)
            System.out.println(1l << n);
        else
            System.out.println(calc(0, 0));
    }

    public static long calc(int i, long sum){
        long ret = 0;
        for(int j = i; j < n; j++){
            long tmp = sum + v[j];
            if(tmp < w) {
                ret ++;
                ret += calc(j + 1, tmp);
            } else if(tmp == w) {
                ret ++;
            }
        }
        return ret;
    }
}
