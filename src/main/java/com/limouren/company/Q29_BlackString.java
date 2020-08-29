package com.limouren.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 暗黑的字符串
 * 一个只包含'A'、'B'和'C'的字符串，如果存在某一段长度为3的连续子串中恰好'A'、'B'和'C'各有一个，那么这个字符串就是纯净的，否则这个字符串就是暗黑的。例如：
 * BAACAACCBAAA 连续子串"CBA"中包含了'A','B','C'各一个，所以是纯净的字符串
 * AABBCCAABB 不存在一个长度为3的连续子串包含'A','B','C',所以是暗黑的字符串
 * 你的任务就是计算出长度为n的字符串(只包含'A'、'B'和'C')，有多少个是暗黑的字符串。
 * https://www.nowcoder.com/practice/7e7ccd30004347e89490fefeb2190ad2?tpId=182&&tqId=34303&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：***
 */
public class Q29_BlackString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(calc4(n));
    }

    /**
     * 列出所有暗黑的字符串，计算量会随着 n 的变大几何倍的增加
     * 用3进制计算，例如 0t0000 = AAAA，0t 代表 3 进制
     * @param n 在大于16后，计算时间大约2秒，每增加1，时间有2次方的增加
     * @return
     */
    public static long calc1(int n) {
        long count = 0;
        long c = (long) Math.pow(3, n);
        for (long i = 0; i < c; i++) {
            boolean flag;
            long tmp = i;
            // 先从尾部拿出两位
            long x1 = tmp % 3, x2 = (int)(tmp / 3) % 3;
            tmp /= 3;
            do {
                tmp /= 3;
                // 拿出第三位
                long x3 = tmp % 3;
                // 如果这个串种包含 ABC 的，记录为true
                flag = x1 != x2 && x1 != x3 && x2 != x3;
                x1 = x2;
                x2 = x3;
            } while(tmp > 0 && !flag);
            if (!flag)
                count++;
        }
        return count * 3;
    }

    /**
     * 数学排列组合法，找暗黑的字符串
     * 规律：
     * 当第 1 位固定后，第 2 位有 3 种填法：第 3 位对应的第 2 位有 3 + 2 + 2 种
     *       ————————————————————————————————
     * 第一位 |A
     *       --------------------------------
     * 第二位 |AA            AB        AC
     *       --------------------------------
     * 第三位 |AAA AAB AAC   ABA ABB   ACA ACC
     *
     * 从第 4 位开始，如果前一位有 3 种排法（AAA AAB AAC），则本位可以有：3 + 2 + 2 种排法
     *              如果前一位有 2 种排法（ABA ABB），则本位可以有：3 + 2 种排法
     *
     * 可以总结出规律，从第 2 位起，就有 3 生 3+2+2，2 生 3+2 的规律
     *
     * 列式：
     * 假设第一位固定一个，只需要让最后的结果 *3 即可
     * f(2) = 3
     * f(3) = 3 + 2 + 2
     * f(4) = (3 + 2 + 2) + (3 + 2) + (3 + 2)
     * …………
     * 只需要迭代统计 3 出现的次数和 2 出现的次数，计算储结果即可
     *
     * @param n < 40
     * @return 注意类型可能会超出 int_max
     */
    public static long calc2(int n) {
        long n3 = 1;
        long n2 = 0;
        while(n-- > 2) {
            long tmp = n3;
            n3 = tmp + n2;
            n2 += 2 * tmp;
        }
        return (n3 * 3 + n2 * 2) * 3;
    }

    /**
     * 数学法找纯净的字符串，用所有排列的总数减去纯净的
     * 指定3个连续的位置为ABC的任意排列，其他位字不管是什么都行，需要排除重复的情形
     * 这个公式不对，@TODO 等待找寻规律
     * 3! * (n-2) * 3^(n-3)
     * 3: 3^3 - 6 * 1 * 3^1 = 21
     * 4: 3^4 - 6 * 2 * 3^2 =
     * @param n < 40
     * @return
     */
    public static long calc3(int n) {
        return (long) (Math.pow(3, n) - 6 * (n - 2) * Math.pow(3, n - 3));
    }

    /**
     * 纯数学找规律
     * 设：f(1) = 3, f(2) = 9, f(x) = 2 * f(x-1) + f(x-2)
     * 斐波那契数列变形
     * @param n < 40
     * @return
     */
    public static long calc4(int n) {
        long ret = 0, f1 = 3, f2 = 9;
        while(n-- > 2) {
            ret = f1 + f2 * 2;
            f1 = f2;
            f2 = ret;
        }
        return ret;
    }
}
