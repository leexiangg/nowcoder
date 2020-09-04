package com.limouren.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * http 代理
 */
public class HttpProxy {

    private static List<String> proxyPool;

    public static String getRandom() throws IOException {
        proxyPool = new ArrayList<>();
        File file = new File(Objects.requireNonNull(HttpProxy.class.getClassLoader().getResource("proxy.txt")).getFile());
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while((line = br.readLine()) != null) {
            proxyPool.add(line);
        }
        br.close();
        return proxyPool.get((int) (Math.random() * proxyPool.size()) % proxyPool.size());
    }

    public static boolean rmProxy(String proxy) throws IOException {
        proxyPool.remove(proxy);
        File file = new File(Objects.requireNonNull(HttpProxy.class.getClassLoader().getResource("proxy.txt")).getFile());
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < proxyPool.size(); i++) {
            bw.write(proxyPool.get(i));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        return true;
    }

}
