package com.limouren.common;

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
    public static void printArray(boolean[][] array) {
        if(array != null)
            Arrays.stream(array).forEach(t -> {
                System.out.print("[");
                if(t != null)
                    for (boolean s : t) {
                        System.out.print(s + ",");
                    }
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
        Arrays.stream(array).forEach(t -> System.out.print(t + " "));
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
        int tmp = 1;
        for (int i = 0; i < (Math.min((n - r), r)); i++) {
            tmp = tmp * ((n - i) / (i + 1));
        }
        return tmp;
//        return A(n, r) / F(r);
    }

    /**
     * 数学中阶乘计算公式 n!
     * @param n 不能大于 13
     * @return
     */
    public static int F(int n) {
        int tmp = 1;
        for (int i = 0; i < n; i++) {
            tmp = tmp * (n - i);
        }
        return tmp;
//        return A(n, n);
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
     * 从不重复的数组 a 中找 n 个数的组合
     * @param a
     * @param n
     * @return
     */
    public static int[][] combination(int[] a, int n) {
        int[][] ret = new int[C(a.length, n)][n];
        int index = 0;
        // 如果 n 只有 1 ，则把 a 队列铺平返回
        if(n == 1) {
            for (int i = 0; i < a.length; i++) {
                ret[i][0] = a[i];
            }
            return ret;
        }
        // 从剩下的里面取一个
        for (int i = 0; i < a.length; i++) {
            int[] combination = new int[a.length - i - 1];
            System.arraycopy(a, i + 1, combination, 0, a.length - i - 1);
            // 把这个后面的继续做组合
            int[][] tmp = combination(combination, n - 1);
            // 把取出来的这一个合并到所有组合的开头
            for (int j = 0; j < tmp.length; j++) {
                int[] inner = new int[tmp[j].length + 1];
                inner[0] = a[i];
                System.arraycopy(tmp[j], 0, inner, 1, tmp[j].length);
                ret[index ++] = inner;
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

    /**
     * 打印一个数的 3 进制
     * @param n 数
     * @param l 格式化长度，0为前端不补零，如果实际转换后超过l，以实际转换结果为准
     * @return 0t12012012
     */
    public static String ternary(long n, int l) {
        StringBuilder sb = new StringBuilder();
        do {
            sb.insert(0, n % 3);
        } while((n /= 3) > 0);
        while(sb.length() < l)
            sb.insert(0, '0');
        return sb.insert(0, "0t").toString();
    }

    /**
     * 除了 1 和 M 的所有约数
     * @return
     */
    public static List<Integer> commonDivisor(int M) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(M); i++) {
            double tmp = (double) M / i;
            if (tmp % 1 == 0) {
                list.add((int) tmp);
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 所有约数
     * @return
     */
    public static List<Integer> commonDivisorAll(int M) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(M); i++) {
            double tmp = (double) M / i;
            if (tmp % 1 == 0) {
                list.add((int) tmp);
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 辗转相除法：最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int divisor(int a, int b) {
        int tmp = a;
        while((tmp = a % b) > 0) {
            a = b;
            b = tmp;
        }
        return b;
    }

    /**
     * 穷举法：最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int divisor2(int a, int b) {
        for (int i = Math.min(a, b); i >= 2; i--) {
            if(a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return 1;
    }

    /**
     * 相减法：最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int divisor3(int a, int b) {
        while(a != b) {
            if(a > b) a -= b;
            else b -= a;
        }
        return a;
    }

}
