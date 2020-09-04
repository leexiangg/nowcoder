package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 矩形重叠
 * 平面内有n个矩形, 第i个矩形的左下角坐标为(x1[i], y1[i]), 右上角坐标为(x2[i], y2[i])。
 * 如果两个或者多个矩形有公共区域则认为它们是相互重叠的(不考虑边界和角落)。
 * 请你计算出平面内重叠矩形数量最多的地方,有多少个矩形相互重叠。
 * https://www.nowcoder.com/practice/a22dd98b3d224f2bb89142f8acc2fe57?tpId=182&&tqId=34324&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：***
 */
public class Q50_RectangleOverlapping {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] xy = new int[4][n];
        for (int i = 0; i < 4; i++) {
            String str = br.readLine().replace("  ", " ");
            if(str.length() > 0) {
                String[] xy_str = str.split(" ");
                for (int j = 0; j < n; j++) {
                    xy[i][j] = Integer.parseInt(xy_str[j]);
                }
            } else {
                i --;
            }
        }
        System.out.println(calc(n, xy));
    }

    public static int calc(int n, int[][] xy) {
        int tmp = 0, max = 0;
        for(int x : xy[0])
            for(int y : xy[1]){
                for(int i = 0; i < n; i++){
                    if(x >= xy[0][i] && x < xy[2][i] && y >= xy[1][i] && y < xy[3][i])
                        tmp++;
                }
                if(max < tmp)
                    max = tmp;
                tmp = 0;
            }
        return max;
    }
}
