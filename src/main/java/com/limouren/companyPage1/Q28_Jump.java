package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 跳石板
 * 小易来到了一条石板路前，每块石板上从1挨着编号为：1、2、3.......
 * 这条石板路要根据特殊的规则才能前进：对于小易当前所在的编号为K的 石板，小易单次只能往前跳K的一个约数(不含1和K)步，即跳到K+X(X为K的一个非1和本身的约数)的位置。 小易当前处在编号为N的石板，他想跳到编号恰好为M的石板去，小易想知道最少需要跳跃几次可以到达。
 * 例如：
 * N = 4，M = 24：
 * 4->6->8->12->18->24
 * 于是小易最少需要跳跃5次，就可以从4号石板跳到24号石板
 * https://www.nowcoder.com/practice/4284c8f466814870bae7799a07d49ec8?tpId=182&&tqId=34302&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：****
 * 贪心算法：不一定能得到最优步数
 * 动态规划：找到所有能到终点得办法，想办法减少计算量
 */
public class Q28_Jump {
    /**
     * 记录所有能走的步子
     */
    private static List<Integer> list;
    /**
     * 记录已经跳过的点
     */
    private static boolean[] point;
    /**
     * 步数
     */
    private static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = nm[0];
        int M = nm[1];
        if(N == M) {
            System.out.println(0);
        } else {
            point = new boolean[M];
            System.out.println(step(N, M));
        }
    }

    /**
     * 计算能走到的地方
     * 列表记录法
     * @param N 起始位置
     * @param M 终点位置
     * @return 返回这一步能走到的所有地方的集合
     */
    public static int step(int N, int M) {
        list = new ArrayList<>();
        List<Integer> divisor = commonDivisor(N);
        for (int d : divisor) {
            list.add(N + d);
        }
        while(list.size() > 0) {
            List<Integer> tmp = new ArrayList<>();
            count ++;
            for (int x : list) {
                divisor = commonDivisor(x);
                for (int d : divisor) {
                    int sum = x + d;
                    if (sum == M) {
                        return count;
                    } else if (sum < M && !point[sum]) {
                        tmp.add(sum);
                        point[sum] = true;
                    }
                }
            }
            list = tmp;
        }
        return -1;
    }

    /**
     * 除了 1 和 M 的所有约数
     * @return
     */
    public static List<Integer> commonDivisor(int M) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(M); i++) {
            double tmp = (double) M / i;
            if (tmp % 1 == 0) {
                list.add((int) tmp);
                list.add(i);
            }
        }
        return list;
    }
}
