package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 计算糖果
 * A,B,C三个人是好朋友,每个人手里都有一些糖果,我们不知道他们每个人手上具体有多少个糖果,但是我们知道以下的信息：
 * A - B, B - C, A + B, B + C. 这四个数值.每个字母代表每个人所拥有的糖果数.
 * 现在需要通过这四个数值计算出每个人手里有多少个糖果,即A,B,C。这里保证最多只有一组整数A,B,C满足所有题设条件。
 * https://www.nowcoder.com/practice/02d8d42b197646a5bbd0a98785bb3a34?tpId=182&&tqId=34307&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q33_Candy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int[] x = {Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3])};
        System.out.println(calc(x));
    }

    /**
     * 设：A - B = x0, B - C = x1, A + B = x2, B + C = x3
     * A = (x0 + x2) / 2
     * B = (x2 - x0) / 2 = (x1 + x3) / 2
     * C = (x3 - x1) / 2
     * @return
     */
    public static String calc(int[] x) {
        int A = x[0] + x[2];
        int B = x[2] - x[0];
        int B1 = x[1] + x[3];
        int C = x[3] - x[1];
        if (B == B1 && A % 2 == 0 && B % 2 == 0 && C % 2 == 0) {
            return "" + A / 2 + " " + B / 2 + " " + C / 2;
        }
        return "No";
    }
}
