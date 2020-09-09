package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 缩写
 * 在英文中,我们会把一些长的名字或者短语进行缩写。例如"looks good to me"缩写为"lgtm",短语中的每个单词的首字母组成缩写。现在给出一个字符串s,字符串s中包括一个或者多个单词,单词之间以空格分割,请输出这个字符串的缩写。
 * https://www.nowcoder.com/practice/45083499b8c5404fb1db44c6ea4f170a?tpId=182&&tqId=34330&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 * StringBuilder 比 StringBuffer 效率高
 */
public class Q56_Abbreviate {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.charAt(0));
        }
        System.out.println(sb);
    }
}
