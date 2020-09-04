package com.limouren.companyPage1;

import com.limouren.common.Common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 疯狂队列
 * 小易老师是非常严厉的,它会要求所有学生在进入教室前都排成一列,并且他要求学生按照身高不递减的顺序排列。有一次,n个学生在列队的时候,小易老师正好去卫生间了。学生们终于有机会反击了,于是学生们决定来一次疯狂的队列,他们定义一个队列的疯狂值为每对相邻排列学生身高差的绝对值总和。由于按照身高顺序排列的队列的疯狂值是最小的,他们当然决定按照疯狂值最大的顺序来进行列队。现在给出n个学生的身高,请计算出这些学生列队的最大可能的疯狂值。小易老师回来一定会气得半死。
 * https://www.nowcoder.com/practice/d996665fbd5e41f89c8d280f84968ee1?tpId=182&&tqId=34314&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：****
 */
public class Q40_CrazyList {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] hs = br.readLine().split(" ");
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = Integer.parseInt(hs[i]);
        }
        System.out.println(calc(n, h));
    }

    /**
     * 每放置一个数，使结果最大化，最大的放中间，两边是最小的两个，紧接着是次大的两个，紧接着是次小的两个
     * 拿1到10举例：5 7 3 9 1 10 2 8 4 6
     * 最小的挨娘睡
     * 计算过程：
     * 3: x3 + x3 - x1 - x2
     *  = 2x3 - x2 - x1
     * 4: x4 + x4 - x1 - x2 + x3 - x1
     *  = 2x4 + x3 - x2 - 2x1
     * 5: x5 + x5 - x1 - x2 + x4 + x3 - x1 - x2
     *  = 2x5 + x4 + x3 - 2x2 - 2x1
     * 6: x6 + x6 - x1 - x2 + x5 + x4 - x1 - x2 + x5 - x3
     *  = 2x6 + 2x5 + x4 - x3 - 2x2 - 2x1
     * 7: x7 + x7 - x1 - x2 + x6 + x5 - x1 - x2 + x6 + x5 - x3 - x4
     *  = 2x7 + 2x6 + 2x5 - x4 - x3 - 2x2 - 2x1
     * 8: x8 + x8 - x1 - x2 + x7 + x6 - x1 - x2 + x7 + x6 - x3 - x4 + x5 - x3
     *  = 2x8 + 2x7 + 2x6 + x5 - x4 - 2x3 - 2x2 - 2x1
     * 9: x9 + x9 - x1 - x2 + x8 + x7 - x1 - x2 + x8 + x7 - x3 - x4 + x6 + x5 - x3 - x4
     *  = 2x9 + 2x8 + 2x7 + x6 + x5 - 2x4 - 2x3 - 2x2 - 2x1
     * 15
     * 1 1 1 1 1 1 1 500 500 500 500 1000 1000 1000 1000
     * 500 1 500 1 1000 1 1000 1 1000 1 1000 1 500 1 500
     * 从此例子可以看出，可能是以最大的数为中心做计算，也可能以最小的数为中心，所以，计算这两种情况，看哪种比较大就可以了
     * @param n
     * @param h
     * @return
     */
    public static String calc(int n, int[] h) {
        if(n == 1)
            return "0";
        else if (n == 2)
            return "" + Math.abs(h[0] - h[1]);
        Arrays.sort(h);
        int sum = sum(n, h);
        // 反转列表再算一次
        for (int i = 0; i < (n + 1) / 2 ; i++) {
            int tmp = h[i];
            h[i] = h[n - i - 1];
            h[n - i - 1] = tmp;
        }
        int tmp = sum(n, h);
        sum = Math.max(sum, tmp);
        return "" + sum;
    }

    public static int sum(int n, int[] h) {
        int sum = 0;
        // 分 3 种情况讨论，偶数 / 除4余1 / 除4余3
        for (int i = 0; i < n / 2 - 1; i++) {
            sum += Math.abs(h[n - i - 1] * 2 - h[i] * 2);
        }
        if(n % 2 == 0) {
            sum += Math.abs(h[n / 2] - h[n / 2 - 1]);
        } else if(n % 4 == 1) {
            sum += Math.abs(h[n / 2 + 1] + h[n / 2] - h[n / 2 - 1] * 2);
        } else {
            sum += Math.abs(h[n / 2 + 1] * 2 - h[n / 2] - h[n / 2 - 1]);
        }
        return sum;
    }

    /**
     * 可以用全排列然后计算每一组的疯狂值，但是要做 50 个数的全排列，肯定是不现实的
     * 先用全排列的方式找找规律
     * 5
     * 1 3 6 10 15  sum = 35, avg = 7
     * 6 10 1 15 3 = 39
     * 5
     * 1 6 10 13 15  sum = 35, avg = 7
     * 13 6 15 1 10 = 39
     * 6
     * 1 3 6 10 15 21  sum = 56, avg = 23/3
     * 10 3 15 1 21 6 = 68
     * 6
     * 1 7 12 16 19 21  sum = 56, avg = 23/3
     * 16 7 19 1 21 12 = 68
     * 7
     * 1 3 6 10 15 21 28  sum = 84, avg = 12
     * 10 15 3 21 1 28 6 = 104
     * 7
     * 1 8 14 19 23 26 28  sum = 84, avg = 12
     * 23 14 26 8 28 1 19 = 104
     * 8
     * 1 3 6 10 15 21 28 36  sum = 120, avg = 15
     * 15 6 21 3 28 1 36 10 = 155
     * 8
     * 1 9 16 22 27 31 34 36  sum = 120, avg = 15
     * 27 16 31 9 34 1 36 22 = 155
     * 10
     * 10 100 1000 10000 30000 70000 100000 200000 400000 800000  sum = 1611110, avg = 161111
     * 70000 10000 100000 1000 200000 100 400000 10 800000 30000 = 3017780
     * 30000 100000 1000 400000 10 800000 100 200000 10000 70000 = 3017780
     * 10
     * 1 2 3 4 5 6 7 8 9 10000  sum = 10045, avg = 1004.5
     * 6 4 7 3 8 2 9 1 10000 5 = 20029
     * 5 7 3 9 1 10000 2 8 4 6
     * 规律：
     * 每个数组都有多种排列方式，但是有一种是可以总结为规律的
     * 1、先找最大的放在中间
     * 2、两边放最小的和第二小的
     * 3、再两边放第二第三大的
     * 4、依次放到最后
     * 总结为：每放置一个数，使结果最大化
     * @return
     */
    public static String test(int n, int[] h) {
        int[][] rank = Common.rank(h);
        int index = 0;
        int max = 0;
        for (int i = 0; i < rank.length; i++) {
            int sum = 0;
            for (int j = 1; j < rank[i].length; j++) {
                sum += Math.abs(rank[i][j] - rank[i][j - 1]);
            }
            if(sum > max) {
                max = sum;
                index = i;
            }
        }
        System.out.println(max);
        Common.printArray(rank[index]);
        return "";
    }
}
