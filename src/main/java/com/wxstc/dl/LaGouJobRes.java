package com.wxstc.dl;

import me.brucezz.crawler.config.Config;
import me.brucezz.crawler.util.JsonUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class LaGouJobRes extends Thread {
    public long i = 1l;
    public int maxI = 0;
    HttpHost proxy = null;
    public String path = "/superclass/dy-1.0-SNAPSHOT.jar";
    public String[] ua = {
        "User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)",
            "User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)",
            "User-Agent:?Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.90 Safari/537.36 2345Explorer/9.2.1.17116",
            "User-Agent:?Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SE 2.X MetaSr 1.0; SE 2.X MetaSr 1.0; .NET CLR 2.0.50727; SE 2.X MetaSr 1.0)",
            "User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)",
            "User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Avant Browser)"
    };
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

    public LaGouJobRes(){

    }
    public LaGouJobRes(String path, int i,int maxI) throws IOException {
        this.i=i;
        this.path=path;
        this.maxI=maxI;
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
            LaGouJob user = JsonUtils.jsonToPojo(result, LaGouJob.class);
            this.i=user.id+1;
        }
    }
    public LaGouJobRes(String path, int i) throws IOException {
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

        //long i = 25594228375l;
        Random r= new Random();
        LaGouJob job = new LaGouJob();
        try {
            CloseableHttpClient client = buildSSLCloseableHttpClient();
            //CloseableHttpClient client = HttpClients.createDefault();
            boolean a = true;
        while (a) {
            if(i>=this.maxI){
                Thread.currentThread().stop();
            }
            //a=false;
            HttpGet httpPost = new HttpGet("https://item.jd.com/"+i+".html");
            RequestConfig requestConfig = RequestConfig.custom().build();//.setProxy(proxy)
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("Cookie","user_trace_token=20171217190338-ef14544a-e319-11e7-9d97-5254005c3644; LGUID=20171217190338-ef14587b-e319-11e7-9d97-5254005c3644; index_location_city=%E5%85%A8%E5%9B%BD; SEARCH_ID=5008dca826ae41f4b0a30a18f794ee37; JSESSIONID=ABAAABAAAIAACBIA0D108B39DC142E9D83FDA8F9984CD22; hideSliderBanner20180305WithTopBannerC=1; TG-TRACK-CODE=index_hotjob; X_HTTP_TOKEN=46eb947944329908b6fc96959e03718a; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2Fjobs%2F39.html%3Fsource%3Dpl%26i%3Dpl-0; _gid=GA1.2.1067653836.1520255739; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1520255738,1520328295; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1520334309; _ga=GA1.2.1276230906.1513508619; LGSID=20180306190220-d754450c-212d-11e8-b126-5254005c3644; LGRID=20180306190507-3ac1ea4e-212e-11e8-9e0e-525400f775ce");
            //httpPost.setHeader("Cookie","JSESSIONID=ABAAABAABEEAAJA2160FFEC2759ED5328C10FF29609FD3E; _ga=GA1.2.1289797741.1517625085; _gat=1; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1517619643,1517621756; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1517625085; user_trace_token=20180306103425-e2dc0a71-20e6-11e8-b126-5254005c3644; LGSID=20180306103425-e2dc0bb2-20e6-11e8-b126-5254005c3644; PRE_UTM=; PRE_HOST=; PRE_SITE=https%3A%2F%2Faccount.lagou.com%2Faccount%2FbindAccountActiveSuccess.html; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; LGRID=20180306103425-e2dc0ce5-20e6-11e8-b126-5254005c3644; LGUID=20180306103425-e2dc0d38-20e6-11e8-b126-5254005c3644; _putrc=F484526B4D3713E6; login=true; unick=%E4%BB%A3%E4%B9%90; showExpriedIndex=1; showExpriedCompanyHome=1; showExpriedMyPublish=1; hasDeliver=16; gate_login_token=4dce3d172c16730f1ac33586d817fc87a0111b0f126510f2");
            httpPost.setHeader("User-Agent",this.ua[r.nextInt(ua.length)]);
            //httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.90 Safari/537.36 2345Explorer/9.2.1.17116");
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = null;
            //httpPost.setURI(new URI("http://music.163.com/user/home?id="+i));
            httpPost.setURI(new URI("https://www.lagou.com/jobs/"+i+".html"));
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
                response = client.execute(httpPost);
                //response = httpClient.execute(httpPost);
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
            String job_name = doc.select("div.job-name").select("span.name").text();
            //String info = doc.select("dd.job_request").text();
            if("".equals(job_name)){
                //System.out.println(resultString);
            }else {
                String salary = doc.select("dd.job_request").select("span.salary").text();
                Element p = doc.select("dd.job_request").select("p").get(0).child(1);
                String dizhi = doc.select("dd.job_request").select("p").get(0).child(1).text().replace("/","").trim();
                String jingyan = doc.select("dd.job_request").select("p").get(0).child(2).text().replace("/","").trim();
                String xueli = doc.select("dd.job_request").select("p").get(0).child(3).text().replace("/","").trim();
                String xingzhi = doc.select("dd.job_request").select("p").get(0).child(4).text();
                String biaoqian = doc.select("dd.job_request").select("ul").text();
                String time = doc.select("dd.job_request").select("p.publish_time").text();
                String publish_time = doc.select("dd.job_request").select("p.publish_time").text().split(" ")[0].trim();
                String job_desc = doc.select("dd.job_bt").select("div").text();
                String job_youhuo = doc.select("dd.job-advantage").select("p").text();
                String commany = doc.select("dl.job_company").select("img").attr("alt");
                String commany_jianjie = doc.select("dl.job_company").select("h2.fl").text().split(" ")[0].trim();

                job.setJob_desc(job_desc);
                job.setJob_youhuo(job_youhuo);
                job.setCommany_jianjie(commany_jianjie);
                job.setCommany(commany);
                job.setDate(publish_time);
                job.setJob_name(job_name);
                job.setSalary(salary);
                job.setAddress(dizhi);
                job.setEducation(xueli);
                job.setExperience(jingyan);
                job.setId(i);
                job.setLabel(biaoqian);
                job.setJob_type(xingzhi);
                //System.out.println(job.toString()+job_desc);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(JsonUtils.objectToJson(job)+"\n");
                bw.flush();
            }
            //System.out.println(response.getStatusLine().getStatusCode()+":"+i);
            i++;
        }
        } catch (Exception e) {
                //e.printStackTrace();
                this.i++;
            new Thread(this).start();
            }
        }


    public static void main2(String args[]) throws IOException {

    }

   public static void main(String args[]) throws Exception {
       final String File_Path = Config.File_Path;
       LaGouJobRes a1 = new LaGouJobRes(File_Path+"/lagoujob1.log",1,250000);
       LaGouJobRes a2 = new LaGouJobRes(File_Path+"/lagoujob2.log",250000,500000);
       LaGouJobRes a3 = new LaGouJobRes(File_Path+"/lagoujob3.log",500000,750000);
       LaGouJobRes a4 = new LaGouJobRes(File_Path+"/lagoujob4.log",750000,1000000);
       LaGouJobRes a5 = new LaGouJobRes(File_Path+"/lagoujob5.log",1000000,1250000);
       LaGouJobRes a6 = new LaGouJobRes(File_Path+"/lagoujob6.log",1250000,1500000);
       LaGouJobRes a7 = new LaGouJobRes(File_Path+"/lagoujob7.log",1500000,1750000);
       LaGouJobRes a8 = new LaGouJobRes(File_Path+"/lagoujob8.log",1750000,2000000);
       LaGouJobRes a9 = new LaGouJobRes(File_Path+"/lagoujob9.log",2000000,2250000);
       LaGouJobRes a10 = new LaGouJobRes(File_Path+"/lagoujoba10.log",2250000,2500000);
       LaGouJobRes a11 = new LaGouJobRes(File_Path+"/lagoujoba11.log",2500000,2750000);
       LaGouJobRes a12 = new LaGouJobRes(File_Path+"/lagoujoba12.log",2750000,3000000);
       LaGouJobRes a13 = new LaGouJobRes(File_Path+"/lagoujoba13.log",3000000,3250000);
       LaGouJobRes a14 = new LaGouJobRes(File_Path+"/lagoujoba14.log",3250000,3500000);
       LaGouJobRes a15 = new LaGouJobRes(File_Path+"/lagoujoba15.log",3500000,3750000);
       LaGouJobRes a16 = new LaGouJobRes(File_Path+"/lagoujoba16.log",3750000,4000000);
       LaGouJobRes a17 = new LaGouJobRes(File_Path+"/lagoujoba17.log",4000000,4250000);
       LaGouJobRes a18 = new LaGouJobRes(File_Path+"/lagoujoba18.log",4250000,4500000);
       LaGouJobRes a19 = new LaGouJobRes(File_Path+"/lagoujoba19.log",4500000,4750000);
       LaGouJobRes a20 = new LaGouJobRes(File_Path+"/lagoujoba20.log",4750000,5000000);
       ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
       cachedThreadPool.execute(a1);
       cachedThreadPool.execute(a2);
       cachedThreadPool.execute(a3);
       cachedThreadPool.execute(a4);
       cachedThreadPool.execute(a5);
       cachedThreadPool.execute(a6);
       cachedThreadPool.execute(a7);
       cachedThreadPool.execute(a8);
       cachedThreadPool.execute(a9);
//       cachedThreadPool.execute(a10);
//       cachedThreadPool.execute(a11);
//       cachedThreadPool.execute(a12);
//       cachedThreadPool.execute(a13);
//       cachedThreadPool.execute(a14);
//       cachedThreadPool.execute(a15);
//       cachedThreadPool.execute(a16);
//       cachedThreadPool.execute(a17);
//       cachedThreadPool.execute(a18);
//       cachedThreadPool.execute(a19);
//       cachedThreadPool.execute(a20);
   }

    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            public void checkClientTrusted(
                    X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            public void checkServerTrusted(
                    X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }

    private static CloseableHttpClient buildSSLCloseableHttpClient() throws Exception {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            //信任所有
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();
        //ALLOW_ALL_HOSTNAME_VERIFIER:这个主机名验证器基本上是关闭主机名验证的,实现的是一个空操作，并且不会抛出javax.net.ssl.SSLException异常。
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }
}

