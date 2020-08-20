package com.limouren;

import java.util.*;

/**
 * 幸运的袋子
 * 一个袋子里面有n个球，每个球上面都有一个号码(拥有相同号码的球是无区别的)。如果一个袋子是幸运的当且仅当所有球的号码的和大于所有球的号码的积。
 * 例如：如果袋子里面的球的号码是{1, 1, 2, 3}，这个袋子就是幸运的，因为1 + 1 + 2 + 3 > 1 * 1 * 2 * 3
 * 你可以适当从袋子里移除一些球(可以移除0个,但是别移除完)，要使移除后的袋子是幸运的。现在让你编程计算一下你可以获得的多少种不同的幸运的袋子。
 * https://www.nowcoder.com/practice/a5190a7c3ec045ce9273beebdfe029ee?tpId=182&&tqId=34289&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 有两种做法
 * 1、全部数字任意组合后，去重，检查是否幸运
 * 2、先把所有的数分别计数，每个数取 0 到 n 个，组合后，检查是否幸运
 * 前两种都会超时或者数组越界
 * 3、网友的答案，自己实在是想不明白了
 * 难度：****
 */
public class Q15_Bag {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            N = scan.nextInt();
            num = new int[N];
            nxt = new int[N];
            for(int i = 0; i < N; i++){
                num[i] = scan.nextInt();
            }
            System.out.println(calc());
        }
    }

    public static int[] num;
    public static int[] nxt;
    public static int N;

    public static int calc() {
        Arrays.sort(num);
        int p = N;
        for(int i = N-1; i >=0; i--){
            if(i < N-1 && num[i+1] > num[i]) p = i+1;
            nxt[i] = p;
        }
        return dfs(0,0,1);
    }

    public static int dfs(int st, int sum, int mul){
        if(st >= N) return (sum > mul)?1:0;
        if(num[st] > 1 && sum < mul) return 0;
        return dfs(st+1, sum + num[st], mul*num[st]) + dfs(nxt[st], sum, mul);
    }


    public static int calc1(int[] x) {
        int ret = 0;
        // 分别把所有的数计数
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : x) {
            map.put(value, map.containsKey(value) ? map.get(value) + 1 : 1);
        }
        // 对 list 组合
        int[][] list = getMap(map);
        for (int[] l : list) {
            if(luck(l)) {
                ret++;
            }
        }
        return ret;
    }

    // 从 map 种取出一个数，尝试这个数取 0 到 n 次，组合
    public static int[][] getMap(Map<Integer, Integer> map) {
        int key = map.keySet().iterator().next();
        if(map.size() == 1) {
            int number = map.get(key);
            int[][] ret = new int[number + 1][];
            for (int i = 0; i <= number; i++) {
                int[] tmp = new int[i];
                Arrays.fill(tmp, key);
                ret[i] = tmp;
            }
            return ret;
        } else {
            int number = map.values().stream().mapToInt(t -> t).reduce(1, (a, b) -> a * (b + 1));
            int[][] ret = new int[number][];
            int index = 0;
            int count = map.remove(key);
            int[][] list = getMap(map);
            for (int[] l : list) {
                for (int i = 0; i <= count; i++) {
                    if(l == null && i == 0)
                        continue;
                    int[] tmp = new int[l != null ? l.length + i : i];
                    if(i != 0)
                        Arrays.fill(tmp, key);
                    if(l != null)
                        System.arraycopy(l, 0, tmp, 0, l.length);
                    ret[index++] = tmp;
                }
            }
            return ret;
        }
    }

    public static boolean luck(int[] list) {
        if(list.length < 2)
            return false;
        long sum = 0, mult = 1;
        for (int i = 0; i < list.length; i++) {
            sum += list[i];
            mult *= list[i];
        }
        return sum > mult;
    }
}
