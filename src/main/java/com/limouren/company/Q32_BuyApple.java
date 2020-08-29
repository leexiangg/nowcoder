package com.limouren.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 买苹果
 * 小易去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个每袋的包装(包装不可拆分)。 可是小易现在只想购买恰好n个苹果，小易想购买尽量少的袋数方便携带。如果不能购买恰好n个苹果，小易将不会购买。
 * https://www.nowcoder.com/practice/61cfbb2e62104bc8aa3da5d44d38a6ef?tpId=182&&tqId=34306&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 * 贪心鸡兔同笼
 */
public class Q32_BuyApple {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(calc(n));
    }

    public static int calc(int n) {
        switch (n % 8) {
            case 0 :
                return n / 8;
            case 2 :
            case 4 :
            case 6 :
                return n / 8 + 1;
            default:
                return -1;
        }
    }
}
