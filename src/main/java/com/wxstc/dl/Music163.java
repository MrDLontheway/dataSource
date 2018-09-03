package com.wxstc.dl;

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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Music163 extends Thread {
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

    public Music163(){

    }
    public Music163(String path, int i) throws IOException {
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
        //https://www.lagou.com/jobs/4006595.html
        //http://music.163.com/user/home?id=1

        long i = 25594228375l;
        try {
        while (true) {
            HttpGet httpPost = new HttpGet("https://item.jd.com/"+i+".html");
            RequestConfig requestConfig = RequestConfig.custom().build();//.setProxy(proxy)
            httpPost.setConfig(requestConfig);
            //httpPost.setHeader("Cookie","JSESSIONID=365F006ECBFD7FF259073B2BDD587CA0-memcached1");
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.90 Safari/537.36 2345Explorer/9.2.1.17116");
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = null;
            //httpPost.setURI(new URI("http://music.163.com/user/home?id="+i));
            i++;
            String resultString = "";
                    //HttpPost httpPost = new HttpPost("http://music.163.com/store/api/storecoupon/bindCoupon");
//                    Map<String, String> param = new HashMap<String, String>();
//                    String paramString = "studentId="+this.i+"&versionNumber=9.3.0&platform=2&channel=AppStore&phoneVersion=11.2.5&phoneModel=iPhone%207&phoneBrand=Apple";
//                    if(!paramString.equals("")){
//                        String[] split = paramString.split("&");
//                        for (String s:split) {
//                            String[] ppam = s.split("=");
//                            param.put(ppam[0],ppam[1]);
//                        }
//                    }
                    //param.put("couponCode","MXN0F29N3");
                    //param.put("couponCode", "MBFHF6MMM");
                    //param.put("noConfirm", "1");
                    // 创建参数列表
//                    if (param != null) {
//                        List<NameValuePair> paramList = new ArrayList();
//                        for (String key : param.keySet()) {
//                            paramList.add(new BasicNameValuePair(key, param.get(key)));
//                        }
//                        // 模拟表单
//                        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
//                        httpPost.setEntity(entity);
//                    }
                    // 执行http请求
                response = httpClient.execute(httpPost);
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
                Document doc = Jsoup.parse(resultString);
//                Elements user_info = doc.select("dl[id=head-box]");
//                String username = doc.select("h2[id=j-name-wrap]").text();
//                String headImg = doc.select("dl[id=ava]").text();
//                String event_count = doc.select("strong[id=event_count]").text();
//                String follow_count = doc.select("strong[id=follow_count]").text();
//                String fan_count = doc.select("strong[id=fan_count]").text();
//                String user_desc = doc.select("div.f-brk").text();
//                String city = user_info.select("span[id=age]").first()==null?"":user_info.select("span[id=age]").first().text();
//                String age = user_info.select("span[id=age]").attr("data-age");
            String shop_name = doc.select("div.sku-name").text();
            Elements select = doc.select("span.p-price");
            //String price = doc.select("span.p-price")==null?"0":doc.select("span.p-price").(1).text();
            if(resultString.equals("")){

            }else {
                System.out.println(shop_name+":");
                //System.out.println(i+"  昵称:"+username+"  头像:"+headImg+"  动态:"+event_count+"  关注:"+follow_count+"  粉丝:"+fan_count+"  城市:"+city+"  年龄:"+age);
            }
            System.out.println(response.getStatusLine().getStatusCode()+":"+i);
                //System.out.println(i+"  昵称:"+username+"  头像:"+headImg+"  动态:"+event_count+"  关注:"+follow_count+"  粉丝:"+fan_count+"  城市:"+city+"  年龄:"+age);
            }
        } catch (Exception e) {
                e.printStackTrace();
            }
        }


    public static void main2(String args[]) throws IOException {

    }

   public static void main(String args[]) throws Exception {
       Music163 a = new Music163();
       Thread t = new Thread(a);
       a.start();
   }
}

