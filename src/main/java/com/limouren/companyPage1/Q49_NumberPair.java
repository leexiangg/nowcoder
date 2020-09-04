package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 数对
 * 牛牛以前在老师那里得到了一个正整数数对(x, y), 牛牛忘记他们具体是多少了。
 * 但是牛牛记得老师告诉过他x和y均不大于n, 并且x除以y的余数大于等于k。
 * 牛牛希望你能帮他计算一共有多少个可能的数对。
 * https://www.nowcoder.com/practice/bac5a2372e204b2ab04cc437db76dc4f?tpId=182&&tqId=34323&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：****
 */
public class Q49_NumberPair {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        System.out.println(calc3(n, k));
    }

    /**
     * 暴力法
     * @param n
     * @param k
     * @return
     */
    public static int calc(int n, int k) {
        int sum = 0;
        for (int x = k; x <= n; x++) {
            for (int y = k; y <= n; y++) {
                if(x != y && x % y >= k) {
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * 跳跃法
     * @param n
     * @param k
     * @return
     */
    public static int calc1(int n, int k) {
        int sum = 0;
        for (int y = k + 1; y <= n; y++) {
            sum += y - k;
            for (int x = y + 1; x <= n; x++) {
                if(x % y >= k) {
                    sum++;
                } else {
                    if(x < 2 * y)
                        x = y + k - 1;
                    else
                        x += k - 1;
                }
            }
        }
        return sum;
    }

    /**
     * 暴力法
     * 过滤一部分计算
     * 1、x < k 的不用计算
     * 2、y <= k 的不用计算
     * 3、x < y 的一定符合要求
     * @param n
     * @param k
     * @return
     */
    public static long calc2(int n, int k) {
        long sum = 0;
        for (int x = k; x <= n; x++) {
            sum += n - x;
            if(x > 2 * k)
            for (int y = k; y <= x; y++) {
                if(x % y >= k) {
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * 计算法
     * @param n
     * @param k
     * @return
     */
    public static long calc3(int n, int k) {
        long sum = 0;
        if(k == 0)
            sum = (long)n * (long)n;
        else
            for (int y = k + 1; y <= n; y++) {
                sum += ((n - y) / y + 1) * (y - k);
                int tmp = n % y - k + 1;
                if(tmp > 0)
                    sum += tmp;
            }
        return sum;
    }
}
