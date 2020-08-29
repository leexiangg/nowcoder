package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 最大奇约数
 * 小易是一个数论爱好者，并且对于一个数的奇数约数十分感兴趣。一天小易遇到这样一个问题： 定义函数f(x)为x最大的奇数约数，x为正整数。 例如:f(44) = 11.
 * 现在给出一个N，需要求出 f(1) + f(2) + f(3).......f(N)
 * 例如： N = 7
 * f(1) + f(2) + f(3) + f(4) + f(5) + f(6) + f(7) = 1 + 1 + 3 + 1 + 5 + 3 + 7 = 21
 * 小易计算这个问题遇到了困难，需要你来设计一个算法帮助他。
 * https://www.nowcoder.com/practice/49cb3d0b28954deca7565b8db92c5296?tpId=182&&tqId=34305&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 1 ≤ N ≤ 1000000000
 * 难度：***
 */
public class Q31_MaxNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(calc1(n));
    }

    /**
     * 最大奇约数，就是一直除以2，直到是奇数为止
     * @param n
     * @return
     */
    public static long calc(int n) {
        long ret = 0;
        if(n % 2 == 1) {
            ret += n;
            n -= 1;
        }
        for (int i = 1; i < n; i += 2) {
            int tmp = i + 1;
            while(tmp % 2 == 0) {
                tmp >>= 1;
            }
            ret += tmp + i;
        }
        return ret;
    }

    /**
     * 网友的巧妙思路
     * 1、如果 n 为奇数，小于等于 n 的所有奇数挑出来，等差数列 ((n + 1) / 2) ^ 2
     * 2、如果 n 为偶数，所有的数除以二，得到另一个连续的数列，继续执行第一步
     * 3、i 为偶数时，(i + 1) / 2 = i / 2
     * @param n
     * @return
     */
    public static long calc1(int n) {
        long sum = 1;
        do {
            long tmp = (n + 1) >> 1;
            sum += tmp * tmp;
        } while((n >>= 1) > 1);
        return sum;
    }

    /**
     * 传统办法
     * 复杂度太高
     * @param n
     * @return
     */
    public static long calc2(int n) {
        long ret = 0;
        for (int i = 1; i <= n; i++) {
            ret += maxDivisor(i);
        }
        return ret;
    }

    /**
     * 最大奇约数
     * @return
     */
    public static int maxDivisor(int n) {
        if(n % 2 == 1)
            return n;
        for (int i = (int) Math.sqrt(n); i >= 1; i--) {
            double tmp = (double) n / i;
            if (tmp % 1 == 0 && tmp % 2 == 1) {
                return (int) tmp;
            }
        }
        return 1;
    }
}
