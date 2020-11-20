package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.nowcoder.com/practice/54868056c5664586b121d9098d008719?tpId=182&&tqId=34340&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 小易有一些立方体，每个立方体的边长为1，他用这些立方体搭了一些塔。
 * 现在小易定义：这些塔的不稳定值为它们之中最高的塔与最低的塔的高度差。
 * 小易想让这些塔尽量稳定，所以他进行了如下操作：每次从某座塔上取下一块立方体，并把它放到另一座塔上。
 * 注意，小易不会把立方体放到它原本的那座塔上，因为他认为这样毫无意义。
 * 现在小易想要知道，他进行了不超过k次操作之后，不稳定值最小是多少。
 */
public class Q66_Tower {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        String[] as = br.readLine().split(" ");
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0, s = 0;
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(as[i]);
            map.put(i, a);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        while(k-- > 0) {
            sb.append(list.get(n - 1).getKey() + 1).append(" ").append(list.get(0).getKey() + 1).append("\n");
            count ++;
            list.get(0).setValue(list.get(0).getValue() + 1);
            list.get(n - 1).setValue(list.get(n - 1).getValue() - 1);
            list.sort(Map.Entry.comparingByValue());
            if(list.get(n - 1).getValue() - list.get(0).getValue() <= 1) {
                break;
            }
        }
        s = list.get(n - 1).getValue() - list.get(0).getValue();
        System.out.println(s + " " + count + "\n" + sb.toString());
    }
}
