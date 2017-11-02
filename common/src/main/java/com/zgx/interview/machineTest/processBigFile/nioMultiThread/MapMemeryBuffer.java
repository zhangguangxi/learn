package com.zgx.interview.machineTest.processBigFile.nioMultiThread;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MapMemeryBuffer {
    public static void main(String[] args) throws  Exception{
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 14 * 1024);
        byte[] bytes = new byte[14 * 1024 * 1024];
        FileInputStream fis = new FileInputStream("C:\\Users\\Dell\\Desktop\\test1.txt");
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Dell\\Desktop\\test2.txt");
        FileChannel fc = fis.getChannel();
        long startTime = System.currentTimeMillis();
        //fc.read(byteBuffer);
        MappedByteBuffer map = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        System.out.println(fc.size()/1024);
        long endTime = System.currentTimeMillis();
        System.out.println("Read time:"+(endTime - startTime)+"ms");
        startTime = System.currentTimeMillis();

       // fos.write(bytes);
        map.flip();
        endTime = System.currentTimeMillis();
        System.out.println("Write time:"+(endTime -  startTime)+"ms");
        fos.flush();
        fc.close();
        fis.close();
    }

}
