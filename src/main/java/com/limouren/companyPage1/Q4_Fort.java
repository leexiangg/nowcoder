package com.limouren.companyPage1;

import java.util.Scanner;

/**
 * 炮台
 * 兰博教训提莫之后,然后和提莫讨论起约德尔人,谈起约德尔人,自然少不了一个人,那 就是黑默丁格------约德尔人历史上最伟大的科学家. 提莫说,黑默丁格最近在思考一个问题:黑默丁格有三个炮台,炮台能攻击到距离它R的敌人 (两点之间的距离为两点连续的距离,例如(3,0),(0,4)之间的距离是5),如果一个炮台能攻击 到敌人,那么就会对敌人造成1×的伤害.黑默丁格将三个炮台放在N*M方格中的点上,并且给出敌人 的坐标. 问:那么敌人受到伤害会是多大?
 * https://www.nowcoder.com/practice/f821a39207cd43518ccddb27fee0481d?tpId=182&&tqId=34278&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q4_Fort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            int r = scan.nextInt();
            int[][] fort = new int[][]{new int[]{scan.nextInt(), scan.nextInt()},
                    new int[]{scan.nextInt(), scan.nextInt()},
                    new int[]{scan.nextInt(), scan.nextInt()}};
            int[] enemy = new int[]{scan.nextInt(), scan.nextInt()};
            int count = 0;
            for (int i = 0; i < 3; i++) {
                if (r * r >= Math.pow(fort[i][0] - enemy[0], 2) + Math.pow(fort[i][1] - enemy[1], 2)) {
                    count++;
                }
            }
            System.out.println(count + "x");
        }
    }
}
