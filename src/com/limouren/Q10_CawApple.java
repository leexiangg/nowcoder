package com.limouren;

import java.util.Scanner;

/**
 * 奶牛分苹果
 * n 只奶牛坐在一排，每个奶牛拥有 ai 个苹果，现在你要在它们之间转移苹果，使得最后所有奶牛拥有的苹果数都相同，每一次，你只能从一只奶牛身上拿走恰好两个苹果到另一个奶牛上，问最少需要移动多少次可以平分苹果，如果方案不存在输出 -1。
 * https://www.nowcoder.com/practice/a174820de48147d489f64103af152709?tpId=182&&tqId=34284&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q10_CawApple {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            int n = scan.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = scan.nextInt();
            }
            System.out.println(calc(a));
        }
    }

    public static String calc(int[] a) {
        int sum = 0;
        boolean flag = true;
        for (int i = 0; i < a.length; i ++) {
            sum += a[i];
            // 如果不是每两个之间相差偶数，则不能平分
            if(i > 0 && (a[i] - a[i - 1]) % 2  != 0) {
                flag = false;
            }
        }
        // 如果总数就不能平分，白搭
        if(!flag || sum % a.length > 0) {
            return "" + -1;
        }
        int avg = sum/a.length;
        int count = 0;
        for (int ai : a) {
            count += Math.abs(ai - avg) / 2;
        }
        return "" + count / 2;
    }
}
