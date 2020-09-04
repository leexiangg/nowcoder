package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 会话列表
 * 小云正在参与开发一个即时聊天工具，他负责其中的会话列表部分。
 * 会话列表为显示为一个从上到下的多行控件，其中每一行表示一个会话，每一个会话都可以以一个唯一正整数id表示。
 * 当用户在一个会话中发送或接收信息时，如果该会话已经在会话列表中，则会从原来的位置移到列表的最上方；如果没有在会话列表中，则在会话列表最上方插入该会话。
 * 小云在现在要做的工作是测试，他会先把会话列表清空等待接收信息。当接收完大量来自不同会话的信息后，就输出当前的会话列表，以检查其中是否有bug。
 * https://www.nowcoder.com/practice/0f52adb3946249f9bb63d964658b2691?tpId=182&&tqId=34318&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q44_TalkList {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            String[] id_str = br.readLine().split(" ");
            Set<String> set = new HashSet<>();
            for (int j = id_str.length - 1; j >= 0; j--) {
                if(set.add(id_str[j]))
                    result.append(id_str[j]).append(" ");
            }
            result.deleteCharAt(result.length() - 1).append("\n");
        }
        System.out.println(result.toString().trim());
    }

    /**
     * 使用set
     * @param id_str
     * @return
     */
    public static String calc(String[] id_str) {
        StringBuilder result = new StringBuilder();
        Set<String> set = new HashSet<>();
        for (int i = id_str.length - 1; i >= 0; i--) {
            if(set.add(id_str[i]))
                result.append(id_str[i]).append(" ");
        }
        return result.toString().trim();
    }

    /**
     * 不使用set
     * @param id_str
     * @return
     */
    public static String calc2(String[] id_str) {
        StringBuilder result = new StringBuilder();
        for (int j = id_str.length - 1; j >= 0; j--) {
            boolean flag = true;
            for (int k = id_str.length - 1; k > j; k--) {
                if (id_str[k].equals(id_str[j])) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                result.append(id_str[j]).append(" ");
            }
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }
}
