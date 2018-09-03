package com.wxstc.dl;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DouBanPeople implements Runnable,Serializable{

    public static void main(String args[]) throws Exception {
        DouBanPeople p = new DouBanPeople();

        Thread p1 = new Thread(p);
        p1.start();
    }

    public void run() {
        // 创建Http Post请求
        HttpPost httpPost = new HttpPost("https://www.douban.com/people/134002125/");
        RequestConfig requestConfig = RequestConfig.custom().build();//.setProxy(proxy)
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.90 Safari/537.36 2345Explorer/9.2.1.17116");
        httpPost.setHeader("Cookie","bid=spdpwL7jRv0; douban-fav-remind=1; _pk_ref.100001.8cb4=%5B%22%22%2C%22%22%2C1534296012%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DDIh1OyW1s1CroA2EPNg4AcHphWip1HXSwtxN8h_Nl_UuwaybC6RSGk9mfWps-Eky%26wd%3D%26eqid%3Dba176b440003aa4f000000045b737fc9%22%5D; loc-last-index-location-id=\"118163\"; ll=\"118163\"; _vwo_uuid_v2=D21BC5CEE367A9C36D682DF41F3B9772D|516760e681d90f90677ce09c5bca7541; _pk_id.100001.8cb4=55c089674f253753.1532426244.2.1534298371.1532426244.; _pk_ses.100001.8cb4=*; __utmt=1; __utma=30149280.1158567552.1534297455.1534297455.1534297455.1; __utmb=30149280.4.10.1534297455; __utmc=30149280; __utmz=30149280.1534297455.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ap=1");

        CloseableHttpClient httpClient = null;//HttpClients.createDefault();
        try {
            httpClient = LaGouCommanyRes.buildSSLCloseableHttpClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CloseableHttpResponse response = null;
        boolean flag = true;
        while (flag) {
            //flag = false;
            String resultString = "";
            try {
//                HttpPost httpPost = new HttpPost("http://music.163.com/store/api/storecoupon/bindCoupon");
                Map<String, String> param = new HashMap<String, String>();
//                String paramString = "studentId="+1+"&versionNumber=9.3.0&platform=2&channel=AppStore&phoneVersion=11.2.5&phoneModel=iPhone%207&phoneBrand=Apple";
//                if(!paramString.equals("")){
//                    String[] split = paramString.split("&");
//                    for (String s:split) {
//                        String[] ppam = s.split("=");
//                        param.put(ppam[0],ppam[1]);
//                    }
//                }
                //param.put("couponCode","MXN0F29N3");
                //param.put("couponCode", "MBFHF6MMM");
                //param.put("noConfirm", "1");
                // 创建参数列表
                if (param != null) {
                    List<NameValuePair> paramList = new ArrayList();
                    for (String key : param.keySet()) {
                        paramList.add(new BasicNameValuePair(key, param.get(key)));
                    }
                    // 模拟表单
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                    httpPost.setEntity(entity);
                }
                // 执行http请求
                response = httpClient.execute(httpPost);
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
//                if(resultString.indexOf("\"message\":\"error\"")>0){
//
//                }else {
//                    BufferedWriter bw = new BufferedWriter(fw);
//                    bw.write(resultString+"\n");
//                    bw.flush();
//                }
//                if(i>=this.maxI){
//                    Thread.currentThread().stop();
//                }else {
//                    this.i++;
//                }
                System.out.println(resultString);
            } catch (Exception e) {
                e.printStackTrace();
                new Thread(this).start();
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
