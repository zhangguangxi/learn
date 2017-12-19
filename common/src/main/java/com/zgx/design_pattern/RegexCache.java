/*
package com.zgx.design_pattern;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public abstract class RegexCache {
    */
/*
    LoadingCache<Key, Graph> graphs = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .removalListener(MY_LISTENER)
        .build(
            new CacheLoader<Key, Graph>() {
                public Graph load(Key key) throws AnyException {
                    return createExpensiveGraph(key);
                }
        });
     *//*

    static Cache<Object, Object> cache = CacheBuilder.newBuilder().build();
    static Cache<Object, Object> expireCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();
    static Cache<Object, Object> sizeCache = CacheBuilder.newBuilder().build();

    public static Pattern getPattern(final String regex) {
        try {
            return (Pattern) cache.get(regex, new Callable<Pattern>() {
                @Override
                public Pattern call() throws Exception {
                    return Pattern.compile(regex);
                }
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map getUser(Long id) {
        try {
            return (Map) expireCache.get(id, new Callable<Map>() {
                @Override
                public Map call() throws Exception {
                    System.out.println("++++" + id + "++++");
                    HashMap<Object, Object> map = new HashMap<>();
                    map.put("id", id);
                    return map;
                }
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //        String input = "sdf,sdfxx?lkjlkj;s-df";
*/
/*        String input = "sdf,sdfxx?lkjlkj;s-df";
        Pattern pattern = getPattern("*.,(;)?.*");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()){
            System.out.println(matcher.group());
        }*//*


//        String result = matcher.replaceAll("");
//        System.out.println(result);
    }

}
*/
