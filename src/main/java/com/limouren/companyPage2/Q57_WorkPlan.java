package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 工作方案
 * 牛牛手中有s份工作需要完成,牛牛准备将工作分给三位员工。考虑到三位员工还有其他工作需要做,牛牛规定他们每人必须要参与的工作数量分别是a,b,c。
 * 牛牛需要制定详细的工作方案,需要满足每份工作至少有一个人做,同一份工作可以由两个或者三个人共同参与。牛牛一下意识到可能的工作方案很多,牛牛需要你帮他计算一下一共有多少种不同的工作方案(对于两种方案,如果某份工作分配的人或者人数不一样就考虑为不一样的工作方案)。
 * 对于输入样例,s = 3, a = 3, b = 1, c = 1
 * a要参与所有三份工作,b和c各自有三种选择,所以不同的工作方案是3 * 3 * 1= 9
 * 如果s = 3, a = 1, b = 1, c = 1
 * 相当于对三个员工做全排列,所以不同的工作方案是3 * 2 * 1 = 6
 * https://www.nowcoder.com/practice/50ae2573431c45db918797836a40406e?tpId=182&&tqId=34331&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*****
 * 没做出来，没有思路，抄的
 */
public class Q57_WorkPlan {
    public static final long MOD = 1000000007L;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int h1 = Integer.parseInt(str[1]);
        int h2 = Integer.parseInt(str[2]);
        int h3 = Integer.parseInt(str[3]);
        int max = Math.max(h1,Math.max(h2,h3));
        long res = 0;
        boolean t = true;
        for(int i=n; i>=max; --i){
            res = t ? (res+C(i,n)*C(h1,i)%MOD*C(h2,i)%MOD*C(h3,i)%MOD)%MOD :
                    (res+MOD-C(i,n)*C(h1,i)%MOD*C(h2,i)%MOD*C(h3,i)%MOD)%MOD;
            t = t ? false : true;
        }
        System.out.println(res);
    }

    public static long C(double m,double n){
        double tem = 1;
        while (m>0){
            tem *= (n--/m--);
        }
        return Math.round(tem)%MOD;
    }
}
