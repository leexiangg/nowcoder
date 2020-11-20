package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.nowcoder.com/practice/ad11db28e0f44a1c97af06fd002477a1?tpId=182&&tqId=34344&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 给定一个N*M的矩阵，在矩阵中每一块有一张牌，我们假定刚开始的时候所有牌的牌面向上。
 * 现在对于每个块进行如下操作：
 * > 翻转某个块中的牌，并且与之相邻的其余八张牌也会被翻转。
 * XXX
 * XXX
 * XXX
 * 如上矩阵所示，翻转中间那块时，这九块中的牌都会被翻转一次。
 * 请输出在对矩阵中每一块进行如上操作以后，牌面向下的块的个数。
 */
public class Q70_Turn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String[] nm = br.readLine().split(" ");
            long n = Long.parseLong(nm[0]) - 2;
            long m = Long.parseLong(nm[1]) - 2;
            if(n == -1 && m == -1) {
                sb.append("1\n");
            } else if(n == -1) {
                sb.append(m).append("\n");
            } else if(m == -1) {
                sb.append(n).append("\n");
            } else {
                sb.append(n * m).append("\n");
            }
        }
        System.out.println(sb);
    }

    /**
     * 用于找规律的
     * 1 0 1 2 3 4 5 6 7
     * 0 0 0 0 0 0 0 0 0
     * 1 0 1 2 3 4 5 6 7
     * 2 0 2 4 6 8 10 12 14
     * 3 0 3 6 9 12 15 18 21
     * 4 0 4 8 12 16 20 24 28
     * 5 0 5 10 15 20 25 30 35
     * 6 0 6 12 18 24 30 36 42
     * 7 0 7 14 21 28 35 42 49
     * @param n
     * @param m
     * @return
     */
    public static int calc(int n, int m) {
        boolean[][] nm = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nm[i][j] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if(i + k >= 0 && i + k < n && j + l >= 0 && j + l < m) {
                            nm[i + k][j + l] = !nm[i + k][j + l];
                        }
                    }
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!nm[i][j])
                    ret ++;
            }
        }
        return ret;
    }
}
