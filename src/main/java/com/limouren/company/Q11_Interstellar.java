package com.limouren.company;

import java.util.Scanner;

/**
 * 星际穿越
 * 航天飞行器是一项复杂而又精密的仪器，飞行器的损耗主要集中在发射和降落的过程，科学家根据实验数据估计，如果在发射过程中，产生了 x 程度的损耗，那么在降落的过程中就会产生 x2 程度的损耗，如果飞船的总损耗超过了它的耐久度，飞行器就会爆炸坠毁。问一艘耐久度为 h 的飞行器，假设在飞行过程中不产生损耗，那么为了保证其可以安全的到达目的地，只考虑整数解，至多发射过程中可以承受多少程度的损耗？
 * https://www.nowcoder.com/practice/53e4c208b8cf497086ecd65ef45349bb?tpId=182&&tqId=34285&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q11_Interstellar {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            long h = scan.nextLong();
            System.out.println(calc(h));
        }
    }

    public static String calc(long h) {
        long sq = (long) Math.sqrt(h);
        while(sq * sq + sq > h) {
            sq --;
        }
        return "" + sq;
    }
}
