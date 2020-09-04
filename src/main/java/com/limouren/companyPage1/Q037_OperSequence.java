package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 操作序列
 * 小易有一个长度为n的整数序列,a_1,...,a_n。然后考虑在一个空序列b上进行n次以下操作:
 * 1、将a_i放入b序列的末尾
 * 2、逆置b序列
 * 小易需要你计算输出操作n次之后的b序列。
 * https://www.nowcoder.com/practice/b53bda356a494154b6411d80380295f5?tpId=182&&tqId=34311&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q037_OperSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] a = br.readLine().split(" ");
        System.out.println(calc(n, a));
    }

    /**
     * 找规律
     * 1 2 3 4 5 6
     * 6 4 2 1 3 5
     * 1 2 3 4 5 6 7
     * 7 5 3 1 2 4 6
     * n为偶数，先偶数位倒序，再奇数位正序
     * n为奇数，先奇数位倒序，再偶数位正序
     * @param n
     * @param a
     * @return
     */
    public static String calc(int n, String[] a) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        if(n % 2 == 0)
            for (int i = 0; i < n; i += 2) {
                sb1.append(a[n - i - 1]).append(" "); // 数组种奇数位倒序
                sb2.append(a[i]).append(" "); // 数组中偶数位正序
            }
        else
            for (int i = 0; i < n; i += 2) {
                sb1.append(a[n - i - 1]).append(" "); // 数组中偶数位倒序
                if (i + 1 < n)
                    sb2.append(a[i + 1]).append(" "); // 数组种奇数位正序
            }
        return sb1.append(sb2).toString().trim();
    }
}

