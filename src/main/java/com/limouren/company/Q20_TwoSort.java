package com.limouren.company;

import java.util.*;

/**
 * 两种排序方法
 * 考拉有n个字符串字符串，任意两个字符串长度都是不同的。考拉最近学习到有两种字符串的排序方法： 1.根据字符串的字典序排序。例如：
 * "car" < "carriage" < "cats" < "doggies < "koala"
 * 2.根据字符串的长度排序。例如：
 * "car" < "cats" < "koala" < "doggies" < "carriage"
 * 考拉想知道自己的这些字符串排列顺序是否满足这两种排序方法，考拉要忙着吃树叶，所以需要你来帮忙验证。
 * https://www.nowcoder.com/practice/839f681bf36c486fbcc5fcb977ffe432?tpId=182&&tqId=34294&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q20_TwoSort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            boolean dic = true;
            boolean len = true;
            String tmp = scan.next();
            for (int i = 1; i < n; i++) {
                String str = scan.next();
                dic &= str.compareTo(tmp) > 0;
                len &= str.length() > tmp.length();
                tmp = str;
            }
            if(dic && len)
                System.out.println("both");
            else if(dic)
                System.out.println("lexicographically");
            else if(len)
                System.out.println("lengths");
            else
                System.out.println("none");
        }
    }
}
