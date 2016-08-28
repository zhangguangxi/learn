package com.zgx.search.solr;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;

/**
 * Created by Administrator on 2016/7/27.
 */
public class SolrJOpt {
    public static void main(String[] args) throws Exception {
         //添加数据到solr服务器
//         addData();

        //从solr服务器查询数据
        queryData();
    }

    public static void addData() throws Exception{
        String solrServer  = "http://192.168.1.108:8888/solr";
        HttpSolrServer server = new HttpSolrServer(solrServer);
        SolrInputDocument document = null;
        for (int i = 0; i <1000 ; i++) {
            document = new SolrInputDocument();
            document.addField("name","zhangsan");
            document.addField("id",i);
            server.add(document);
            if(i %100 == 0){
                server.commit();
            }
        }

    }

    public static void queryData()throws Exception{
        String solrServer  = "http://192.168.1.108:8888/solr";
        HttpSolrServer server = new HttpSolrServer(solrServer);
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set("q","name:zhangsan");
//        params.set("defType","electronics");
        params.set("start",0);
        QueryResponse response = server.query(params);
        SolrDocumentList results = response.getResults();
        for (int i = 0; i <results.size() ; i++) {
            System.out.printf(results.get(i)+"");
        }
    }
}
