package com.wxstc.dl;

import me.brucezz.crawler.bean.DY;
import java.util.concurrent.Executors;

import me.brucezz.crawler.config.Config;
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
import java.util.concurrent.ExecutorService;

public class SuperClass extends Thread {
    public int i = 137689;
    public int maxI = 0;
    HttpHost proxy = null;
    public String path = "/superclass/dy-1.0-SNAPSHOT.jar";
    public String[] Myproxy = {
            "39.106.117.115:3128",
            "47.94.196.195:3128",
            "86.110.118.160:3128",
            "no"
            //"49.89.54.78:808",
    };
    public FileWriter fw = null;

    /*public SuperClass() throws IOException {
        FileInputStream fileInputStream = null;
        BufferedReader reader = null;
        File = new File(this.path)
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
            SuperClassUserApi user = JsonUtils.jsonToPojo(result, SuperClassUserApi.class);
            this.i=user.data.studentId+1;
        }
    }*/

    public SuperClass(String path, int i) throws IOException {
        this.path=path;
        this.i=i;
        this.maxI=i+2000000;
        fw=new FileWriter(new File(path), true);
        FileInputStream fileInputStream = null;
        BufferedReader reader = null;
        File f = new File(this.path);
        if(!f.exists()){
            f.createNewFile();
        }
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
            SuperClassUserApi user = JsonUtils.jsonToPojo(result, SuperClassUserApi.class);
            this.i=user.data.studentId+1;
        }
    }

    @Override
    public synchronized void run() {
        // 创建Http Post请求
        HttpPost httpPost = new HttpPost("http://120.55.151.61/V2/Student/getTopInfoById.action");
        RequestConfig requestConfig = RequestConfig.custom().build();//.setProxy(proxy)
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Cookie","JSESSIONID=365F006ECBFD7FF259073B2BDD587CA0-memcached1");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        while (true) {
            String resultString = "";
                try {
                    //HttpPost httpPost = new HttpPost("http://music.163.com/store/api/storecoupon/bindCoupon");
                    Map<String, String> param = new HashMap<String, String>();
                    String paramString = "studentId="+this.i+"&versionNumber=9.3.0&platform=2&channel=AppStore&phoneVersion=11.2.5&phoneModel=iPhone%207&phoneBrand=Apple";
                    if(!paramString.equals("")){
                        String[] split = paramString.split("&");
                        for (String s:split) {
                            String[] ppam = s.split("=");
                            param.put(ppam[0],ppam[1]);
                        }
                    }
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
                    if(resultString.indexOf("\"message\":\"error\"")>0){

                    }else {
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(resultString+"\n");
                        bw.flush();
                    }
                    if(i>=this.maxI){
                        Thread.currentThread().stop();
                    }else {
                        this.i++;
                    }
                    //System.out.println(i+resultString);
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

    public static void main2(String args[]) {
        final String File_Path = Config.File_Path;
        //final String File_Path = "/superclass";
        try {
            //SuperClass d = new SuperClass();
//            SuperClass d1 = new SuperClass(File_Path+"/SuperClassUser1.txt",0);
//            SuperClass d2 = new SuperClass(File_Path+"/SuperClassUser2.txt",2000000);
//            SuperClass d3 = new SuperClass(File_Path+"/SuperClassUser3.txt",4000000);
//            SuperClass d4 = new SuperClass(File_Path+"/SuperClassUser4.txt",6000000);
//            SuperClass d5 = new SuperClass(File_Path+"/SuperClassUser5.txt",8000000);
//            SuperClass d6 = new SuperClass(File_Path+"/SuperClassUser6.txt",10000000);
//            SuperClass d7 = new SuperClass(File_Path+"/SuperClassUser7.txt",12000000);
//            SuperClass d8 = new SuperClass(File_Path+"/SuperClassUser8.txt",14000000);
//            SuperClass d9 = new SuperClass(File_Path+"/SuperClassUser9.txt",16000000);
//            SuperClass d11 = new SuperClass(File_Path+"/SuperClassUser10.txt",18000000);
//            SuperClass d12 = new SuperClass(File_Path+"/SuperClassUser11.txt",20000000);
//            SuperClass d13 = new SuperClass(File_Path+"/SuperClassUser12.txt",22000000);
//            SuperClass d14 = new SuperClass(File_Path+"/SuperClassUser13.txt",24000000);
//            SuperClass d15 = new SuperClass(File_Path+"/SuperClassUser14.txt",26000000);
//            SuperClass d16 = new SuperClass(File_Path+"/SuperClassUser15.txt",28000000);
            SuperClass d17 = new SuperClass(File_Path+"/SuperClassUser16.txt",30000000);
            d17.maxI=31000000;
//            Thread thread1 = new Thread(d1);
//            Thread thread2 = new Thread(d2);
//            Thread thread3 = new Thread(d3);
//            Thread thread4 = new Thread(d4);
//            Thread thread5 = new Thread(d5);
//            Thread thread6 = new Thread(d6);
//            Thread thread7 = new Thread(d7);
//            Thread thread8 = new Thread(d8);
//            Thread thread9 = new Thread(d9);
//            Thread thread11 = new Thread(d11);
//            Thread thread12 = new Thread(d12);
//            Thread thread13 = new Thread(d13);
//            Thread thread14 = new Thread(d14);
//            Thread thread15 = new Thread(d15);
//            Thread thread16 = new Thread(d16);
            Thread thread17 = new Thread(d17);
//            thread1.start();
//            thread2.start();
//            thread3.start();
//            thread4.start();
//            thread5.start();
//            thread6.start();
//            thread7.start();
//            thread8.start();
//            thread9.start();
//            thread11.start();
//            thread12.start();
//            thread13.start();
//            thread14.start();
//            thread15.start();
//            thread16.start();
            thread17.start();
//            ExecutorService pool = Executors.newFixedThreadPool(15);
//            pool.execute(thread1);
//            pool.execute(thread2);
//            pool.execute(thread3);
//            pool.execute(thread4);
//            pool.execute(thread5);
//            pool.execute(thread6);
//            pool.execute(thread7);
//            pool.execute(thread8);
//            pool.execute(thread9);
//            pool.execute(thread11);
//            pool.execute(thread12);
//            pool.execute(thread13);
//            pool.execute(thread14);
//            pool.execute(thread15);
//            pool.execute(thread16);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("文件读取错误");
        }
    }

   public static void main(String args[]) throws Exception {

   }
}

