package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.nowcoder.com/practice/3e483fe3c0bb447bb17ffb3eeeca78ba?tpId=182&&tqId=34339&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 今天上课，老师教了小易怎么计算加法和乘法，乘法的优先级大于加法，但是如果一个运算加了括号，那么它的优先级是最高的。例如：
 * 1+2*3=7
 * 1*(2+3)=5
 * 1*2*3=6
 * (1+2)*3=9
 * 现在小易希望你帮他计算给定3个数a，b，c，在它们中间添加"+"， "*"， "("， ")"符号，能够获得的最大值。
 */
public class Q65_Expression {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] abc = br.readLine().split(" ");
        int a = Integer.parseInt(abc[0]);
        int b = Integer.parseInt(abc[1]);
        int c = Integer.parseInt(abc[2]);
        int[] x = {a+b+c, (a+b)*c, (a+c)*b, (b+c)*a, a+b*c, a*b+c, a*c+b, a*b*c};
        int max = 0;
        for (int t : x)
            max = Math.max(max, t);
        System.out.println(max);
    }
}
