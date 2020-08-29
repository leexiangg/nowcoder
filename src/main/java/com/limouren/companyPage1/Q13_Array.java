package com.limouren.companyPage1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 数组还原
 * 牛牛的作业薄上有一个长度为 n 的排列 A，这个排列包含了从1到n的n个数，但是因为一些原因，其中有一些位置（不超过 10 个）看不清了，但是牛牛记得这个数列顺序对的数量是 k，顺序对是指满足 i < j 且 A[i] < A[j] 的对数，请帮助牛牛计算出，符合这个要求的合法排列的数目。
 * https://www.nowcoder.com/practice/b698e67a2f5b450a824527e82ed7495d?tpId=182&&tqId=34287&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：****
 */
public class Q13_Array {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            int k = scan.nextInt();
            int[] A = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = scan.nextInt();
            }
            System.out.println(calc(A, k));
        }
    }

    public static String calc(int[] A, int k) {
        int ret = 0;
        // 记录缺失的数
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            list.add(i + 1);
        }
        for (int value : A) {
            if (value > 0) {
                list.remove(Integer.valueOf(value));
            }
        }
        // 看不清的这些数做全排列
        int[] array = list.stream().mapToInt(t -> t).toArray();
        do {
            // 取出一个排列r，补充到原队列中，计算一遍，如果符合，ret ++
            int index = 0;
            int[] tmp = Arrays.copyOf(A, A.length);
            for (int i = 0; i < tmp.length; i++) {
                if(tmp[i] == 0) {
                    tmp[i] = array[index++];
                }
            }
            if(compare(tmp) == k)
                ret ++;
        } while (nextPermutation(array));
        return "" + ret;
    }

    public static int compare(int[] A) {
        int sub = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i] < A[j] && A[A[i] - 1] < A[A[j] - 1]) {
                    sub++;
                }
            }
        }
        return sub;
    }

    public static boolean nextPermutation(int[] array) {
        //沒有下一個全排列
        if(array.length <= 1) {
            return false;
        }
        //从后往前找，找到第一个不满足降序的数（要考虑到重复的数字）
        int i = array.length - 2;
        for(; i >= 0 && array[i] > array[i+1]; i--) {}
        //没有全排列
        if(i == -1) {
            return false;
        }
        //从i开始往后找到大于arr[i]的最小的数
        int k = i+1;
        for(; k < array.length && array[k] > array[i]; k++) {}
        //交换arr[i]和arr[k-1]
        int t =array[i];
        array[i] = array[k-1];
        array[k-1] =t;
        //重新对arr[i]后面的数排序，接下来继续进行全排列操作
        Arrays.sort(array,i+1, array.length);
        return true;
    }
}
