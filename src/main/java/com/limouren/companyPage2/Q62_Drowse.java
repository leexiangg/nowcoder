package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * 瞌睡
 * 小易觉得高数课太无聊了，决定睡觉。不过他对课上的一些内容挺感兴趣，所以希望你在老师讲到有趣的部分的时候叫醒他一下。你知道了小易对一堂课每分钟知识点的感兴趣程度，并以分数量化，以及他在这堂课上每分钟是否会睡着，你可以叫醒他一次，这会使得他在接下来的k分钟内保持清醒。你需要选择一种方案最大化小易这堂课听到的知识点分值。
 * https://www.nowcoder.com/practice/93f2c11daeaf45959bb47e7894047085?tpId=182&&tqId=34336&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 */
public class Q62_Drowse {

    private static StreamTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StreamTokenizer(br);
        int n = nextInt();
        int k = nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();;
        }
        // 先把 t 中所有的 1 算到总的里面
        int sum = 0;
        int[] t = new int[n];
        int[] s = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            t[i] = nextInt();
            if(t[i] == 1) {
                sum += a[i];
                if(i > 0)
                    s[i] = s[i - 1];
                else s[i] = 0;
            } else {
                if(i > 0)
                    s[i] = s[i - 1] + a[i];
                else s[i] = a[i];
            }
            if(i > k - 1)
                max = Math.max(max, s[i] - s[i - k]);
            else if(i == k - 1)
                max = s[i];
        }
        System.out.println(Arrays.toString(s));
        System.out.println(max + sum);
    }

    private static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
