package com.limouren.company;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 扫描透镜
 * 在N*M的草地上,提莫种了K个蘑菇,蘑菇爆炸的威力极大,兰博不想贸然去闯,而且蘑菇是隐形的.只 有一种叫做扫描透镜的物品可以扫描出隐形的蘑菇,于是他回了一趟战争学院,买了2个扫描透镜,一个 扫描透镜可以扫描出(3*3)方格中所有的蘑菇,然后兰博就可以清理掉一些隐形的蘑菇. 问:兰博最多可以清理多少个蘑菇?
 * 注意：每个方格被扫描一次只能清除掉一个蘑菇。
 * https://www.nowcoder.com/practice/6a219d196df44d3abd82fbadb1a62c3f?tpId=182&&tqId=34279&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：***
 */
public class Q5_Mushroom {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            int k = scan.nextInt();
            int[][] mush = new int[n][m];
            while(k-- > 0) {
                int x = scan.nextInt() - 1;
                int y = scan.nextInt() - 1;
                mush[x][y] ++;
            }
            System.out.println(findMush(mush) + findMush(mush));
        }
    }

    public static int findMush(int[][] mush) {
        int x = 0, y = 0, count = 0;
        // 遍历所有方格
        for (int i = 0; i < mush.length - 2; i++) {
            for (int j = 0; j < mush[i].length - 2; j++) {
                int tmp_count = 0;
                // 找蘑菇最多的地方
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if(mush[i + k][j + l] > 0)
                            tmp_count ++;
                    }
                }
                if(tmp_count > count) {
                    count = tmp_count;
                    x = i;
                    y = j;
                }
            }
        }
        // 排除蘑菇
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(mush[x + i][y + j] > 0)
                    mush[x + i][y + j] --;
            }
        }
        return count;
    }
}
