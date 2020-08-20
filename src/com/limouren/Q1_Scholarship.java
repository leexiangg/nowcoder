package com.limouren;
import java.util.*;
import java.util.stream.*;

/**
 * 奖学金
 * 小v今年有n门课，每门都有考试，为了拿到奖学金，小v必须让自己的平均成绩至少为avg。每门课由平时成绩和考试成绩组成，满分为r。现在他知道每门课的平时成绩为ai ,若想让这门课的考试成绩多拿一分的话，小v要花bi 的时间复习，不复习的话当然就是0分。同时我们显然可以发现复习得再多也不会拿到超过满分的分数。为了拿到奖学金，小v至少要花多少时间复习。
 * https://www.nowcoder.com/practice/cee98a512ec246a2918ea8121f7612c8?tpId=182&&tqId=34275&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：**
 */
public class Q1_Scholarship {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            // 科目数量
            int count = scan.nextInt();
            // 满分
            int full = scan.nextInt();
            // 平均分
            int avg = scan.nextInt();
            List<AB> list = new ArrayList<>();
            while(list.size() != count) {
                list.add(new AB(scan.nextInt(), scan.nextInt()));
            }
            System.out.println(time(count, full, avg, list));
        }
    }

    public static long time(int count, int full, int avg, List<AB> list) {
        // 按b排序
        List<AB> list2 = list.stream().sorted(Comparator.comparing(AB::getB)).collect(Collectors.toList());
        // 总缺的分数
        int less = (avg * count) - list2.stream().map(AB::getA).reduce(0, Integer::sum);
        // 如果不缺，直接返回0
        if(less <= 0) return 0;
        // 总复习时间
        long time = 0;
        for (AB ab : list2) {
            // 本门课离满分的数
            int lessScore = full - ab.getA();
            // 如果分数不够，继续复习
            if (lessScore < less) {
                time += lessScore * ab.getB();
                less -= lessScore;
            }
            // 如果学完这门课就够了，学够时间停止复习
            else {
                time += less * ab.getB();
                break;
            }
        }
        return time;
    }

    static class AB {
        private Integer a;
        private Integer b;
        public AB(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public Integer getA() {
            return a;
        }

        public Integer getB() {
            return b;
        }
    }
}