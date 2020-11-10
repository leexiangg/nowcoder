package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 俄罗斯方块
 * 小易有一个古老的游戏机，上面有着经典的游戏俄罗斯方块。因为它比较古老，所以规则和一般的俄罗斯方块不同。
 * 荧幕上一共有 n 列，每次都会有一个 1 x 1 的方块随机落下，在同一列中，后落下的方块会叠在先前的方块之上，当一整行方块都被占满时，这一行会被消去，并得到1分。
 * 有一天，小易又开了一局游戏，当玩到第 m 个方块落下时他觉得太无聊就关掉了，小易希望你告诉他这局游戏他获得的分数。
 * https://www.nowcoder.com/practice/9407e24a70b04fedba4ab3bd3ae29704?tpId=182&&tqId=34335&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 */
public class Q61_Tetris {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int[] count = new int[Integer.parseInt(nm[0])];
        String[] c_str = br.readLine().split(" ");
        for (String c : c_str)
            count[Integer.parseInt(c) - 1] ++;
        int min = Integer.MAX_VALUE;
        for (int c : count)
            min = Math.min(c, min);
        System.out.println(min);
    }
}
