package com.limouren.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 数字游戏
 * 小易邀请你玩一个数字游戏，小易给你一系列的整数。你们俩使用这些整数玩游戏。每次小易会任意说一个数字出来，然后你需要从这一系列数字中选取一部分出来让它们的和等于小易所说的数字。 例如： 如果{2,1,2,7}是你有的一系列数，小易说的数字是11.你可以得到方案2+2+7 = 11.如果顽皮的小易想坑你，他说的数字是6，那么你没有办法拼凑出和为6 现在小易给你n个数，让你找出无法从n个数中选取部分求和的数字中的最小数（从1开始）。
 * https://www.nowcoder.com/practice/876e3c5fcfa5469f8376370d5de87c06?tpId=182&&tqId=34297&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 * 思路1：把所有的数的能加得的列表排序，找出第一个缺的
 * 思路2：有n个1，后面的数字之间就可以有n个空，把x排序后，找到超过n个空的地方，把前面的累加，就可以找到缺的值
 */
public class Q23_NumberGame {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String n = reader.readLine();
        int[] x = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(calc2(x));
    }

    public static int calc2(int[] x) {
        Arrays.sort(x);
        int sum = 0;
        for (int i = 0; i < x.length; i++) {
            if(x[i] > sum + 1) {
                break;
            }
            sum += x[i];
        }
        return sum + 1;
    }

    public static int calc1(int[] x) {
        int[] sum = new int[(int) Math.pow(2, x.length)];
        for (int i = 0; i < sum.length; i++) {
            int tmp = i;
            int s = 0;
            for (int value : x) {
                if (tmp % 2 == 1)
                    s += value;
                tmp /= 2;
            }
            sum[i] = s;
        }
        sum = Arrays.stream(sum).distinct().sorted().toArray();
        int ret = Arrays.stream(x).sum() + 1;
        for (int i = 1; i < sum.length; i++) {
            if(sum[i] > i) {
                ret = i;
                break;
            }
        }
        return ret;
    }
}
