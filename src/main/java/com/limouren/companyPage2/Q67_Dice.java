package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.nowcoder.com/practice/12b1b8ef17e1441f86f322b250bff4c0?tpId=182&&tqId=34341&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 小易在学校中学习了关于字符串的理论, 于是他基于此完成了一个字典的项目。
 * 小易的这个字典很奇特, 字典内的每个单词都包含n个'a'和m个'z', 并且所有单词按照字典序排列。
 * 小易现在希望你能帮他找出第k个单词是什么。
 */
public class Q67_Dice {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nmk = br.readLine().split(" ");
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        long k = Integer.parseInt(nmk[2]);
        for (int i = 0; i <= n; i++) {
            long c = C(i + m, i);
            if(c >= k) {
                for (int j = 0; j < n - i; j++) {
                    sb.append("a");
                }
                calc(i, m, k);
                System.out.println(sb);
                return;
            }
        }
        System.out.println("-1");
    }

    /**
     * 二级制判断法，边界为 2^200 ，天文数字
     * 按第一位是a还是z分成两部分，前一部分的数量为C(n+m-1, n-1)
     * @param n 1 <= n <= 100
     * @param m 1 <= m <= 100
     * @param k 1 <= k <= 10^9
     * @return zzaa
     */
    public static void calc(int n, int m, long k) {
        if(n == 0 && m == 0)
            return;
        if(n == 0) {
            sb.append("z");
            calc(0, m - 1, k);
            return;
        }
        if(m == 0) {
            sb.append("a");
            calc(n - 1, 0, k);
            return;
        }
        long c = C(n + m - 1, n - 1);
        if(k > c) {
            sb.append("z");
            calc(n, m - 1, k - c);
        } else {
            sb.append("a");
            calc(n - 1, m, k);
        }
    }

    /**
     * 数学中组合数量计算公式：C(n, r)
     * @param n
     * @param r
     * @return
     */
    public static long C(int n, int r) {
        long tmp = 1;
        for (int i = 0; i < (Math.min((n - r), r)); i++) {
            tmp = tmp * (n - i) / (i + 1);
        }
        return tmp;
    }
}
