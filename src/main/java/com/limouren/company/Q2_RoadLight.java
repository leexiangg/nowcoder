package com.limouren.company;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 路灯
 * 一条长l的笔直的街道上有n个路灯，若这条街的起点为0，终点为l，第i个路灯坐标为ai ，每盏灯可以覆盖到的最远距离为d，为了照明需求，所有灯的灯光必须覆盖整条街，但是为了省电，要使这个d最小，请找到这个最小的d。
 * https://www.nowcoder.com/practice/62cdf520b9d94616b6644ac03a0306ff?tpId=182&&tqId=34276&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：**
 */
public class Q2_RoadLight {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            // 路灯数量
            int count = scan.nextInt();
            // 路的长度
            long length = scan.nextLong();
            List<Long> list = new ArrayList<>();
            while (list.size() != count) {
                list.add(scan.nextLong());
            }
            System.out.println(light(length, list));
        }
    }

    /**
     * 计算每盏灯需要覆盖的最远距离
     * @param length 路的长度
     * @param list 路灯位置集合
     * @return 保留两位小数
     */
    public static String light(long length, List<Long> list) {
        list = list.stream().sorted().collect(Collectors.toList());
        // 路灯最远距离
        double lightLength = Math.max(list.get(0), (length - list.get(list.size() - 1)));
        for (int i = 0; i < list.size() - 1; i++) {
            lightLength = Math.max(lightLength, (double) (list.get(i + 1) - list.get(i)) / 2);
        }
        return String.format("%.2f", lightLength);
    }

}
