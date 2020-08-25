package com.limouren.company;

import java.util.*;

/**
 * 打怪升级
 * 小易经常沉迷于网络游戏.有一次,他在玩一个打怪升级的游戏,他的角色的初始能力值为 a.在接下来的一段时间内,他将会依次遇见n个怪物,每个怪物的防御力为b1,b2,b3...bn. 如果遇到的怪物防御力bi小于等于小易的当前能力值c,那么他就能轻松打败怪物,并 且使得自己的能力值增加bi;如果bi大于c,那他也能打败怪物,但他的能力值只能增加bi 与c的最大公约数.那么问题来了,在一系列的锻炼后,小易的最终能力值为多少?
 * https://www.nowcoder.com/practice/fe6c73cb899c4fe1bdd773f8d3b42c3d?tpId=182&&tqId=34277&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q3_Level {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            int n = scan.nextInt();
            int a = scan.nextInt();
            for (int i = 0; i < n; i++) {
                int tmp = scan.nextInt();
                a += (a >= tmp ? tmp : divisor(a, tmp));
            }
            System.out.println(a);
        }
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

    /**
     * 穷举法：最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int divisor2(int a, int b) {
        for (int i = Math.min(a, b); i >= 2; i--) {
            if(a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return 1;
    }

    /**
     * 相减法：最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int divisor3(int a, int b) {
        while(a != b) {
            if(a > b) a -= b;
            else b -= a;
        }
        return a;
    }
}