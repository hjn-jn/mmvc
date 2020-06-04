package com.util.rabbitMQ;

import sun.misc.BASE64Encoder;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.List;

/**
 * 通过rabbitMQ的管理工具API获得当前信息信息
 * 仅在开启管理插件的时候有效
 */
public class MQ_InformationUtil {

    private static String host ="192.168.10.248";
    private static String port = "15672";
    private static String username = "mq248";
    private static String password = "mq248";
    private static HttpURLConnection httpConn;
    private static BufferedReader in;

    public static void main(String[] args) throws Exception {
        String urlString = "http://" + host + ":" + port + "/api/queues/";
        URL url = new URL(urlString);
        httpConn = (HttpURLConnection) url.openConnection();
        //设置用户名密码
        String auth = username + ":" + password;
        BASE64Encoder enc = new BASE64Encoder();
        String encoding = enc.encode(auth.getBytes());
        httpConn.setDoOutput(true);
        httpConn.setRequestProperty("Authorization", "Basic " + encoding);
        // 建立实际的连接
        httpConn.connect();
        //读取响应
        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            StringBuilder content = new StringBuilder();
            String tempStr = "";
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            while ((tempStr = in.readLine()) != null) {
                content.append(tempStr);
            }
            in.close();
            httpConn.disconnect();
            System.out.println(content.toString());
        } else {
            httpConn.disconnect();
            System.out.println("");
        }
    }


    public MQ_InformationUtil() throws IOException {

    }

    public List loadQueues() throws IOException {
        String urlString = "http://" + host + ":" + port + "/api/queues/";
        URL url = new URL(urlString);
        httpConn = (HttpURLConnection) url.openConnection();
        //设置用户名密码
        String auth = username + ":" + password;
        BASE64Encoder enc = new BASE64Encoder();
        String encoding = enc.encode(auth.getBytes());
        httpConn.setDoOutput(true);
        httpConn.setRequestProperty("Authorization", "Basic " + encoding);
        // 建立实际的连接
        httpConn.connect();
        //读取响应
        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            StringBuilder content = new StringBuilder();
            String tempStr = "";
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            while ((tempStr = in.readLine()) != null) {
                content.append(tempStr);
            }
            in.close();
            httpConn.disconnect();
            System.out.println(content.toString());
        } else {
            httpConn.disconnect();
            System.out.println("");
        }

        List listQueues = null;

        return listQueues;
    }

    public List loadExchanges() throws IOException {
        String urlString = "http://" + host + ":" + port + "/api/queues/";
        URL url = new URL(urlString);
        httpConn = (HttpURLConnection) url.openConnection();
        //设置用户名密码
        String auth = username + ":" + password;
        BASE64Encoder enc = new BASE64Encoder();
        String encoding = enc.encode(auth.getBytes());
        httpConn.setDoOutput(true);
        httpConn.setRequestProperty("Authorization", "Basic " + encoding);
        // 建立实际的连接
        httpConn.connect();
        //读取响应
        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            StringBuilder content = new StringBuilder();
            String tempStr = "";
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            while ((tempStr = in.readLine()) != null) {
                content.append(tempStr);
            }
            in.close();
            httpConn.disconnect();
            System.out.println(content.toString());
        } else {
            httpConn.disconnect();
            System.out.println("");
        }
        List exchangesList = null;

        return exchangesList;
    }

}
