package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 优雅的点
 * 小易有一个圆心在坐标原点的圆，小易知道圆的半径的平方。小易认为在圆上的点而且横纵坐标都是整数的点是优雅的，小易现在想寻找一个算法计算出优雅的点的个数，请你来帮帮他。
 * 例如：半径的平方如果为25
 * 优雅的点就有：(+/-3, +/-4), (+/-4, +/-3), (0, +/-5) (+/-5, 0)，一共12个点。
 * https://www.nowcoder.com/practice/0960cb46233b446687b77facc9148b89?tpId=182&&tqId=34301&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q27_ElegantPoint {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int r_sqrt = Integer.parseInt(br.readLine());
        int ret = 0;
        int tmp = (int) Math.sqrt(r_sqrt);
        for (int i = 0; i < tmp; i++) {
            double j = Math.sqrt(r_sqrt - i * i);
            if(j % 1 == 0) {
                tmp = (int)j;
                if(i == 0 || i == j)
                    ret += 4;
                else
                    ret += 8;
            }
        }
        System.out.println(ret);
    }
}
