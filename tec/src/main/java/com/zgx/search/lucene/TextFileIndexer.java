package com.zgx.search.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/25.
 */
public class TextFileIndexer {
    public static void main(String[] args) throws Exception {
        //指定要索引文件对应文件夹的位置
        File fileDir = new File("D:\\tmp\\learn_search\\lucene\\example1");
        //指定索引文件存放位置
        File indexDir = new File("D\\tmp\\learn_search\\lucene\\example1\\index");
        File[] textFiles = fileDir.listFiles();
        //创建分析器类

        long startTime = new Date().getTime();
        //以document的形式为文本文件创建并存储索引
        for (File f : textFiles)
            if (f.isFile() && f.getName().endsWith(".txt")) {
                System.out.printf("File " + f.getCanonicalPath() + "正在被索引");
                String temp = FileReaderAll(f.getCanonicalPath(), "GBK");
                System.out.printf("temp==>" + temp);
                Document document = new Document();
                //分字段存储索引信息
                Field fieldPath = new Field("path", f.getPath(), Field.Store.YES, Field.Index.NO);
                Field fieldBody = new Field("body", temp, Field.Store.YES,Field.Index.ANALYZED_NO_NORMS,Field.TermVector.WITH_POSITIONS_OFFSETS);
                //将字段信息存放到索引文档中
                document.add(fieldPath);
                document.add(fieldBody);

            }
    }

    public static String FileReaderAll(String fileName, String charset) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charset));
        String line = new String();
        String temp = new String();
        while ((line = reader.readLine()) != null) {
            temp += line;
        }
        reader.close();
        return temp;
    }
}
