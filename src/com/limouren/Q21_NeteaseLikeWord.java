package com.limouren;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 小易喜欢的单词
 * 小易喜欢的单词具有以下特性：
 * 1.单词每个字母都是大写字母
 * 2.单词没有连续相等的字母
 * 3.单词没有形如“xyxy”(这里的x，y指的都是字母，并且可以相同)这样的子序列，子序列可能不连续。
 * 例如：
 * 小易不喜欢"ABBA"，因为这里有两个连续的'B'
 * 小易不喜欢"THETXH"，因为这里包含子序列"THTH"
 * 小易不喜欢"ABACADA"，因为这里包含子序列"AAAA"
 * 小易喜欢"A","ABA"和"ABCBA"这些单词
 * 给你一个单词，你要回答小易是否会喜欢这个单词（只要不是不喜欢，就是喜欢）。
 * https://www.nowcoder.com/practice/ca7b8af83e2f4ec1af2f23d6733223b5?tpId=182&&tqId=34295&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 */
public class Q21_NeteaseLikeWord {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            char[] str = scan.next().toCharArray();
            boolean BBTHTHAAAA = true;
            // 字符桶
            char[] charBucket = new char[str.length];
            Arrays.fill(charBucket, (char) 0);
            // 字符的位置信息，从 1 开始，最多记 3 次
            int[][] countBucket = new int[str.length][3];
            for (int i = 0; i < str.length; i++) {
                if(!BBTHTHAAAA) break;
                if(i > 0 && str[i] == str[i - 1]) {
                    BBTHTHAAAA = false;
                    break;
                }
                for (int j = 0; j <= i; j++) {
                    // 如果桶中已有字符，直接记录位置
                    if(charBucket[j] == str[i]) {
                        for (int k = 0; ; k++) {
                            if(k == 3) {
                                BBTHTHAAAA = false;
                                break;
                            }
                            if(countBucket[j][k] == 0) {
                                countBucket[j][k] = i + 1;
                                break;
                            }
                        }
                        break;
                    }
                    // 如果桶中没有字符，先记录字符，再记录位置
                    else if(charBucket[j] == (char) 0) {
                        charBucket[j] = str[i];
                        countBucket[j][0] = i + 1;
                        break;
                    }
                }
            }
            for (int i = 0; i < countBucket.length; i++) {
                if(countBucket[i].length > 1) {
                    for (int j = i + 1; j < countBucket.length; j++) {
                        if(countBucket[j].length > 1) {
                            int count = 0;
                            for (int k = 0; k < countBucket[i].length; k++) {
                                for (int l = 0; l < countBucket[j].length; l++) {
                                    if(countBucket[i][k] != 0 && countBucket[j][l] != 0
                                            && countBucket[i][k] < countBucket[j][l]
                                            && (k < 2 && countBucket[i][k+1] > countBucket[j][l])) {
                                        System.out.println(i + " "+ j + " " + k + " " + l);
                                        count ++;
                                        break;
                                    }
                                }
                            }
                            if(count > 1) {
                                BBTHTHAAAA = false;
                                break;
                            }
                        }
                    }
                    if(!BBTHTHAAAA) break;
                }
            }
            if(!BBTHTHAAAA) {
                System.out.println("Dislikes");
            } else {
                System.out.println("Likes");
            }
        }
    }
}
