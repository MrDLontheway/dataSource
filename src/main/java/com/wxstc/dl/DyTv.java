package com.wxstc.dl;

import me.brucezz.crawler.bean.DY;
import me.brucezz.crawler.util.JsonUtils;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.*;

public class DyTv extends Thread {
    public int i = 137689;
    HttpHost proxy = null;
    public String path = "H:\\dyTV.log";
    public String[] Myproxy = {
            "39.106.117.115:3128",
            "47.94.196.195:3128",
            "86.110.118.160:3128",
            "no"
            //"49.89.54.78:808",
    };
    public FileWriter fw = new FileWriter(new File(this.path), true);;

    public DyTv() throws IOException {
        FileInputStream fileInputStream = null;
        BufferedReader reader = null;
        fileInputStream = new FileInputStream(this.path);
        String charset = "UTF-8";
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
        reader = new BufferedReader(inputStreamReader);
        String tmps = null;
        String result = null;
        while ((tmps=reader.readLine())!=null){
            if(!"".equals(tmps)){
                result=tmps;
            }
        }
        if(!"".equals(result)&&result!=null){
            DY dy = JsonUtils.jsonToPojo(result, DY.class);
            this.i=dy.getData().uid+1;
        }
    }

    public DyTv(String path, int i) throws IOException {
        this.path=path;
        this.i=i;
        fw=new FileWriter(new File(path), true);
        FileInputStream fileInputStream = null;
        BufferedReader reader = null;
        fileInputStream = new FileInputStream(this.path);
        String charset = "UTF-8";
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
        reader = new BufferedReader(inputStreamReader);
        String tmps = null;
        String result = null;
        while ((tmps=reader.readLine())!=null){
            if(!"".equals(tmps)){
                result=tmps;
            }
        }
        if(!"".equals(result)&&result!=null){
            DY dy = JsonUtils.jsonToPojo(result, DY.class);
            this.i=dy.getData().uid+1;
        }
    }

    @Override
    public synchronized void run() {
        while (true) {
            CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = null;
                String resultString = "";
                try {
                    // 创建Http Post请求
                    HttpGet httpPost = new HttpGet("https://yuba.douyu.com/wbapi/web/user/detail/" + i);
                    //HttpPost httpPost = new HttpPost("http://music.163.com/store/api/storecoupon/bindCoupon");
                    Map<String, String> param = new HashMap<String, String>();
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
                        //httpPost.setEntity(entity);
                    }
                    //proxy = new HttpHost(res[0], Integer.parseInt(res[1]));
                    //HttpHost proxy=new HttpHost("39.106.117.115", 3128);
                    RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
                    httpPost.setConfig(requestConfig);
                    httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
                    //httpPost.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
                    // 执行http请求
                    response = httpClient.execute(httpPost);
                    resultString = EntityUtils.toString(response.getEntity(), "utf-8");
                    if (resultString.contains("\"status_code\":200")) {
                        //System.out.println(resultString);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(resultString+"\n");
                        bw.flush();
                        String hostName = "";
                        if(proxy!=null){
                            hostName = proxy.getHostName();
                        }
                        System.out.println(hostName+new Date()+resultString);
                        i++;
                    } else {
                        String hostName = "";
                        if(proxy!=null){
                            hostName = proxy.getHostName();
                        }
                        System.out.println(hostName+new Date()+resultString);
                        Random r = new Random();
                        String ips = Myproxy[r.nextInt(Myproxy.length)];
                        if(ips.equals("no")){
                            proxy = null;
                        }else {
                            String[] res = ips.split(":");
                            proxy = new HttpHost(res[0], Integer.parseInt(res[1]));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

   /* public static void main(String args[]) {
        try {
            DyTv d = new DyTv();
            DyTv d2 = new DyTv("H:\\dyTV2.log",10000000);
            DyTv d3 = new DyTv("H:\\dyTV3.log",20000000);
            DyTv d4 = new DyTv("H:\\dyTV4.log",30000000);
            DyTv d5 = new DyTv("H:\\dyTV5.log",40000000);
            DyTv d6 = new DyTv("H:\\dyTV6.log",50000000);
            DyTv d7 = new DyTv("H:\\dyTV7.log",60000000);
            DyTv d8 = new DyTv("H:\\dyTV8.log",70000000);
            DyTv d9 = new DyTv("H:\\dyTV9.log",80000000);

            DyTv d10 = new DyTv("H:\\dyTV9.9.log",90000000);
            DyTv d11 = new DyTv("H:\\dyTV10.log",100000000);
            DyTv d12 = new DyTv("H:\\dyTV11.log",110000000);
            DyTv d13 = new DyTv("H:\\dyTV12.log",120000000);
            DyTv d14 = new DyTv("H:\\dyTV13.log",130000000);
            DyTv d15 = new DyTv("H:\\dyTV14.log",140000000);
            DyTv d16 = new DyTv("H:\\dyTV15.log",150000000);
            DyTv d17 = new DyTv("H:\\dyTV16.log",160000000);
            DyTv d18 = new DyTv("H:\\dyTV17.log",170000000);
            //DyTv d19 = new DyTv("H:\\dyTV18.log",180000000);
            Thread thread1 = new Thread(d);
            Thread thread2 = new Thread(d2);
            Thread thread3 = new Thread(d3);
            Thread thread4 = new Thread(d4);
            Thread thread5 = new Thread(d5);
            Thread thread6 = new Thread(d6);
            Thread thread7 = new Thread(d7);
            Thread thread8 = new Thread(d8);
            Thread thread9 = new Thread(d9);

            Thread thread10 = new Thread(d10);
            Thread thread11 = new Thread(d11);
            Thread thread12 = new Thread(d12);
            Thread thread13 = new Thread(d13);
            Thread thread14 = new Thread(d14);
            Thread thread15 = new Thread(d15);
            Thread thread16 = new Thread(d16);
            Thread thread17 = new Thread(d17);
            Thread thread18 = new Thread(d18);
            //Thread thread10 = new Thread(d2);

            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
            thread6.start();
            thread7.start();
            thread8.start();
            thread9.start();

            thread10.start();
            thread11.start();
            thread12.start();
            thread13.start();
            thread14.start();
            thread15.start();
            thread16.start();
            thread17.start();
            thread18.start();
//            thread10.start();
        } catch (Exception e) {
            System.out.println("文件读取错误");
        }
    }*/

   public static void main(String args[]) throws Exception {
//       FileWriter fw=new FileWriter(new File("H:\\dyformat.log"), true);
//       FileInputStream fileInputStream = null;
//       fileInputStream = new FileInputStream("H:\\dyTV.log");
//       BufferedReader reader = null;
//       String charset = "UTF-8";
//       InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
//       reader = new BufferedReader(inputStreamReader);
//       String tmps = null;
//       String result = null;
//       BufferedWriter bw = new BufferedWriter(fw);
//       while ((tmps=reader.readLine())!=null){
//           if(!"".equals(tmps)){
//               result=tmps;
//           }
//           if(!"".equals(result)&&result!=null){
//               DY dy = JsonUtils.jsonToPojo(result, DY.class);
//               bw.write(dy.getData().toString()+"\n");
//               bw.flush();
//           }
//       }
   }
}

