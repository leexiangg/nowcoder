package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.nowcoder.com/practice/b7985769dc434d85a16717908669bcab?tpId=182&&tqId=34342&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 你有3个需要完成的任务，完成这3个任务是需要付出代价的。
 * 首先，你可以不花任何代价的完成一个任务；然后，在完成了第i个任务之后，你可以花费|Ai - Aj|的代价完成第j个任务。|x|代表x的绝对值。
 * 计算出完成所有任务的最小代价。
 */
public class Q68_Cast {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] As = br.readLine().split(" ");
        int max = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            int tmp = Integer.parseInt(As[i]);
            max = Math.max(max, tmp);
            min = Math.min(min, tmp);
        }
        System.out.println(max - min);
    }

}
