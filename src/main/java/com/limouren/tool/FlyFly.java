package com.limouren.tool;

import com.alibaba.fastjson.JSONArray;
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
    private double hisRate = 6.8375;
    private long hisRateTime = 0;

    public FlyFly(int w, int h) {
        this.setSize(w, h);
        Dimension dimension =  Toolkit.getDefaultToolkit().getScreenSize();
//        width = ((int)dimension.getWidth() - w) / 2;
//        height = ((int)dimension.getHeight() - h) / 2;
        width = ((int)dimension.getWidth() - w);
        height = ((int)dimension.getHeight() - h - 36);
        // this.setLocation(((int)dimension.getWidth() - w) / 2, ((int)dimension.getHeight() - h) / 2); //(int)dimension.getHeight() - h);
        this.setLocation(width, height); //(int)dimension.getHeight() - h);
        this.setAlwaysOnTop(true); //窗体总在最前面
        this.setResizable(false); //窗体不能改变大小
        this.setUndecorated(true); //窗体不要边框
        this.setOpacity(0.8f);
        this.getContentPane().setLayout(new GridLayout(2, 1));
        jltd.setFont(new Font("Serif", Font.BOLD, 16));
        jltd.setOpaque(true);
        jlx.setSize(w, h / 2);
        jltd.setBackground(Color.BLACK);
        jltd.setForeground(Color.WHITE);
        jltd.setLocation(0, 0);
        this.getContentPane().add(jltd);
        jlx.setFont(new Font("Serif", Font.BOLD, 16));
        jlx.setOpaque(true);
        jlx.setSize(w, h / 2);
        jlx.setBackground(Color.BLACK);
        jlx.setForeground(Color.WHITE);
        jlx.setLocation(0, h / 2);
        this.getContentPane().add(jlx);
        this.setVisible(true);
        this.setText();
    }

    /**
     * 设置文字
     */
    public void setText() {
        while(true) {
            try {
                // 获取行情
                getQuot().forEach(t -> {
                    if ("AgT+D".equals(t.getQ68())) {
                        // 设置整体控件根据行情波动上下浮动
//                        this.setLocation(width, Math.max(0, height + (mark - Integer.parseInt(t.getQ70())) * 50));
                        t.setQ63("" + Double.valueOf(t.getQ63()).intValue());
                        t.setQ70("" + Double.valueOf(t.getQ70()).intValue());
                        jltd.setText(t.toString());
                    } else {
                        // 根据实时汇率换算 美元/盎司 成为 元/kg，31.1034768
                        double q63 = Double.parseDouble(t.getQ63()); // 现价
                        double q70 = Double.parseDouble(t.getQ70()); // 涨跌幅度
                        double rate = getRate();
                        t.setQ63("" + Double.valueOf(q63 / 31.1034768 * rate * 1000).intValue());
                        t.setQ70("" + Double.valueOf(q70 / 31.1034768 * rate * 1000).intValue());
                        jlx.setText(t.toString());
                    }
                });
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据涨跌设置文字颜色
     * @param t
     * @param jLabel
     */
    private void setJLable(Quot t, JLabel jLabel) {
//        double q70 = Double.parseDouble(t.getQ70());
//        if (q70 > 0) {
//            jLabel.setForeground(Color.RED);
//        } else if (q70 < 0) {
//            jLabel.setForeground(Color.GREEN);
//        } else {
//            jLabel.setForeground(Color.black);
//        }
        jLabel.setText(t.toString());
    }

    /**
     * 获取汇率
     * 一个小时获取一次
     * http://www.webmasterhome.cn/huilv/USD/USDCNY/
     * @return
     */
    private double getRate() {
        if(new Date().getTime() - hisRateTime > 3600000) {
            try {
                String url = "http://www.webmasterhome.cn/huilv/USD/USDCNY/";
                HttpsClientUtil https = new HttpsClientUtil();
                Map<String, String> header = new HashMap<>();
                header.put("Referer", "http://www.webmasterhome.cn/");
                https.setBaseheader(header);
                String str = https.doGet(url, false);
                System.out.println(str);
                // <span class="mexl">6.8375</span>
                int startIndex = str.indexOf("<span class=\"mexl\">") + 19;
                int endIndex = str.indexOf("</span>", startIndex);
                str = str.substring(startIndex, endIndex);
                hisRate = Double.parseDouble(str);
                hisRateTime = new Date().getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hisRate;
    }

    /**
     * 获取白银行情
     * @return
     */
    public List<Quot> getQuot() {
        try {
            String url = "http://limouren.com:8080/quot/quot";
            HttpsClientUtil https = new HttpsClientUtil();
            String str = https.doGet(url, false);
            System.out.println(str);
            return JSONArray.parseArray(str, Quot.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 获取白银行情
     * @return
     */
    public List<Quot> getQuot1() {
        List<Quot> quots = new ArrayList<>();
        try {
            String url = "https://api.jijinhao.com/realtime/quotejs.htm?codes=JO_9754%2CJO_92232&currentPage=1&pageSize=2&_=";
            HttpsClientUtil https = new HttpsClientUtil();
            Map<String, String> header = new HashMap<>();
            header.put("Referer", "https://www.cngold.org/img_date/livesilvercn_rmb.html");
            https.setBaseheader(header);
            String str = https.doGet(url + new Date().getTime(), false);
            System.out.println(str);
            str = str.replace("var quot_str = [", "");
            str = str.substring(0, str.length() - 1);
            JSONObject json = JSONObject.parseObject(str);
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

    /**
     * 行情
     */
    public static class Quot {
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
            this.q63 = "" + Double.valueOf(q63).intValue();
        }

        public String getQ70() {
            return q70;
        }

        public void setQ70(String q70) {
            this.q70 = "" + Double.valueOf(q70).intValue();
        }

        @Override
        public String toString() {
            return q63 + " " + q70 + "  ";
        }
    }

    public static void main(String[] _s) {
        FlyFly ff = new FlyFly(65, 36);
    }
}
