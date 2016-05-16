package com.ustc.hardy.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Author: Created by QuincyHu on 2016/5/16 0016 14:53.
 * Email:  zhihuqunxing@163.com
 */
//实现和服务器的交互
public class HttpUtil {

    //向服务器发送数据请求
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine())!= null){
                        response.append(line);
                    }
                    if(listener != null)
                        listener.onFinish(response.toString());    //回调onFinish()方法

                }catch (Exception e){
                    if(listener != null)
                        listener.onError(e);
                }finally {
                    if(connection != null)
                        connection.disconnect();
                }
            }
        }).start();
    }

    //请求数据后处理
    public interface HttpCallbackListener{
        void onFinish(String response);
        void onError(Exception e);
    }


}

