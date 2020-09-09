package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 一封奇怪的信
 * 现在你需要用一台奇怪的打字机书写一封书信。信的每行只能容纳宽度为100的字符，也就是说如果写下某个字符会导致行宽超过100，那么就要另起一行书写
 * 信的内容由a-z的26个小写字母构成，而每个字母的宽度均会事先约定。例如字符宽度约定为[1,2,3,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5]，那么就代表'a'到'd'四个字母的宽度分别是1,2,3,4，而'e'到'z'的宽度均为5
 * 那么按照上述规则将给定内容S书写成一封信后，这封信共有几行？最后一行宽度是多少？
 * https://www.nowcoder.com/practice/d7764905e41a413c98900e22a9cc4ec3?tpId=182&&tqId=34327&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q53_StrangeLetter {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] charLengthStr = br.readLine().split(" ");
        int[] charLength = new int[26];
        for (int i = 0; i < 26; i++) {
            charLength[i] = Integer.parseInt(charLengthStr[i]);
        }
        String letter = br.readLine();
        int line = 0;
        int flag = 0;
        for (int i = 0; i < letter.length(); i++) {
            int length = charLength[letter.charAt(i) - 'a'];
            flag += length;
            if(flag > 100) {
                line ++;
                flag = length;
            }
        }
        System.out.println(line + 1 + " " + flag);
    }
}
