package com.zgx.search.lucene;

import org.apache.lucene.analysis.Analyzer;

/**
 * Created by Administrator on 2016/7/19.
 */
public class AnalyzerTest {
      //测试数据
      public static String testData="中文分词(Chinese Word Segmentation) 指的是将一个汉字序列切分成一" +
              "一个单独的词。分词就是将连续的字序列按照一定的规范重新组合成词序列的过程。";

      public static Analyzer getIKAnalyzer(){
          return new IKAnalyzer();
      }
}
