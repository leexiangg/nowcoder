package com.limouren.companyPage2;

import com.limouren.common.Common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 骰子游戏
 * 小易参加了一个骰子游戏,这个游戏需要同时投掷n个骰子,每个骰子都是一个印有数字1~6的均匀正方体。
 * 小易同时投掷出这n个骰子,如果这n个骰子向上面的数字之和大于等于x,小易就会获得游戏奖励。
 * 小易想让你帮他算算他获得奖励的概率有多大。
 * https://www.nowcoder.com/practice/0e83797c34e54cca91179fe9ad681bc4?tpId=182&&tqId=34334&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：****
 */
public class Q60_Dice {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nx = br.readLine().split(" ");
        int n = Integer.parseInt(nx[0]);
        int x = Integer.parseInt(nx[1]);
        System.out.println(calc(n, x));
    }

    /**
     * TODO 这种算组合总数的方法很常见，要牢牢记住
     * @param n 个数
     * @param x
     * @return
     */
    public static String calc(int n, int x) {
        long[][] dp = new long[n + 1][x + 1];
        if(n * 6 < x) {
            return "0";
        } else if(n >= x) {
            return "1";
        } else {
            for(int i = 1; i <= 6; i ++)
                dp[1][i] = 1;            //只投一个骰子，分数为i的方案数为1
            for(int i = 1; i <= n; i ++){        //从投一个骰子到n个骰子
                for(int j = 1; j <= x; j ++){
                    for(int k = 1; k <= 6; k ++){    //骰子只有1-6
                        if(j - k < 0)        //如果j-k<0就没有必要继续比较了  对后面的k,j-k肯定都是<0的了
                            break;
                        dp[i][j] += dp[i - 1][j - k];    //投了i个骰子，总得分为j的方案数=投了i-1个骰子，得分为j-1,j-2,...,j-k（当然前提要j-k>=0,因为不可能投了i-1个骰子得了负分）
                    }
                }
            }
        }
        Common.printArray(dp);
        long max = (long) Math.pow(6, n);
        long sum = max;
        for(int i = 1; i < x; i ++)
            sum -= dp[n][i];
        long divisor = divisor(sum, max);
        return (sum / divisor) + "/" + (max / divisor);
    }

    /**
     * 辗转相除法：最大公约数
     * @param a
     * @param b
     * @return
     */
    public static long divisor(long a, long b) {
        long tmp;
        while((tmp = a % b) > 0) {
            a = b;
            b = tmp;
        }
        return b;
    }
}
