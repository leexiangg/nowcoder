package com.limouren.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * 回文序列
 * 如果一个数字序列逆置之后跟原序列是一样的就称这样的数字序列为回文序列。例如：
 * {1, 2, 1}, {15, 78, 78, 15} , {112} 是回文序列,
 * {1, 2, 2}, {15, 78, 87, 51} ,{112, 2, 11} 不是回文序列。
 * 现在给出一个数字序列，允许使用一种转换操作：
 * 选择任意两个相邻的数，然后从序列移除这两个数，并用这两个数字的和插入到这两个数之前的位置(只插入一个和)。
 * 现在对于所给序列要求出最少需要多少次操作可以将其变成回文序列。
 * https://www.nowcoder.com/practice/0147cbd790724bc9ae0b779aaf7c5b50?tpId=182&&tqId=34300&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q26_PalindromeSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        LinkedList<Integer> queue = new LinkedList<>();
        Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(queue::add);
        int ret = 0;
        while(queue.size() > 1) {
            int start = queue.removeFirst();
            int end = queue.removeLast();
            if(start > end) {
                ret ++;
                queue.addFirst(start - end);
            } else if(start < end) {
                ret ++;
                queue.addLast(end - start);
            }
        }
        System.out.println(ret);
    }
}
