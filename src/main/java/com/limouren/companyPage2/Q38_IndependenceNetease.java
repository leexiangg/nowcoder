package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 独立的小易
 * 小易为了向他的父母表现他已经长大独立了,他决定搬出去自己居住一段时间。一个人生活增加了许多花费: 小易每天必须吃一个水果并且需要每天支付x元的房屋租金。当前小易手中已经有f个水果和d元钱,小易也能去商店购买一些水果,商店每个水果售卖p元。小易为了表现他独立生活的能力,希望能独立生活的时间越长越好,小易希望你来帮他计算一下他最多能独立生活多少天。
 * https://www.nowcoder.com/practice/a99cdf4e2f44499e80749699cc2ec2b9?tpId=182&&tqId=34312&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 贪心算法
 * 难度：*
 */
public class Q38_IndependenceNetease {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        // 房租
        long x = Integer.parseInt(s[0]);
        // 水果
        long f = Integer.parseInt(s[1]);
        // 钱
        long d = Integer.parseInt(s[2]);
        // 水果价格
        long p = Integer.parseInt(s[3]);
        System.out.println(Math.min((d + f * p) / (x + p), d / x));
    }
}
