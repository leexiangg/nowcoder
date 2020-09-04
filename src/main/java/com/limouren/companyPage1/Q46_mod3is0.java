package com.limouren.companyPage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 被三整除
 * 小Q得到一个神奇的数列: 1, 12, 123,...12345678910,1234567891011...。
 * 并且小Q对于能否被3整除这个性质很感兴趣。
 * 小Q现在希望你能帮他计算一下从数列的第l个到第r个(包含端点)有多少个数可以被3整除。
 * https://www.nowcoder.com/practice/51dcb4eef6004f6f8f44d927463ad5e8?tpId=182&&tqId=34320&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q46_mod3is0 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        while ((s = reader.readLine()) != null) {
            String[] ss = s.split(" ");
            int l = Integer.parseInt(ss[0]);
            int r = Integer.parseInt(ss[1]);
            System.out.println(calc(l, r));
        }
    }

    /**
     * 找规律：这个数列序号 n 如果满足 n % 3 != 1，这个数列就可以被3整除
     * 1  false
     * 12  true
     * 123  true
     * 1234  false
     * 12345  true
     * 123456  true
     * 1234567  false
     * 12345678  true
     * 123456789  true
     * 12345678910  false
     * 1234567891011  true
     * 123456789101112  true
     * 12345678910111213  false
     * 1234567891011121314  true
     * 123456789101112131415  true
     * 12345678910111213141516  false
     * 1234567891011121314151617  true
     * 123456789101112131415161718  true
     * 12345678910111213141516171819  false
     * 1234567891011121314151617181920  true
     * 123456789101112131415161718192021  true
     * 12345678910111213141516171819202122  false
     * 1234567891011121314151617181920212223  true
     * 123456789101112131415161718192021222324  true
     * 12345678910111213141516171819202122232425  false
     * 1234567891011121314151617181920212223242526  true
     * 123456789101112131415161718192021222324252627  true
     * 12345678910111213141516171819202122232425262728  false
     * 1234567891011121314151617181920212223242526272829  true
     * 123456789101112131415161718192021222324252627282930  true
     * @param l 左
     * @param r 右
     * @return 总数
     */
    public static int calc(int l, int r) {
        if(l == r && l % 3 == 1)
            return 0;
        if(l == r && l % 3 != 1)
            return 1;
        if(l % 3 == 1 && r % 3 == 1)
            return (r - l) / 3 * 2;
        if((l % 3 == 2 && r % 3 == 2) || (l % 3 == 0 && r % 3 == 0))
            return (r - l) / 3 * 2 + 1;
        if(l % 3 == 0 && r % 3 == 2)
            return (r - l + 1) / 3 * 2;
        if(l % 3 == 2 && r % 3 == 0)
            return (r - l + 2) / 3 * 2;
        return 0;
    }
}
