package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.nowcoder.com/practice/83b419c027fa490aa60669b0e7dc06a3?tpId=182&&tqId=34337&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 又到了丰收的季节，恰逢小易去牛牛的果园里游玩。
 * 牛牛常说他对整个果园的每个地方都了如指掌，小易不太相信，所以他想考考牛牛。
 * 在果园里有N堆苹果，每堆苹果的数量为ai，小易希望知道从左往右数第x个苹果是属于哪一堆的。
 * 牛牛觉得这个问题太简单，所以希望你来替他回答。
 */
public class Q63_Harvest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] as = br.readLine().split(" ");
        int[] ai = new int[n + 1];
        ai[0] = 0;
        for (int i = 1; i <= n; i ++) {
            ai[i] = ai[i - 1] + Integer.parseInt(as[i - 1]);
        }
        int m = Integer.parseInt(br.readLine());
        String[] qs = br.readLine().split(" ");

        // 1、二分查找法，840ms
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i ++) {
            sb.append(dichotomy(ai, Integer.parseInt(qs[i]))).append("\n");
        }
        System.out.println(sb);

//        // 2、Map排序法，1064ms
//        int index = 0;
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < m; i ++) {
//            map.put(i, Integer.parseInt(qs[i]));
//        }
//        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
//        list.sort(Map.Entry.comparingByValue());
//        for (int i = 0; i < list.size(); i++) {
//            while(list.get(i).getValue() > ai[index]) {
//                index ++;
//            }
//            map.put(list.get(i).getKey(), index);
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < m; i ++) {
//            sb.append(map.get(i).intValue()).append("\n");
//        }
//        System.out.println(sb);

//        // 3、坐标法，空间换时间，843ms
//        int index = 0;
//        int[] xi = new int[ai[n] + 1];
//        for (int i = 0; i <= ai[n]; i ++) {
//            if(ai[index] < i)
//                index ++;
//            xi[i] = index;
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < m; i ++) {
//            sb.append(xi[Integer.parseInt(qs[i])]).append("\n");
//        }
//        System.out.println(sb);

//        // 4、看了别人代码才知道自己才疏学浅，原来Arrays里面有一个方法叫binarySearch
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < m; i ++) {
//            int idx = Arrays.binarySearch(ai, Integer.parseInt(qs[i]));
//            if(idx < 0) idx = -(idx+1);
//            sb.append(idx).append("\n");
//        }
//        System.out.println(sb);

    }

    /**
     * 用二分法查找
     * @param ai
     * @param q
     * @return
     */
    public static int dichotomy(int[] ai, int q) {
        int start = 0;
        int end = ai.length;
        while(true) {
            int index = (start + end) / 2;
            if(ai[index] > q) {
                if(ai[index - 1] < q) {
                    return index;
                } else {
                    end = index - 2;
                }
            } else if(ai[index] < q) {
                if(ai[index + 1] > q) {
                    return index + 1;
                } else {
                    start = index + 2;
                }
            } else {
                return index;
            }
        }
    }

}
