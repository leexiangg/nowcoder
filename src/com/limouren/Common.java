package com.limouren;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Common {

    /**
     * 打印数组
     * @param array
     */
    public static void printArray(Object[][] array) {
        Arrays.stream(array).forEach(t -> {
            Arrays.stream(t).forEach(s -> System.out.print(s + "  "));
            System.out.println();
        });
    }

    /**
     * 打印数组
     * @param array
     */
    public static void printArray(int[][] array) {
        if(array != null)
            Arrays.stream(array).forEach(t -> {
                System.out.print("[");
                if(t != null)
                    Arrays.stream(t).forEach(s -> System.out.print(s + ","));
                System.out.println("]");
            });
    }

    /**
     * 打印数组
     * @param array
     */
    public static void printArray(long[][] array) {
        Arrays.stream(array).forEach(t -> {
            Arrays.stream(t).forEach(s -> System.out.print(s + "  "));
            System.out.println();
        });
    }

    /**
     * 打印数组
     * @param array
     */
    public static void printArray(int[] array) {
        Arrays.stream(array).forEach(t -> System.out.print(t + "  "));
        System.out.println();
    }

    /**
     * 数学中排列数量计算公式：A(n, r)
     * @param n
     * @param r
     * @return
     */
    public static int A(int n, int r) {
        int tmp = 1;
        for (int i = 0; i < r; i++) {
            tmp = tmp * (n - i);
        }
        return tmp;
    }

    /**
     * 数学中组合数量计算公式：C(n, r)
     * @param n
     * @param r
     * @return
     */
    public static int C(int n, int r) {
        return A(n, r) / F(r);
    }

    /**
     * 数学中阶乘计算公式 n!
     * @param n 不能大于 13
     * @return
     */
    public static int F(int n) {
        return A(n, n);
    }

    /**
     * 排列的枚举
     * 列举出把 n 分成 r 份的所有的有序情况
     * TODO
     * @param n
     * @param r
     * @return
     */
    public static int[][] rank(int n, int r) {
        int[][] ret = new int[A(n, r)][r];
        return ret;
    }

    /**
     * 组合的枚举
     * 列举出把 n 分成 r 份的所有的无序情况
     * TODO
     * @param n
     * @param r
     * @return
     */
    public static int[][] combination(int n, int r) {
        int[][] ret = new int[C(n, r)][r];
        return ret;
    }

    /**
     * 找出 x 能组合成的所有数组
     * n 个数的组合有 2^n-1 种
     * 按 2 进制某一位是否为 1 枚举
     * @param x
     * @return
     */
    public static int[][] combination(int[] x) {
        int[][] ret = new int[(int) Math.pow(2, x.length) - 1][];
        for (int i = ret.length; i > 0; i--) {
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0, t = i; t > 0; t /= 2, j++) {
                if(t % 2 == 1) {
                    tmp.add(x[j]);
                }
            }
            ret[ret.length - i] = tmp.stream().mapToInt(t -> t).toArray();
        }
        return ret;
    }

    /**
     * 把 n 分成两份的所有情况，无序
     * @param n
     * @return 返回第一个数的集合
     */
    public static int[] separate(int n) {
        int[] rtn = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            rtn[i] = i + 1;
        }
        return rtn;
    }

    /**
     * 递归法，对一个完全不重复的数组做全排列
     * 10个全排序3.8s
     * @param a
     * @return a.length! 个数组
     * 1,2,3,4,5
     */
    public static Object[][] rank(Object[] a) {
        Object[][] ret = new Object[F(a.length)][a.length];
        int index = 0;
        if(a.length == 1) {
            return new Object[][]{a};
        }
        Object first = a[0];
        Object[] tmp = Arrays.copyOfRange(a, 1, a.length);
        Object[][] rank = rank(tmp);
        // 把第一个数合并到返回的数组中
        for (int i = 0; i < rank.length; i++) {
            for (int j = 0; j <= rank[i].length; j++) {
                Object[] inner = new Object[rank[i].length + 1];
                System.arraycopy(rank[i], 0, inner, 0, j);
                inner[j] = first;
                System.arraycopy(rank[i], j, inner, j + 1, rank[i].length - j);
                ret[index++] = inner;
            }
        }
        return ret;
    }

    /**
     * 递归法，对一个完全不重复的数组做全排列
     * @param a
     * @return a.length! 个数组
     * 1,2,3,4,5
     */
    public static int[][] rank(int[] a) {
        int[][] ret = new int[F(a.length)][a.length];
        int index = 0;
        if(a.length == 1) {
            return new int[][]{a};
        }
        int first = a[0];
        int[] tmp = Arrays.copyOfRange(a, 1, a.length);
        int[][] rank = rank(tmp);
        // 把第一个数合并到返回的数组中
        for (int i = 0; i < rank.length; i++) {
            for (int j = 0; j <= rank[i].length; j++) {
                int[] inner = new int[rank[i].length + 1];
                System.arraycopy(rank[i], 0, inner, 0, j);
                inner[j] = first;
                System.arraycopy(rank[i], j, inner, j + 1, rank[i].length - j);
                ret[index++] = inner;
            }
        }
        return ret;
    }

    /**
     *         do {
     *             ret[index ++] = array;
     *         } while(nextPermutation(array));
     * @param array
     * @return
     */
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
        int t = array[i];
        array[i] = array[k - 1];
        array[k - 1] = t;
        //重新对arr[i]后面的数排序，接下来继续进行全排列操作
        Arrays.sort(array,i+1, array.length);
        return true;
    }

    /**
     * 数组倒序
     * @param x
     * @param fromIndex
     * @param toIndex
     * @return
     */
    public static int[] sortDesc(int[] x, int fromIndex, int toIndex) {
        int[] ret = new int[x.length];
        int[] tmp = new int[toIndex - fromIndex];
        System.arraycopy(x, fromIndex, tmp, 0, toIndex - fromIndex);
        System.arraycopy(x, 0, ret, 0, fromIndex);
        Arrays.sort(tmp);
        for (int i = 0; i < tmp.length; i++) {
            ret[toIndex - i - 1] = tmp[i];
        }
        System.arraycopy(x, toIndex, ret, toIndex, x.length - toIndex);
        return ret;
    }

    public static void main(String[] args) {
        printArray(combination(new int[]{1,2,3}));
    }

}
