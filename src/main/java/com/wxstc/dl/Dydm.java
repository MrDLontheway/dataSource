package com.wxstc.dl;

//import com.gexin.rp.sdk.base.uitls.MD5Util;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.UUID;

public class Dydm extends Thread{
    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//获取当前时间戳
    String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();//构造uuid作为devid参数
    //String vk = MD5Util.MD5(timestamp + "7oE9nPEG9xXV69phU31FYCLUagKeYtsF" + uuid);//vk参数
    public Dydm() throws Exception {
    }

    public static void main(String args[]) throws Exception {
        //发送登陆请求
        //MessageHandler.send(socket, Request.gid(rid, uuid, timestamp, vk));
        //等待接收
        //MessageHandler.receive(socket, loginListener);
        Socket socket = new Socket("openbarrage.douyutv.com", 8601);//Socket连接服务器
        Dydm d = new Dydm();
        Thread t = new Thread(d);
        t.run();
        start(9293,socket);
    }
    static void sendmsg(String msgstr, PrintWriter pw) throws Exception {
        String msg = msgstr;
        int data_length = msg.length() + 8;
        int code = 689;
        pw.write(msg);
        pw.flush();
    }

     static void start(int roomid, Socket socket) throws Exception {
         String msg = "type@=loginreq/username@=rieuse/password@=douyu/roomid@={}/\u0000".format(String.valueOf(roomid));
         OutputStream os=socket.getOutputStream();//字节输出流
         PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
         sendmsg(msg,pw);
         String msg_more = "type@=joingroup/rid@={}/gid@=-9999/\u0000".format(String.valueOf(roomid));
         sendmsg(msg_more,pw);

         System.out.println("---------------欢迎连接到{"+roomid+"}的直播间---------------");
         while (true){
             InputStream is=socket.getInputStream();
             BufferedReader br=new BufferedReader(new InputStreamReader(is));
             String info=null;
             while((info=br.readLine())!=null){
                 System.out.println(info);
             }
         }
    }

    public void run(Socket socket) {
        while(true){
            try {
                OutputStream os=socket.getOutputStream();//字节输出流
                PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
                String msg = "type@=keeplive/tick@=" + new Date().getTime() +"/\u0000";
                sendmsg(msg,pw);
                Thread.currentThread().sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
