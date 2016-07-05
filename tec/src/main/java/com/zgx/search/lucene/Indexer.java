package com.zgx.search.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * 索引操作类
 * Created by Administrator on 2016/6/25.
 */
public class Indexer {
    public IndexWriter writer;

    public Indexer(String indexDir) throws IOException {

        //创建分词器
        Analyzer analyzer=new StandardAnalyzer();//分词器
        //创建写索引配置(依赖分词器)
        IndexWriterConfig writerConfig = new IndexWriterConfig(analyzer);
        //索引存储位置
        Directory directory  =  FSDirectory.open(Paths.get(indexDir));
        //根据索引存储位置与索引位置
        writer = new IndexWriter(directory,writerConfig);
    }

    /**
     * 关闭写索引
     * @throws Exception
     */
    public void close() throws Exception{
        writer.close();
   }

    /**
     * 添加指定目錄的所有文件的索引
     * @param  dataDir
     * @return
     * @throws Exception
     */
    public int index(String dataDir)throws Exception{
        //得到指定目录的文档数组
        File[] files = new File(dataDir).listFiles();
        for (File file:files) {
            if(file.isFile()&&file.getName().endsWith(".txt")){
                indexFile(file);
            }
        }
        return writer.numDocs();
    }

    public void indexFile(File file)throws Exception{
        //打印索引的文件路径信息
        System.out.printf("索引文件："+file.getCanonicalPath());
        Document document  = getDocument(file);
        new ArrayList<Object>();

        writer.addDocument(document);
    }

    /**
     * 返回一个文档记录
     * @param file
     * @return
     * @throws Exception
     */
    public Document getDocument(File file)throws Exception{
        Document document = new Document();
        //添加一个文档信息，相当于一个数据库表字段
        document.add(new TextField("context",new FileReader(file)));
        //添加文档的名字属性
        document.add(new TextField("fileName",file.getName(),Field.Store.YES) );
        //添加文档的路径属性
        document.add(new TextField("filePath",file.getCanonicalPath(),Field.Store.YES));
        return document;

    }


    public static void main(String[] args) throws Exception {
        String dataDir = "D:\\tmp\\learn_search\\lucene\\example1";
        String indexDir = "D:\\tmp\\learn_search\\lucene\\example1\\index";
        Indexer indexer = null;
        int indexSum  = 0;
        try {
            indexer = new Indexer(indexDir);
            indexSum = indexer.index(dataDir);
            System.out.printf("完成 "+indexSum+"个文件的索引");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                indexer.close();
            }catch (Exception e){
                System.out.printf("e==>"+e);
            }
        }
    }

}

