package com.limouren.fortuitous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 求一个数的阶乘
 */
public class Q1_Factorial {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(factorial(n));
        System.out.println(count(n));
    }

    /**
     * 求n的阶乘
     * @param n!
     * @return 阶乘
     */
    public static String factorial(int n) {
        StringBuilder sb = new StringBuilder();
        long[] a = new long[10000];
        a[0] = 1;
        int i, j, m = 0;
        long c;
        for(i = 1; i <= n; i++) {
            c = 0;
            for(j = 0; j <= m; j++) {
                a[j] = a[j] * i + c;
                c = a[j] / 10000;
                a[j] = a[j] % 10000;
            }
            if(c > 0) {
                m++;
                a[m] = c;
            }
        }
        sb.append(a[m]);
        for(i = m - 1; i >= 0; i--)
            sb.append(String.format("%04d", a[i]));
        return sb.toString();
    }

    /**
     * 求n的阶乘的位数
     * 斯特林(Stirling)公式
     * @param n!
     * @return 位数
     */
    public static int count(int n) {
        int s = 1;
        if(n > 3)
            s = (int) (Math.log10(2 * Math.PI * n) / 2 + n * Math.log10(n / Math.E) + 1);
        return s;
    }
}
