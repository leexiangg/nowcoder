package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 安置路灯
 * 小Q正在给一条长度为n的道路设计路灯安置方案。
 * 为了让问题更简单,小Q把道路视为n个方格,需要照亮的地方用'.'表示, 不需要照亮的障碍物格子用'X'表示。
 * 小Q现在要在道路上设置一些路灯, 对于安置在pos位置的路灯, 这盏路灯可以照亮pos - 1, pos, pos + 1这三个位置。
 * 小Q希望能安置尽量少的路灯照亮所有'.'区域, 希望你能帮他计算一下最少需要多少盏路灯。
 * https://www.nowcoder.com/practice/3a3577b9d3294fb7845b96a9cd2e099c?tpId=182&&tqId=34321&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q47_BuildRoadLight {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[] ret = new int[t];
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            char[] s = br.readLine().toCharArray();
            ret[i] = Math.min(calcAsc(n, s), calcDesc(n, s));
        }
        for (int r : ret) {
            System.out.print(r + "\n");
        }
    }

    /**
     * 从前计算一遍
     * @param n
     * @param s
     * @return
     */
    public static int calcAsc(int n, char[] s) {
        int sum = 0, light = 0;
        for (int i = 0; i < n; i++) {
            if(light > 0 || s[i] == '.')
                light ++;
            if(light == 3) {
                sum ++;
                light = 0;
            }
        }
        if(light > 0)
            sum ++;
        return sum;
    }

    /**
     * 从后计算一遍
     * @param n
     * @param s
     * @return
     */
    public static int calcDesc(int n, char[] s) {
        int sum = 0, light = 0;
        for (int i = n - 1; i >= 0; i--) {
            if(light > 0 || s[i] == '.')
                light ++;
            if(light == 3) {
                sum ++;
                light = 0;
            }
        }
        if(light > 0)
            sum ++;
        return sum;
    }
}
