package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 牛牛找工作
 * 为了找到自己满意的工作，牛牛收集了每种工作的难度和报酬。牛牛选工作的标准是在难度不超过自身能力值的情况下，牛牛选择报酬最高的工作。在牛牛选定了自己的工作后，牛牛的小伙伴们来找牛牛帮忙选工作，牛牛依然使用自己的标准来帮助小伙伴们。牛牛的小伙伴太多了，于是他只好把这个任务交给了你。
 * https://www.nowcoder.com/practice/46e837a4ea9144f5ad2021658cb54c4d?tpId=182&&tqId=34319&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：****
 */
public class Q45_FindWork {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        // 工作
        int N = Integer.parseInt(NM[0]);
        // 小伙伴
        int M = Integer.parseInt(NM[1]);
        // 工作难度和报酬
        Work[] work = new Work[N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            if(line.trim().length() > 0) {
                String[] work_str = line.split(" ");
                work[i] = new Work(Integer.parseInt(work_str[0]), Integer.parseInt(work_str[1]));
            } else {
                i --;
            }
        }
        // 小伙伴的能力
        Buddy[] buddies = new Buddy[M];
        String line = null;
        while(line == null || line.trim().length() == 0)
            line = br.readLine();
        String[] buddies_str = line.split(" ");
        for (int i = 0; i < M; i++) {
            buddies[i] = new Buddy(i, Integer.parseInt(buddies_str[i]));
        }
        System.out.println(calc(N, M, work, buddies));
    }

    /**
     * 双排序法
     * 1、把所有工作按能力值排序
     * 2、给所有的小伙伴按顺序标上号，然后把小伙伴按能力排序
     * 3、给小伙伴匹配工作，发现某个工作已不满足后面更高能力的小伙伴时，删掉这个工作
     * @param N 工作数
     * @param M 小伙伴数
     * @param work 工作难度和报酬
     * @param buddies 小伙伴
     * @return
     */
    public static String calc(int N, int M, Work[] work, Buddy[] buddies) {
        // 记录已经没用的工作脚标
        int index = 0;
        // 排序
        Arrays.sort(work);
        // 把垃圾工作过滤一遍
        List<Work> workList = new ArrayList<>();
        workList.add(new Work(work[0].ability, 0));
        Work wk = work[0];
        for (Work w : work) {
            if(w.salary > wk.salary)
                workList.add(new Work(w.ability, wk.salary));
            w.salary = Math.max(w.salary, wk.salary);
            wk = w;
        }
        workList.add(new Work(Integer.MAX_VALUE, wk.salary));

        // 开始计算
        Buddy[] tmp = Arrays.copyOf(buddies, buddies.length);
        Arrays.sort(tmp);
        for (Buddy buddy : tmp) {
            while(index < workList.size() - 1 && buddy.ability >= workList.get(index).ability) {
                index ++;
            }
            buddies[buddy.index].salary = workList.get(index).salary;
        }
        StringBuilder sb = new StringBuilder();
        for (Buddy buddy : buddies) {
            sb.append(buddy.salary).append("\n");
        }
        return sb.toString().trim();
    }

    static class Buddy implements Comparable<Buddy> {
        public int index;
        public int ability;
        public int salary;

        public Buddy(int index, int ability) {
            this.index = index;
            this.ability = ability;
        }

        @Override
        public String toString() {
            return "Buddy{" +
                    "index=" + index +
                    ", ability=" + ability +
                    ", salary=" + salary +
                    '}';
        }

        @Override
        public int compareTo(Buddy o) {
            return Integer.compare(this.ability, o.ability);
        }
    }

    /**
     * 遍历法查找
     * @param N 工作数
     * @param M 小伙伴数
     * @param work 工作难度和报酬
     * @param ability 小伙伴的能力
     * @return
     */
    public static String calc1(int N, int M, Work[] work, int[] ability) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int max = 0;
            for (int j = 0; j < N; j++) {
                if(ability[i] >= work[j].ability)
                    max = Math.max(max, work[j].salary);
            }
            sb.append(max).append("\n");
        }
        return sb.toString();
    }

    /**
     * 先过滤工作，在查找
     * 1、先把工作按能力值排序，高能力的工作如果还没有低能力的工作工资高
     * 2、直接把高能力的的工资计为低能力的工资，顺便把低能力的工作删掉
     * 3、二分查找工作
     * @param N 工作数
     * @param M 小伙伴数
     * @param work 工作难度和报酬
     * @param ability 小伙伴的能力
     * @return
     */
    public static String calc2(int N, int M, Work[] work, int[] ability) {
        StringBuilder sb = new StringBuilder();
        // 1、先把工作按能力值排序，高能力的工作如果还没有低能力的工作工资高
        Arrays.sort(work);
        List<Work> workList = new ArrayList<>();
        workList.add(new Work(work[0].ability, 0));
        Work tmp = work[0];
        for (Work w : work) {
            if(w.salary > tmp.salary)
                workList.add(new Work(w.ability, tmp.salary));
            w.salary = Math.max(w.salary, tmp.salary);
            tmp = w;
        }
        workList.add(new Work(Integer.MAX_VALUE, tmp.salary));

        // 2、每个小伙伴
        for (int a : ability) {
            int max = 0;
            // 按二分法查找，找自己刚好可以胜任的工作
            int start_index = 0, end_index = workList.size() - 1;
            while(end_index >= start_index) {
                if(end_index == start_index) {
                    if(a < workList.get(end_index).ability)
                        max = workList.get(end_index).salary;
                    break;
                }
                int index = (end_index + start_index) / 2;
                Work w = workList.get(index);
                if(a < w.ability) {
                    max = w.salary;
                    end_index = index - 1;
                } else {
                    start_index = index + 1;
                }
            }
            sb.append(max).append("\n");
        }
        return sb.toString();
    }

    static class Work implements Comparable<Work> {
        public int ability;
        public int salary;

        public Work(int ability, int salary) {
            this.ability = ability;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Work{" +
                    "ability=" + ability +
                    ", salary=" + salary +
                    '}';
        }

        @Override
        public int compareTo(Work o) {
            return Integer.compare(this.ability, o.ability);
        }
    }
}
