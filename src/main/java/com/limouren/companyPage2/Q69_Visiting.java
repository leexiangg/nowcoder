package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.nowcoder.com/practice/b8e21f5816874425836b7d32011f46b0?tpId=182&&tqId=34343&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 小易准备去拜访他的朋友，他的家在0点，但是他的朋友的家在x点(x > 0)，均在一条坐标轴上。小易每一次可以向前走1，2，3，4或者5步。问小易最少走多少次可以到达他的朋友的家。
 */
public class Q69_Visiting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        System.out.println(x % 5 > 0 ? x / 5 + 1 : x / 5);
    }
}
