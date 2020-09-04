package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 牛牛的闹钟
 * 牛牛总是睡过头，所以他定了很多闹钟，只有在闹钟响的时候他才会醒过来并且决定起不起床。从他起床算起他需要X分钟到达教室，上课时间为当天的A时B分，请问他最晚可以什么时间起床
 * https://www.nowcoder.com/practice/9173e83d1774462f81255a26feafd7c6?tpId=182&&tqId=34325&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q51_NiuClick {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] Hi = new int[n];
        for (int i = 0; i < n; i++) {
            String[] Hi_str = br.readLine().trim().split(" ");
            Hi[i] = Integer.parseInt(Hi_str[0]) * 60 + Integer.parseInt(Hi_str[1]);
        }
        int X = Integer.parseInt(br.readLine().trim());
        String[] AB_str = br.readLine().trim().split(" ");
        int AB = Integer.parseInt(AB_str[0]) * 60 + Integer.parseInt(AB_str[1]);
        System.out.println(calc(n, Hi, X, AB));
    }

    /**
     * 二分法查找
     * @param n
     * @param Hi
     * @param X
     * @param AB
     * @return
     */
    public static String calc(int n, int[] Hi, int X, int AB) {
        int ret = 0;
        // 最晚起床时间
        int tmp = AB - X;
        Arrays.sort(Hi);
        int start_index = 0, end_index = n - 1;
        while(start_index <= end_index) {
            int index = (start_index + end_index) / 2;
            if(Hi[index] > tmp) {
                end_index = index - 1;
            } else if(Hi[index] < tmp) {
                ret = Hi[index];
                start_index = index + 1;
            } else {
                ret = Hi[index];
                break;
            }
        }
        return "" + (ret / 60) + " " + (ret % 60);
    }
}
