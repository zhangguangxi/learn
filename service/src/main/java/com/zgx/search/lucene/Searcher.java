package com.zgx.search.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;

/**
 * 索引查询
 * Created by Administrator on 2016/6/25.
 */
public class Searcher {
    /**
     *
     * @param indexDir
     * @param query
     * @throws Exception
     */
    public static void search(String indexDir,String query) throws Exception{
        //获取索引地址
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        //读索引
        IndexReader indexReader = DirectoryReader.open(dir);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //创建标准分词器
        Analyzer analyzer = new StandardAnalyzer();
        //指定查询Document的某个属性
        QueryParser queryParser = new QueryParser("context",analyzer);
        //指定查询某个索引内容，对应某个分词
        Query q = queryParser.parse(query);
        //执行搜索
        TopDocs hits =  indexSearcher.search(q,10);
        System.out.printf("匹配"+query+"查询到"+hits.totalHits+"个记录");
        //打印Document的fileName属性
        for (ScoreDoc scoreDoc: hits.scoreDocs) {
            Document doc = indexSearcher.doc(scoreDoc.doc);
            System.out.printf(doc.get("fileName"));
        }
        indexReader.close();
    }

    public static void main(String[] args) {
        String indexDir = "D:\\tmp\\learn_search\\lucene\\example1\\index";
        String query = "人生";
        try {
            search(indexDir,query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
