package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 交错01串
 * 如果一个01串任意两个相邻位置的字符都是不一样的,我们就叫这个01串为交错01串。例如: "1","10101","0101010"都是交错01串。
 * 小易现在有一个01串s,小易想找出一个最长的连续子串,并且这个子串是一个交错01串。小易需要你帮帮忙求出最长的这样的子串的长度是多少。
 * https://www.nowcoder.com/practice/3fbd8fe929ea4eb3a254c0ed34ac993a?tpId=182&&tqId=34310&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q36_Cross0x {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        int l = 1, max = 0;
        for (int i = 1; i < s.length; i++) {
            if(s[i] != s[i - 1]) {
                max = Math.max(max, ++l);
            } else {
                max = Math.max(max, l);
                if(max > s.length - i)
                    break;
                l = 1;
            }
        }
        System.out.println(max);
    }
}
