package com.limouren.tool;

import com.alibaba.fastjson.JSONObject;
import com.limouren.common.HttpsClientUtil;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.*;

/**
 * 白银行情实时了解
 */
public class FlyFly extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel jltd = new JLabel();
    private JLabel jlx = new JLabel();
    private int width, height;
    private int mark;

    public FlyFly(int w, int h) {
        this.setSize(w, h);
        Dimension dimension =  Toolkit.getDefaultToolkit().getScreenSize();
        width = ((int)dimension.getWidth() - w) / 2;
        height = ((int)dimension.getHeight() - h) / 2;
        // this.setLocation(((int)dimension.getWidth() - w) / 2, ((int)dimension.getHeight() - h) / 2); //(int)dimension.getHeight() - h);
        this.setLocation(width, height); //(int)dimension.getHeight() - h);
        this.setAlwaysOnTop(true); //窗体总在最前面
        this.setResizable(false); //窗体不能改变大小
        this.setUndecorated(true); //窗体不要边框
        this.setOpacity(0.5f);
        this.getContentPane().setLayout(new GridLayout(1, 1));
        jltd.setFont(new Font("Serif", Font.BOLD, 20));
        jltd.setOpaque(true);
        jltd.setBackground(Color.WHITE);
        jltd.setForeground(Color.GRAY);
        jltd.setLocation(0, 0);
        this.getContentPane().add(jltd);
        jlx.setFont(new Font("Serif", Font.BOLD, 20));
        jlx.setOpaque(true);
        jlx.setBackground(Color.WHITE);
        jlx.setForeground(Color.GRAY);
        jlx.setLocation(w / 2, 0);
        this.getContentPane().add(jlx);
        this.setVisible(true);
        this.setText();
    }

    public void setText() {
        String url = "https://api.jijinhao.com/realtime/quotejs.htm?codes=JO_9754%2CJO_92232&currentPage=1&pageSize=2&_=";
        while(true) {
            HttpsClientUtil https = new HttpsClientUtil();
            try {
                Map<String, String> header = new HashMap<>();
                header.put("Referer", "https://www.cngold.org/img_date/livesilvercn_rmb.html");
                https.setBaseheader(header);
                String result = https.doGet(url + new Date().getTime(), false);
                parseResponse(result).forEach(t -> {
                    if("AgT+D".equals(t.getQ68())) {
                        setJLable(t, jltd);
                        this.setLocation(width, Math.max(0, height + (mark - Integer.parseInt(t.getQ70())) * 50));
                        mark = Integer.parseInt(t.getQ70());
                    } else {
                        setJLable(t, jlx);
                    }
                });
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setJLable(Quot t, JLabel jLabel) {
        double q70 = Double.parseDouble(t.getQ70());
        if (q70 > 0) {
            jLabel.setForeground(Color.RED);
        } else if (q70 < 0) {
            jLabel.setForeground(Color.GREEN);
        } else {
            jLabel.setForeground(Color.black);
        }
        jLabel.setText(t.toString());
    }

    public List<Quot> parseResponse(String str) {
        List<Quot> quots = new ArrayList<>();
        try {
            System.out.println(str);
            str = str.replace("var quot_str = [", "");
            str = str.substring(0, str.length() - 1);
            JSONObject json = JSONObject.parseObject(str);
            AtomicReference<String> ret = new AtomicReference<>("");
            if(json != null) {
                List<JSONObject> data = (List<JSONObject>) json.get("data");
                data.forEach(t -> {
                    JSONObject quote = (JSONObject)t.get("quote");
                    Quot quot = new Quot(quote.get("q68").toString(), quote.get("q63").toString(), quote.get("q70").toString());
                    quots.add(quot);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quots;
    }

    static class Quot {
        /**
         * 合约代码
         */
        private String q68;
        /**
         * 现价
         */
        private String q63;
        /**
         * 涨跌幅度
         */
        private String q70;

        public Quot(String q68, String q63, String q70) {
            this.q68 = q68;
            this.q63 = q63;
            this.q70 = q70;
        }

        public String getQ68() {
            return q68;
        }

        public void setQ68(String q68) {
            this.q68 = q68;
        }

        public String getQ63() {
            return q63;
        }

        public void setQ63(String q63) {
            this.q63 = q63;
        }

        public String getQ70() {
            return q70;
        }

        public void setQ70(String q70) {
            this.q70 = q70;
        }

        @Override
        public String toString() {
            return q63 + " " + q70 + "  ";
        }
    }

    public static void main(String[] _s) {
        FlyFly ff = new FlyFly(210, 30);
    }
}
