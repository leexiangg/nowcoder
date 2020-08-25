package com.limouren.tool;

import com.alibaba.fastjson.JSONObject;
import com.limouren.common.HttpsClientUtil;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.*;

public class FlyFly extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel jl = new JLabel();

    public FlyFly(int w, int h) {
        this.setSize(w, h);
        Dimension dimension =  Toolkit.getDefaultToolkit().getScreenSize();
        // this.setLocation(((int)dimension.getWidth() - w) / 2, ((int)dimension.getHeight() - h) / 2); //(int)dimension.getHeight() - h);
        this.setLocation(((int)dimension.getWidth() - w) / 2, ((int)dimension.getHeight() - h)); //(int)dimension.getHeight() - h);
        this.setAlwaysOnTop(true); //窗体总在最前面
        this.setResizable(false); //窗体不能改变大小
        this.setUndecorated(true); //窗体不要边框
        this.setOpacity(0.5f);
        this.getContentPane().setLayout(new GridLayout(1, 1));
        jl.setFont(new Font("Serif", Font.BOLD, 20));
        jl.setOpaque(true);
        jl.setBackground(Color.WHITE);
        jl.setForeground(Color.GREEN);
        this.getContentPane().add(jl);
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
                jl.setText(parseResponse(result));
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String parseResponse(String str) {
        try {
            System.out.println(str);
            str = str.replace("var quot_str = [", "");
            str = str.substring(0, str.length() - 1);
            JSONObject quot = JSONObject.parseObject(str);
            AtomicReference<String> ret = new AtomicReference<>("");
            if(quot != null) {
                List<JSONObject> data = (List<JSONObject>) quot.get("data");
                data.forEach(t -> {
                    JSONObject quote = (JSONObject)t.get("quote");
                    ret.set(ret.get() + quote.get("q63") + "  " + quote.get("q70") + "  ");
                });
            }
            return ret.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "没有行情";
    }

    public static void main(String[] _s) {
        FlyFly ff = new FlyFly(210, 30);
    }
}
