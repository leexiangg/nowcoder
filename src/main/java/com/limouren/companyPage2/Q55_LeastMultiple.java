package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q55_LeastMultiple {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] number_str = br.readLine().split(" ");
        int[] number = new int[number_str.length];
        for (int i = 0; i < number_str.length; i++) {
            number[i] = Integer.parseInt(number_str[i]);
        }
        System.out.println(calc(number));
    }

    public static int calc(int[] number) {
        int ret = Integer.MAX_VALUE;
        // 5个选3个，只有10种组合形式
        int[][] index = {{0, 1, 2}, {0, 1, 3}, {0, 1, 4}, {0, 2, 3}, {0, 2, 4}, {0, 3, 4}, {1, 2, 3}, {1, 2, 4}, {1, 3, 4}, {2, 3, 4}};
        for (int[] i : index) {
            int a = number[i[0]];
            int b = number[i[1]];
            int c = number[i[2]];
            int ab_divisor = divisor(a, b);
            int ab = a * b / ab_divisor;
            int abc_divisor = divisor(ab, c);
            int abc = ab * c / abc_divisor;
            ret = Math.min(ret, abc);
        }
        return ret;
    }

    /**
     * 辗转相除法：最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int divisor(int a, int b) {
        int tmp = a;
        while((tmp = a % b) > 0) {
            a = b;
            b = tmp;
        }
        return b;
    }
}
