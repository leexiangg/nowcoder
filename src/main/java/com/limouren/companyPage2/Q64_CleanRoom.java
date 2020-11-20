package com.limouren.companyPage2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.nowcoder.com/practice/c32f4c74446541a1ad2abbe54476681f?tpId=182&&tqId=34338&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 又到了周末，小易的房间乱得一团糟。
 * 他希望将地上的杂物稍微整理下，使每团杂物看起来都紧凑一些，没有那么乱。
 * 地上一共有n团杂物，每团杂物都包含4个物品。第i物品的坐标用(ai,bi)表示，小易每次都可以将它绕着(xi,yi)逆时针旋转90°，
 * 这将消耗他的一次移动次数。如果一团杂物的4个点构成了一个面积不为0的正方形，我们说它是紧凑的。
 * 因为小易很懒，所以他希望你帮助他计算一下每团杂物最少需要多少步移动能使它变得紧凑。
 */
public class Q64_CleanRoom {

    /**
     * 穷举
     * 逆时针转四次就转回去了,所以每次直接修改原数组就可以了,不用定义另外的class
     * 一定要注意计算距离的时候用long, 否则要溢出(算平方就行,不用开根号,所以没用double,于是遇到了溢出)
     * 判断是否是正方形: 最短边有4条,最长边有2条
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] points = new int[4][4];
        String[] strs;
        while(n-- >0){
            int cnt = Integer.MAX_VALUE;
            for(int i = 0; i < 4; i++){
                strs = br.readLine().trim().split(" ");
                points[i][0] = Integer.parseInt(strs[0]);
                points[i][1] = Integer.parseInt(strs[1]);
                points[i][2] = Integer.parseInt(strs[2]);
                points[i][3] = Integer.parseInt(strs[3]);
            }

            for(int i = 0; i < 4; i++){
                rotateCounterClockwise(points[0]);
                for(int j = 0; j < 4; j++){
                    rotateCounterClockwise(points[1]);
                    for(int k = 0; k < 4; k++){
                        rotateCounterClockwise(points[2]);
                        for(int l = 0; l < 4; l++){
                            rotateCounterClockwise(points[3]);
                            if(isSquare(points)){
                                int c = (i+1)%4+(j+1)%4+(k+1)%4+(l+1)%4;
                                if(c < cnt) cnt = c;
                            }
                        }
                    }
                }
            }
            System.out.println(cnt == Integer.MAX_VALUE ? -1 : cnt);
        }
        br.close();
    }

    // 逆时针转一次
    private static void rotateCounterClockwise(int[] point){
        int x = point[0];
        point[0] = point[2] + point[3] - point[1];
        point[1] = x - point[2] + point[3];
    }

    private static boolean isSquare(int[][] points){
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        int cnt1 = 0, cnt2 = 0;
        long tmp = 0;
        for(int i = 0; i < 3; i++){
            for(int j = i+1; j < 4; j++){
                tmp = distance(points,i,j);
                if(tmp < min){min = tmp; cnt1 = 1;}
                else if(tmp == min) {cnt1++;}
                if(tmp > max){max = tmp; cnt2 = 1;}
                else if(tmp == max) {cnt2++;}
            }
        }
        return cnt1 == 4 && min != 0 && cnt2 == 2;
    }

    private static long distance(int[][] points, int i, int j){
        return ((long)(points[i][0] - points[j][0]))*(points[i][0] - points[j][0])
                + (points[i][1] - points[j][1])*(points[i][1] - points[j][1]);
    }

}
