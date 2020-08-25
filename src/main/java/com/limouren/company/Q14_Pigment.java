package com.limouren.company;

import com.limouren.common.Common;

import java.util.*;

/**
 * 混合颜料
 * 你就是一个画家！你现在想绘制一幅画，但是你现在没有足够颜色的颜料。为了让问题简单，我们用正整数表示不同颜色的颜料。你知道这幅画需要的n种颜色的颜料，你现在可以去商店购买一些颜料，但是商店不能保证能供应所有颜色的颜料，所以你需要自己混合一些颜料。混合两种不一样的颜色A和颜色B颜料可以产生(A XOR B)这种颜色的颜料(新产生的颜料也可以用作继续混合产生新的颜色,XOR表示异或操作)。本着勤俭节约的精神，你想购买更少的颜料就满足要求，所以兼职程序员的你需要编程来计算出最少需要购买几种颜色的颜料？
 * https://www.nowcoder.com/practice/5b1116081ee549f882970eca84b4785a?tpId=182&&tqId=34288&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*****
 */
public class Q14_Pigment {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            int[] x = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = scan.nextInt();
            }
            System.out.println(calc(x));
        }
    }

    public static String calc(int[] x) {
        for(int i = x.length-1; i > 0; i--) {
            Arrays.sort(x, 0, i + 1);
            for(int j = i-1; j >= 0; j--) {
                //x[i]^x[j] < x[j]意味着j位置上数的最高位被消除了，这时候把j位置上的数更新
                //x[i]^x[j]>= x[j]，意味着当前数最高位本来就是0，不用管它
                if((x[i] ^ x[j]) < x[j]) {
                    x[j] ^= x[i];
                }
            }
        }
        Common.printArray(x);
        int num = 0;
        for (int item : x) {
            if(item != 0)
                num++;
        }
        return "" + num;
    }
}
