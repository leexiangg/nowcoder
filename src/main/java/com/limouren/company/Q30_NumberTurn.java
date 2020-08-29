package com.limouren.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 数字翻转
 * 对于一个整数X，定义操作rev(X)为将X按数位翻转过来，并且去除掉前导0。例如:
 * 如果 X = 123，则rev(X) = 321;
 * 如果 X = 100，则rev(X) = 1.
 * 现在给出整数x和y,要求rev(rev(x) + rev(y))为多少？
 * https://www.nowcoder.com/practice/bc62febdd1034a73a62224affe8bddf2?tpId=182&&tqId=34304&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q30_NumberTurn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] xy = br.readLine().split(" ");
        int x = Integer.parseInt(xy[0]);
        int y = Integer.parseInt(xy[1]);
        System.out.println(rev(rev(x) + rev(y)));
    }

    public static int rev(int n) {
        int ret = 0;
        do {
            ret += n % 10;
            n /= 10;
        } while(n > 0 && (ret *= 10) >= 0);
        return ret;
    }
}
