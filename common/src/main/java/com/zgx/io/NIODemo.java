package com.zgx.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.Executors;

public class NIODemo {

    public static void main(String[] args) throws Exception {
//        nioCopyFile("d://a.txt","d://b.txt");
        bufferInfo();
    }

    public static  void nioCopyFile(String resource,String destination) throws Exception{
        //创建文件输入流
        FileInputStream fileInputStream = new FileInputStream(resource);
        //创建文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream(destination);
        //读文件通道
        FileChannel readChannel = fileInputStream.getChannel();
        //写文件痛的
        FileChannel writeChannel = fileOutputStream.getChannel();
        //读入缓存数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(true){
            buffer.clear();
            int len = readChannel.read(buffer);
            if(len == -1){
                break;
            }
            buffer.flip();
            writeChannel.write(buffer);
        }
        //关闭缓存通道
        readChannel.close();
        writeChannel.close();
    }

    /**
     *  位置(position) 容量(capacity)和上限(limit)
     *
     *  参数                          写模式                                                 度模式
     *  位置(position)          当前缓冲区位置，将从position的下一位置开始写数据      当前缓冲区的读取位置，将从此位置后，读取数据
     *  容量(position)          缓冲区的总容量上限                               缓冲区的总容量上限
     *  上限(limit)           缓冲区的实际上限，它总是小鱼等于容量，通常等于容量大小    代表可读取的总容量，和上次写入的数据量相等。
     *
     */
    public static void bufferInfo(){
        ByteBuffer buffer = ByteBuffer.allocate(15);
        System.out.println("limit="+buffer.limit()+" capacity="+buffer.capacity()+" position="+ buffer.position());
        for (int i = 0; i < 10; i++) {
             buffer.put((byte)i);
        }
        System.out.println("limit="+buffer.limit()+" capacity="+buffer.capacity()+" position="+ buffer.position());
        //重置position  重置之后positon的位置变成0，同时将limit设置为当前位置
        buffer.flip();
        System.out.println("limit="+buffer.limit()+" capacity="+buffer.capacity()+" position="+ buffer.position());
        for (int i = 0; i < 5; i++) {
            System.out.println(buffer.get());
        }
        System.out.println();
        System.out.println("limit="+buffer.limit()+" capacity="+buffer.capacity()+" position="+ buffer.position());
        buffer.flip();
        System.out.println("limit="+buffer.limit()+" capacity="+buffer.capacity()+" position="+ buffer.position());

    }
}
