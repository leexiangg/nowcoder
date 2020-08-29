package com.limouren.companyPage1;

import com.limouren.common.Common;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO 答案都看不懂，废菜
 * 分田地
 * 牛牛和 15 个朋友来玩打土豪分田地的游戏，牛牛决定让你来分田地，地主的田地可以看成是一个矩形，每个位置有一个价值。分割田地的方法是横竖各切三刀，分成 16 份，作为领导干部，牛牛总是会选择其中总价值最小的一份田地， 作为牛牛最好的朋友，你希望牛牛取得的田地的价值和尽可能大，你知道这个值最大可以是多少吗？
 * https://www.nowcoder.com/practice/fe30a13b5fb84b339cb6cb3f70dca699?tpId=182&&tqId=34283&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*****
 */
public class Q9_DivideFields {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Common.printArray(rank3(6));
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            int[][] fields = new int[n][m];
            for (int i = 0; i < n; i++) {
                char[] tmp = scan.next().toCharArray();
                for (int j = 0; j < m; j++) {
                    fields[i][j] = tmp[j] - '0';
                }
            }
            long start = new Date().getTime();
            System.out.println(calc(fields));
            System.out.println(new Date().getTime() - start);
        }
    }

    /**
     * 看大神的代码，mark思路
     * 1、结果这个数一定是小于等于 sum/16
     * 2、看看有没有哪种切法能小于这个数
     *      TODO 看了半天，没看明白，真的是半天！！！！
     * 3、如果有则记录这个数，再继续找有没有更小的
     * @param fields
     * @return
     */
    public static String calc(int[][] fields) {
        int[][] sum=new int[fields.length+1][fields[0].length+1];
        for (int i=1;i<=fields.length ;i++ )
        {
            for (int j=1;j<=fields[0].length ;j++ )
            {
                sum[i][j]=sum[i-1][j]+
                        sum[i][j-1]-sum[i-1][j-1]+fields[i-1][j-1];
            }
        }
        int left=0, right = sum[fields.length][fields[0].length] / 16, res=0;
        while (left <= right)
        {
            int mid=(left+right)/2;
            if (judge(mid,fields.length,fields[0].length,sum))
            {
                left=mid+1;
                res=mid;
            }
            else
            {
                right=mid-1;
            }
        }
        return "" + res;
    }

    //计算matrix矩阵中左上顶点(i,j)到右下顶点(x-1,y-1)确定的田地的价值和
    public static int cal(int x,int y,int i,int j,int[][] sum)
    {
        return sum[x][y]-sum[x][j]-sum[i][y]+sum[i][j];
    }

    //判断x是否小于等于小于田地中最小一块的值
    public static boolean judge(int x,int n,int m,int[][] sum)
    {
        for (int i=1;i<=m-3 ;i++ )
        {
            for (int j=i+1;j<=m-2 ;j++ )
            {
                for (int k=j+1;k<=m-1 ;k++ )
                {
                    int last=0,cnt=0;
                    for (int r=1;r<=n ;r++ )
                    {
                        int s1=cal(r,i,last,0,sum);
                        int s2=cal(r,j,last,i,sum);
                        int s3=cal(r,k,last,j,sum);
                        int s4=cal(r,m,last,k,sum);
                        //当前横切一刀条件满足
                        if (s1>=x&&s2>=x&&s3>=x&&s4>=x)
                        {
                            last=r;
                            cnt++;
                        }
                    }
                    //表明x小于等于16块田地中的最小值，返回true
                    if (cnt>=4)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 暴力计算，太久太久了
     * 40 * 20 未使用线程池422秒，采用线程池后17秒
     * 75 * 75 线程池的方案经过一个小时没算出来
     * @param n
     * @param m
     * @param fields
     * @return
     */
    public static String calc(int n, int m, int[][] fields) throws ExecutionException, InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
        // 把 n 和 m 各分成 4 份
        int[][] dn = rank3(n);
        int[][] dm = rank3(m);
        AtomicInteger max = new AtomicInteger();
        for (int i = 0; i < dn.length; i++) {
            int finalI = i;
            fixedThreadPool.submit(() -> {
                for (int j = 0; j < dm.length; j++) {
                    int min = Integer.MAX_VALUE;
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 4; l++) {
                            int sum = 0;
                            for (int o = (k == 0 ? 0 : dn[finalI][k - 1]); o < (k == 3 ? n : dn[finalI][k]); o++) {
                                for (int p = (l == 0 ? 0 : dm[j][l - 1]); p < (l == 3 ? m : dm[j][l]); p++) {
                                    sum += fields[o][p];
                                }
                            }
                            min = Math.min(min, sum);
                        }
                    }
                    max.set(Math.max(max.get(), min));
                }
                return max;
            }).get();
        }
        return "" + max;
    }

    /**
     * 把n分成4份，相当于从n-1个空位置选取3个位置
     * @param n
     * @return 返回位置数组
     */
    public static int[][] rank3(int n) {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            count = count * (n - i - 1) / (i + 1);
        }
        int[][] dn = new int[count][3];
        int[] tmp = new int[3];
        int x = 0;
        for (int i = 0; i < n - 3; i++) {
            tmp[0] = i + 1;
            for (int j = 0; j < n - i - 3; j++) {
                tmp[1] = j + i + 2;
                for (int k = 0; k < n - i - j - 3; k++) {
                    tmp[2] = k + j + i + 3;
                    dn[x ++] = Arrays.copyOf(tmp, 3);
                }
            }
        }
        return dn;
    }

}
