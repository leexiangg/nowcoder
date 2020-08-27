package com.limouren.company;

import java.util.*;

/**
 * 构造队列
 * 小明同学把1到n这n个数字按照一定的顺序放入了一个队列Q中。现在他对队列Q执行了如下程序：
 * while(!Q.empty())              //队列不空，执行循环
 * {
 *     int x=Q.front();            //取出当前队头的值x
 *     Q.pop();                 //弹出当前队头
 *     Q.push(x);               //把x放入队尾
 *     x = Q.front();              //取出这时候队头的值
 *     printf("%d\n",x);          //输出x
 *     Q.pop();                 //弹出这时候的队头
 * }
 * 做取出队头的值操作的时候，并不弹出当前队头。
 * 小明同学发现，这段程序恰好按顺序输出了1,2,3,...,n。现在小明想让你构造出原始的队列，你能做到吗？[注：原题样例第三行5有错，应该为3，以下已修正]
 * https://www.nowcoder.com/practice/657d09e2b3704574814089ba8566d98d?tpId=182&&tqId=34299&rp=1&ru=/ta/exam-all&qru=/ta/exam-all/question-ranking
 * 难度：*
 */
public class Q25_ConstructQueue {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int T = scan.nextInt();
            while(T -- > 0) {
                int n = scan.nextInt();
                LinkedList<Integer> queue = new LinkedList<>();
                for (int i = n; i > 0; i--) {
                    queue.add(i);
                    queue.add(queue.poll());
                }
                StringBuffer sb = new StringBuffer();
                while (queue.size() > 0) {
                    sb.append(queue.removeLast() + " ");
                }
                System.out.println(sb.toString().trim());
            }
        }
    }
}
