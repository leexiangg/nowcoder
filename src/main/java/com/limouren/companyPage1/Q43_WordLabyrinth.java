package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 字符迷阵
 * 字符迷阵是一种经典的智力游戏。玩家需要在给定的矩形的字符迷阵中寻找特定的单词。
 * 在这题的规则中，单词是如下规定的：
 * 1. 在字符迷阵中选取一个字符作为单词的开头；
 * 2. 选取右方、下方、或右下45度方向作为单词的延伸方向；
 * 3. 以开头的字符，以选定的延伸方向，把连续得到的若干字符拼接在一起，则称为一个单词。
 * 以图1为例，如果要在其中寻找单词"WORD"，则绿色框所标示的都是合法的方案，而红色框所标示的都是不合法的方案。
 * 现在的问题是，给出一个字符迷阵，及一个要寻找的单词，问能在字符迷阵中找到多少个该单词的合法方案。注意合法方案是可以重叠的，如图1所示的字符迷阵，其中单词"WORD"的合法方案有4种。
 * https://www.nowcoder.com/practice/8fb1e165abcb4b709d5a2f0ba759d0a6?tpId=182&&tqId=34317&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：***
 */
public class Q43_WordLabyrinth {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        Input[] inputs = new Input[T];
        for (int i = 0; i < T; i++) {
            String[] rc = br.readLine().split(" ");
            int row = Integer.parseInt(rc[0]);
            int col = Integer.parseInt(rc[1]);
            char[][] arr = new char[row][col];
            for (int j = 0; j < row; j++) {
                arr[j] = br.readLine().toCharArray();
            }
            char[] desc = br.readLine().toCharArray();
            inputs[i] = new Input(row, col, arr, desc);
        }
        StringBuilder result = new StringBuilder();
        for (Input input : inputs) {
            result.append(calc(input)).append("\n");
        }
        System.out.println(result);
    }

    /**
     * 遍历每一个点，看这个点的右点、右下、下点是否符合要求
     * @param input
     * @return
     */
    public static String calc(Input input) {
        int sum = 0;
        for (int i = 0; i < input.row; i++) {
            for (int j = 0; j < input.col; j++) {
                if(input.arr[i][j] == input.desc[0]) {
                    // 记录右点、右下、下点
                    int right = 1, right_down = 1, down = 1;
                    for (int k = 1; k < input.desc.length; k++) {
                        if(i + k >= input.row || input.arr[i + k][j] != input.desc[k])
                            right &= 0;
                        if(i + k >= input.row || j + k >= input.col || input.arr[i + k][j + k] != input.desc[k])
                            right_down &= 0;
                        if(j + k >= input.col || input.arr[i][j + k] != input.desc[k])
                            down &= 0;
                        if(right == 0 && right_down == 0 && down == 0)
                            break;
                    }
                    sum += right + right_down + down;
                }
            }
        }
        return "" + sum;
    }

    static class Input {
        public int row;
        public int col;
        public char[][] arr;
        public char[] desc;

        public Input(int row, int col, char[][] arr, char[] desc) {
            this.row = row;
            this.col = col;
            this.arr = arr;
            this.desc = desc;
        }
    }
}
