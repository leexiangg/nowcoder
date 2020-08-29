package com.limouren.companyPage1;

import java.util.Scanner;

/**
 * 不要二
 * 二货小易有一个W*H的网格盒子，网格的行编号为0~H-1，网格的列编号为0~W-1。每个格子至多可以放一块蛋糕，任意两块蛋糕的欧几里得距离不能等于2。
 * 对于两个格子坐标(x1,y1),(x2,y2)的欧几里得距离为:
 * ( (x1-x2) * (x1-x2) + (y1-y2) * (y1-y2) ) 的算术平方根
 * 小易想知道最多可以放多少块蛋糕在网格盒子里。
 * https://www.nowcoder.com/practice/1183548cd48446b38da501e58d5944eb?tpId=182&&tqId=34290&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q16_NotTwo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int w = scan.nextInt();
            int h = scan.nextInt();
            System.out.println(calc(w, h));
        }
    }

    /**
     * 先在第一个地方放一个，(x+1, y)，(x, y+1)，(x+1, y+1)可以放
     * 	    0	1	2	3	4	5	6	7	8	9
     * 0	⚪	⚪			⚪	⚪			⚪	⚪
     * 1	⚪	⚪			⚪	⚪			⚪	⚪
     * 2			⚪	⚪			⚪	⚪
     * 3			⚪	⚪			⚪	⚪
     * 4	⚪	⚪			⚪	⚪			⚪	⚪
     * 5	⚪	⚪			⚪	⚪			⚪	⚪
     * 6			⚪	⚪			⚪	⚪
     * 7			⚪	⚪			⚪	⚪
     * 8	⚪	⚪			⚪	⚪			⚪	⚪
     * 9	⚪	⚪			⚪	⚪			⚪	⚪
     * @param w
     * @param h
     * @return
     */
    public static String calc(int w, int h) {
        int sum = 0;
        for (int i = 0; i < w; i ++) {
            for (int j = 0; j < h; j ++) {
                if(((i % 4 == 0 || i % 4 == 1) && (j % 4 == 0 || j % 4 == 1)) ||
                        ((i % 4 == 2 || i % 4 == 3) && (j % 4 == 2 || j % 4 == 3))) {
                    sum ++;
                }
            }
        }
        return "" + sum;
    }
}
