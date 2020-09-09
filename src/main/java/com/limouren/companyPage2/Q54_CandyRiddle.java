package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 糖果谜题
 * 小明是幼儿园的一名老师。某天幼儿园园长给小朋友们每人发一颗糖果，小朋友们拿到后发现有一些同学拿到的糖果颜色和自己相同，有一些同学糖果颜色和自己不同。
 * 假定每个小朋友只知道有多少同学和自己拿到了相同颜色的糖果。
 * 上课后，有一部分小朋友兴奋的把这一结果告诉小明老师，并让小明老师猜一猜，最少有多少同学拿到了糖果。
 * 例如有三个小朋友告诉小明老师这一结果如下：
 * 其中第一个小朋友发现有1人和自己糖果颜色一样，第二个小朋友也发现有1人和自己糖果颜色一样，第三个小朋友发现有3人和自己糖果颜色一样。
 * 第一二个小朋友可互相认为对方和自己颜色相同，比如红色；
 * 第三个小朋友不可能再为红色（否则第一二个小朋友会发现有2人和自己糖果颜色相同），假设他拿到的为蓝色糖果，那么至少还有另外3位同学拿到蓝色的糖果，最终至少有6位小朋友拿到了糖果。
 * 现在请你帮助小明老师解答下这个谜题。
 * https://www.nowcoder.com/practice/8ff3e3a14ea04c6bb3a60e2e457dafb1?tpId=182&&tqId=34328&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q54_CandyRiddle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] kidStr = br.readLine().split(" ");
        int[] kid = new int[kidStr.length];
        for (int i = 0; i < kidStr.length; i++) {
            kid[i] = Integer.parseInt(kidStr[i]);
        }
        Arrays.sort(kid);
        int count = 0;
        int flag = 0;
        int tmp = 0;
        for (int k : kid) {
            if(k == 0) {
                count++;
            } else {
                if(flag > 0 && k == tmp) {
                    flag--;
                } else {
                    flag = k;
                    tmp = k;
                    count += k + 1;
                }
            }
        }
        System.out.println(count);
    }
}
