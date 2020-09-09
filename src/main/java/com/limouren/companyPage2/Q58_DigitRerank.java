package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 数位重排
 * 牛牛有一个正整数x,牛牛需要把数字x中的数位进行重排得到一个新数(不同于x的数),牛牛想知道这个新数是否可能是原x的倍数。请你来帮他解决这个问题。
 * https://www.nowcoder.com/practice/f970201e9f7e4040ab25a40918e27d15?tpId=182&&tqId=34332&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 */
public class Q58_DigitRerank {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < t; i++) {
            ret.append(calc(br.readLine())).append("\n");
        }
        System.out.println(ret);
    }

    public static String calc(String str) {
        char[] chars = str.toCharArray();
        char[][] rank = rank(chars);
        int x = Integer.parseInt(str);
        for (int i = 0; i < rank.length; i++) {
            int r = Integer.parseInt(String.valueOf(rank[i]));
            if(r % x == 0 && r != x) {
                return "Possible";
            }
        }
        return "Impossible";
    }

    /**
     * 递归法，对一个完全不重复的数组做全排列
     * @param a
     * @return a.length! 个数组
     * 1,2,3,4,5
     */
    public static char[][] rank(char[] a) {
        int count = 1;
        for (int i = 1; i <= a.length; i++) {
            count *= i;
        }
        char[][] ret = new char[count][a.length];
        int index = 0;
        if(a.length == 1) {
            return new char[][]{a};
        }
        char first = a[0];
        char[] tmp = Arrays.copyOfRange(a, 1, a.length);
        char[][] rank = rank(tmp);
        // 把第一个数合并到返回的数组中
        for (int i = 0; i < rank.length; i++) {
            for (int j = 0; j <= rank[i].length; j++) {
                char[] inner = new char[rank[i].length + 1];
                System.arraycopy(rank[i], 0, inner, 0, j);
                inner[j] = first;
                System.arraycopy(rank[i], j, inner, j + 1, rank[i].length - j);
                ret[index++] = inner;
            }
        }
        return ret;
    }
}
