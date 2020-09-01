package com.limouren.companyPage2;

import com.limouren.common.Common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 小易喜欢的队列
 * 小易非常喜欢拥有以下性质的数列:
 * 1、数列的长度为n
 * 2、数列中的每个数都在1到k之间(包括1和k)
 * 3、对于位置相邻的两个数A和B(A在B前),都满足(A <= B)或(A mod B != 0)(满足其一即可)
 * 例如,当n = 4, k = 7
 * 那么{1,7,7,2},它的长度是4,所有数字也在1到7范围内,并且满足第三条性质,所以小易是喜欢这个数列的
 * 但是小易不喜欢{4,4,4,2}这个数列。小易给出n和k,希望你能帮他求出有多少个是他会喜欢的数列。
 * https://www.nowcoder.com/practice/49375dd6a42d4230b0dc4ea5a2597a9b?tpId=182&&tqId=34315&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*****
 */
public class Q41_NeteaseLikeList {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        System.out.println(calc(n, k));
    }

    /**
     * 网友的代码  TODO 多琢磨琢磨
     * state[i][j]表示整个状态空间，其中i(1<=i<=n)表示数列的长度，j(1<=j<=k)表示数列长度为i且以数字j结尾。
     * 递推关系有：state[i][j] += state[i-1][m] (1<=m<=k, 并且(m,j)是个合法的数列)，但是直接按照递推关系，
     * 用三层for循环会超时。为此可以先将长度为i-1的合法数列求和(记为sum)。然后对于数列长度为i的每一个j，
     * 求出数列长度为i-1时非法的序列个数（记为invalid）,即有state[i][j] = sum - invalid。
     * 对于invalid求取，可以参照素数筛选。算法的时间复杂度大概为O(nkloglogk)
     * @param n 长度
     * @param k 最大能取得数字
     * @return
     */
    public static long calc(int n, int k) {
        int[][] state = new int[n+1][k+1];

        state[0][1] = 1;

        for(int i=1; i<=n; i++) {
            int sum = 0;
            for(int j=1; j<=k; j++) {
                sum = (sum + state[i-1][j]) % 1000000007;
            }
            for(int j=1; j<=k; j++) {
                int invalid = 0;
                int p = 2;
                while(p*j <= k) {
                    invalid = (invalid + state[i-1][p*j]) % 1000000007;
                    p++;
                }
                state[i][j] = (sum - invalid + 1000000007) % 1000000007;
            }
        }

        int sum = 0;
        for(int i=1; i<=k; i++) {
            sum = (sum + state[n][i]) % 1000000007;
        }
        return sum;
    }

    /**
     * 从前往后递归法
     * 1、循环取当前位置的一个数
     * 2、当前位置符合条件的记录数 * 后面位置符合规律的数量
     * 从后往前计算计算量太大
     * @param n 长度
     * @param k 最大能取得数字
     * @param per 前一位数A，最开始调用时传入0
     * @return
     */
    public static long calc1(int n, int k, int per) {
        long sum = 0;
        for (int i = 1; i <= k; i++) {
            if(per == 0 || (per <= i || per % i != 0)) {
                if(n == 1)
                    sum ++;
                else
                    sum += calc1(n - 1, k, i);
            }
        }
        return sum % 1000000007;
    }

    /**
     * 先使用全排列法找规律
     * n\ k|   2     3     4     5     6     7
     * ---------------------------------------------
     * 1   |   2     3     4     5     6     7
     * 2   |   3     7     12    20    28    40
     * 3   |   4     15    33    76    123   220
     * 4   |   5     31    88    285   532   1201
     * 5   |   6     63    232   1065  2292  6547
     * 6   |   7     127   609   3976  9865  35680
     * 7   |   8     255   1596  14840 42450 194440
     * 服了，找不到规律，只能在优化递归方式了
     * @param n
     * @param k
     * @return
     */
    public static String test(int n, int k) {
        int count = 0;
        int[][] rank = rank(n, k);
        for (int i = 0; i < rank.length; i++) {
            boolean flag = true;
            for (int j = 0; j < rank[i].length - 1; j++) {
                flag &= (rank[i][j] <= rank[i][j + 1] || rank[i][j] % rank[i][j + 1] != 0);
            }
            if(flag)
                count ++;
        }
        return "" + count;
    }

    /**
     * 递归法列出所有符合情况的排列
     * @param n
     * @param k
     * @return
     */
    public static int[][] rank(int n, int k) {
        int[][] ret = new int[(int) Math.pow(k, n)][n];
        if(n == 1) {
            for (int i = 0; i < k; i++) {
                ret[i][0] = i + 1;
            }
            return ret;
        }
        int index = 0;
        for (int i = 0; i < k; i++) {
            int[][] rank = rank(n - 1, k);
            for (int j = 0; j < rank.length; j++) {
                ret[index][0] = i + 1;
                System.arraycopy(rank[j], 0, ret[index], 1, rank[j].length);
                index ++;
            }
        }
        return ret;
    }
}
