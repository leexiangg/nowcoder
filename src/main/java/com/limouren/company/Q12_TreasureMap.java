package com.limouren.company;

import java.util.Scanner;

/**
 * 藏宝图
 * 牛牛拿到了一个藏宝图，顺着藏宝图的指示，牛牛发现了一个藏宝盒，藏宝盒上有一个机关，机关每次会显示两个字符串 s 和 t，根据古老的传说，牛牛需要每次都回答 t 是否是 s 的子序列。注意，子序列不要求在原字符串中是连续的，例如串 abc，它的子序列就有 {空串, a, b, c, ab, ac, bc, abc} 8 种。
 * https://www.nowcoder.com/practice/74475ee28edb497c8aa4f8c370f08c30?tpId=182&&tqId=34286&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q12_TreasureMap {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            char[] str1 = scan.nextLine().toCharArray();
            char[] str2 = scan.nextLine().toCharArray();
            int tmp = 0;
            for (char str : str1) {
                if(str2.length > tmp && str == str2[tmp]) {
                    tmp ++;
                }
            }
            if(tmp == str2.length)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}
