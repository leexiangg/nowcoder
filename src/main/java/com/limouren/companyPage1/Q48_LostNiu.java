package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 迷路的牛牛
 * 牛牛去犇犇老师家补课，出门的时候面向北方，但是现在他迷路了。虽然他手里有一张地图，但是他需要知道自己面向哪个方向，请你帮帮他。
 * https://www.nowcoder.com/practice/fc72d3493d7e4be883e931d507352a4a?tpId=182&&tqId=34322&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q48_LostNiu {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        char[] s = br.readLine().toCharArray();
        int step = 0;
        for (char c : s) {
            step += c == 'L' ? 3 : 1;
        }
        int ret = step % 4;
        if(ret == 0)
            System.out.println("N");
        else if(ret == 1)
            System.out.println("E");
        else if(ret == 2)
            System.out.println("S");
        else
            System.out.println("W");
    }
}
