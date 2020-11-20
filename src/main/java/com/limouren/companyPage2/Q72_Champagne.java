package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.nowcoder.com/practice/d4f843fc299a493ca9dbd76122a0a3d3?tpId=182&&tqId=34346&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 节日到啦，牛牛和妞妞邀请了好多客人来家里做客。
 * 他们摆出了一座高高的香槟塔，牛牛负责听妞妞指挥，往香槟塔里倒香槟。
 * 香槟塔有个很优雅的视觉效果就是如果这一层的香槟满了，就会从边缘处往下一层流去。
 * 妞妞会发出两种指令，指令一是往第x层塔内倒体积为v的香槟，指令二是询问第k层塔香槟的体积为多少。
 * 告诉你香槟塔每层香槟塔的初始容量，你能帮牛牛快速回答妞妞的询问吗？
 */
public class Q72_Champagne {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        int[] ai = new int[n];
        String[] as = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            ai[i] = Integer.parseInt(as[i]);
        }
        int[] tmp = Arrays.copyOf(ai, n);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String[] xvk = br.readLine().split(" ");
            if(xvk.length == 3) {
                int x = Integer.parseInt(xvk[1]);
                int v = Integer.parseInt(xvk[2]);
                if(tmp[x - 1] >= v)
                    tmp[x - 1] = tmp[x - 1] - v;
                else
                    for (int j = x; j < n; j++) {
                        if(tmp[j - 1] < v) {
                            v -= tmp[j - 1];
                            tmp[j - 1] = 0;
                        } else {
                            tmp[j - 1] = tmp[j - 1] - v;
                            break;
                        }
                    }
            } else {
                int k = Integer.parseInt(xvk[1]);
                sb.append(ai[k - 1] - tmp[k - 1]).append("\n");
            }
        }
        System.out.println(sb);
    }
}
