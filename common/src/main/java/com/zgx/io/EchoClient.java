package com.zgx.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        Socket client = null;
        BufferedReader reader = null;
        PrintWriter writer = null;

        //创建客户端
        client = new Socket();
        //连接服务器'
        client.connect(new InetSocketAddress("localhost",8000));
        //通过流的形向服务器写入相应的内容(true:写入)
        writer = new PrintWriter(client.getOutputStream(), true);
        writer.print("Hello");
        writer.flush();

    }

}
