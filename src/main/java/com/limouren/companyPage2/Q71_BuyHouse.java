package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.nowcoder.com/practice/edf9346066f047a9833b3284798d6c29?tpId=182&&tqId=34345&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 在一条街上有n幢房子，标号从1到n，两个在标号上相差为1的房子视为相邻，这些房子中有k幢房子已有住户。
 * 现你准备搬入这条街，你能搬入一幢房子的条件是这幢房子没有人住在里面，与此同时由于你非常热爱与邻居进行交流，故而你需要你所入住的房子两边上都有住户。
 * 现要你求最小的可能符合要求的房子数，以及最大的可能符合要求的房子数。
 *
 * Note: 就样例来说，#代表已有住户，-代表空位，这种情况（###---)，没有满足条件的房子，为最小，故输出0
 * 最大的情况为(#-#-#-)，此种情况有二个位置满足条件，为最大，故输出2
 */
public class Q71_BuyHouse {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String[] nk = br.readLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            if(k <= 1 || n - k <= 0)
                sb.append("0 0\n");
            else if(k <= n - k)
                sb.append("0 ").append(Math.min(k, n - k) - 1).append("\n");
            else
                sb.append("0 ").append(Math.min(k, n - k)).append("\n");
        }
        System.out.println(sb);
    }
}
