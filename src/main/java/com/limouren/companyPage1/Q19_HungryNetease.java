package com.limouren.companyPage1;

import java.util.Scanner;

/**
 * 饥饿的小易
 * 小易总是感觉饥饿，所以作为章鱼的小易经常出去寻找贝壳吃。最开始小易在一个初始位置x_0。对于小易所处的当前位置x，他只能通过神秘的力量移动到 4 * x + 3或者8 * x + 7。因为使用神秘力量要耗费太多体力，所以它只能使用神秘力量最多100,000次。贝壳总生长在能被1,000,000,007整除的位置(比如：位置0，位置1,000,000,007，位置2,000,000,014等)。小易需要你帮忙计算最少需要使用多少次神秘力量就能吃到贝壳。
 * https://www.nowcoder.com/practice/5ee8df898312465a95553d82ad8898c3?tpId=182&&tqId=34293&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：***
 */
public class Q19_HungryNetease {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int x = scan.nextInt();
            System.out.println(eat(x));
        }
    }

    /**
     * 找到规律：
     * 规律一：
     * 设：A(x) = 4x + 3, B(x) = 8x + 7, F(x) = 2x + 1
     * A(x) = F(F(x)), B(x) = F(F(F(x)))
     * 最多十万次B(x)，只需要最多 30万次 F(x)
     *
     * 规律二：
     * F(x) % n = F(x % n) % n
     *
     * @param x 289869954
     * @return 99999
     */
    public static int eat(int x) {
        int i = 0;
        while (x != 0 && i <= 300000) {
            x = ((x << 1) + 1) % 1000000007;
            i++;
        }
        return i != 1 ? (int) Math.ceil((i + 1.0) / 3) : -1;
    }
}
