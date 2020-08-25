package com.limouren.company;

import java.util.*;

/**
 * 下厨房
 * 牛牛想尝试一些新的料理，每个料理需要一些不同的材料，问完成所有的料理需要准备多少种不同的材料。
 * https://www.nowcoder.com/practice/ca5c9ba9ebac4fd5ae9ba46114b0f476?tpId=182&&tqId=34282&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q8_Cook {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Set<String> set = new HashSet<>();
        while(scan.hasNext()) {
            String line = scan.nextLine();
            set.addAll(Arrays.asList(line.split(" ")));
        }
        System.out.println(set.size());
    }
}
