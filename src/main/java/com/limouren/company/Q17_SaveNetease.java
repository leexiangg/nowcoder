package com.limouren.company;

import java.util.Scanner;

/**
 * 解救小易
 * 有一片1000*1000的草地，小易初始站在(1,1)(最左上角的位置)。小易在每一秒会横向或者纵向移动到相邻的草地上吃草(小易不会走出边界)。大反派超超想去捕捉可爱的小易，他手里有n个陷阱。第i个陷阱被安置在横坐标为xi ，纵坐标为yi 的位置上，小易一旦走入一个陷阱，将会被超超捕捉。你为了去解救小易，需要知道小易最少多少秒可能会走入一个陷阱，从而提前解救小易。
 * https://www.nowcoder.com/practice/cd763d8541fc4243b8d3b967bb6d6b6a?tpId=182&&tqId=34291&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 */
public class Q17_SaveNetease {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            int[] x = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = scan.nextInt();
            }
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int y = scan.nextInt();
                min = Math.min(min, x[i] + y - 2);
            }
            System.out.println(min);
        }
    }
}
