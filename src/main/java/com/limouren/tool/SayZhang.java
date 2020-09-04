package com.limouren.tool;

import com.limouren.common.HttpProxy;
import com.limouren.common.HttpsClientUtil;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * 专家说说实时通知
 */
public class SayZhang extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel jltd = new JLabel();
    private int width, height;
    private static String top;

    public SayZhang(int w, int h) {
        this.setSize(w, h);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        width = ((int) dimension.getWidth() - w) / 2;
        height = (int) dimension.getHeight() - h - 40;
        this.setLocation(width, height); //(int)dimension.getHeight() - h);
        this.setAlwaysOnTop(true); //窗体总在最前面
        this.setResizable(false); //窗体不能改变大小
        this.setUndecorated(true); //窗体不要边框
        this.setOpacity(0.5f);
        this.getContentPane().setLayout(new GridLayout(1, 1));
        jltd.setFont(new Font("Serif", Font.BOLD, 14));
        jltd.setOpaque(true);
        jltd.setBackground(Color.WHITE);
        jltd.setForeground(Color.GRAY);
        this.getContentPane().add(jltd);
        this.setVisible(true);
        this.setText();
    }

    /**
     * https://mingjia.cngold.org/expert/1868756/sayIndex.htm
     */
    public void setText() {
        // 只取最新得一条
        String url = "https://quanzi.cngold.org/say/userLine/1868756/1/1/?&_=";
        while (true) {
            HttpsClientUtil https = new HttpsClientUtil();
            try {
                Map<String, String> header = new HashMap<>();
                header.put("Referer", "https://mingjia.cngold.org/expert/1868756/sayIndex.htm");
                https.setBaseheader(header);
                // 设置代理
                String result = null;
                do {
                    String ipPort = HttpProxy.getRandom();
                    String[] proxyIpPort = ipPort.split(":");
                    https.setProxy(true, proxyIpPort[0], Integer.parseInt(proxyIpPort[1]));
                    result = https.doGet(url + new Date().getTime(), false);
//                        HttpProxy.rmProxy(ipPort);
//                    else
//                        break;
                } while (result == null || result.length() <= 0);
                result = result.substring(result.indexOf("\"content\":\"") + 11);
                result = result.substring(0, result.indexOf("\",\"pictures\":"));
                System.out.println(result);
                if(!result.equals(top)) {
                    if(result.indexOf("做多") > 0) {
                        jltd.setForeground(Color.RED);
                        top = result;
                    } else if(result.indexOf("做空") > 0) {
                        jltd.setForeground(Color.GREEN);
                        top = result;
                    } else {
                        jltd.setForeground(Color.BLUE);
                        top = result;
                    }
                } else {
                    jltd.setForeground(Color.BLACK);
                }
                jltd.setText(result);
                Thread.sleep(180000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] _s) {
        SayZhang ff = new SayZhang(1920, 20);
    }
}
