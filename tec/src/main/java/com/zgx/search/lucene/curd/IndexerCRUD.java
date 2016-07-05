package com.zgx.search.lucene.curd;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2016/7/7.
 */
public class IndexerCRUD {
    //创建模拟数据
    private static String[] ids = {"1","2","3"};
    private static String[] names={"zs","ls","ww"};
    private static String[] descriptions={"good","good","study"};
    //索引存储路径
    private static String dirPath = "D:\\tmp\\learn_search\\lucene\\curd";

    //获取写索引器
    public static IndexWriter getIndexWriter(String dirPath){
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(getDefaultAnalyzer());
        IndexWriter indexWriter=null;
        try {
             indexWriter = new IndexWriter(getDirectory(dirPath), indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }


    //获取读索引器
    public static IndexReader getIndexReader(){
        IndexReader indexreader = null;
        try {
            indexreader= DirectoryReader.open(getDirectory(dirPath));
            System.out.printf("当前存储的文档数:"+indexreader.numDocs());
            System.out.printf("当前存储的文档数，包含回收站的文档:"+indexreader.maxDoc());
            System.out.printf("回收站的文档数:"+indexreader.numDeletedDocs());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexreader;
    }

    //获取默认分词器

    public static Analyzer getDefaultAnalyzer(){
        return new StandardAnalyzer();
    }

    //获取索引磁盘存储器
    public static Directory getDirectory(String dirPath){
        Directory directory = null;
        try {
            directory= FSDirectory.open(Paths.get(dirPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directory;
                
    }

    //创建索引
    public static void createIndex(){
        IndexWriter indexWriter = getIndexWriter(dirPath);
        Document document = new Document();
        for (int i = 0 ;i<ids.length;i++){
            document.add(new StringField("ids",ids[i], Field.Store.YES));
            document.add(new StringField("ids",ids[i], Field.Store.YES));
            document.add(new StringField("ids",ids[i], Field.Store.YES));
            try {
                indexWriter.addDocument(document);
                indexWriter.close();
                getIndexReader();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //删除索引
    public static void deleteIndex(){
        
    }

}