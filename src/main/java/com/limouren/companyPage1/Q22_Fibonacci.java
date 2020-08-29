package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Fibonacci数列
 * Fibonacci数列是这样定义的：
 * F[0] = 0
 * F[1] = 1
 * for each i ≥ 2: F[i] = F[i-1] + F[i-2]
 * 因此，Fibonacci数列就形如：0, 1, 1, 2, 3, 5, 8, 13, ...，在Fibonacci数列中的数我们称为Fibonacci数。给你一个N，你想让其变为一个Fibonacci数，每一步你可以把当前数字X变为X-1或者X+1，现在给你一个数N求最少需要多少步可以变为Fibonacci数。
 * https://www.nowcoder.com/practice/18ecd0ecf5ef4fe9ba3f17f8d00d2d66?tpId=182&&tqId=34296&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q22_Fibonacci {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = br.readLine()) != null) {
            int strInt = Integer.parseInt(str);
            int[] start = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269};
            for (int i = 1; i < start.length; i++) {
                if(strInt >= start[i - 1] && strInt < start[i]) {
                    System.out.println(Math.min(strInt - start[i - 1], start[i] - strInt));
                    break;
                }
            }
        }
    }

    public static int[] fibonacci(int[] start) {
        int length = start.length;
        int[] ret = start;
        if(start[length - 1] <= 1000000) {
            ret = Arrays.copyOf(start, length + 1);
            ret[length] = start[length - 1] + start[length - 2];
            ret = fibonacci(ret);
        }
        return ret;
    }
}
