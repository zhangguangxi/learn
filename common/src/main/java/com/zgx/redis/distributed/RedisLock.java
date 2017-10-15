package com.zgx.redis.distributed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.swing.text.StyledEditorKit;


public class RedisLock {
    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private RedisTemplate redisTemplate;

    private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLS =100;

    /**
     * Lock key path
     */
    private String lockKey;

    /**
     * 锁超时时间,防止线程在入锁后，无限的执行等待
     */
    private int expireMesesc = 60 * 1000;


    /**
     * 锁等待时间
     */
    private int timeoutMsesc = 10 * 1000;



    private volatile  boolean locked = false;

    public RedisLock(RedisTemplate redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey+"_lock";
    }


    public RedisLock(RedisTemplate redisTemplate, String lockKey, int timeoutMsesc) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.timeoutMsesc = timeoutMsesc;
    }


    public RedisLock(RedisTemplate redisTemplate, String lockKey, int expireMesesc, int timeoutMsesc) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.expireMesesc = expireMesesc;
        this.timeoutMsesc = timeoutMsesc;
    }

    public String getLockKey(){
        return lockKey;
    }

    private String get(final String key){
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
               @Override
               public Object doInRedis(RedisConnection redisConnection) {
                   StringRedisSerializer serializer = new StringRedisSerializer();
                   byte[] data = redisConnection.get(serializer.serialize(key));
                   redisConnection.close();
                   if (data == null) {
                       return null;
                   }
                   return serializer.deserialize(data);
               }
           });
        } catch (DataAccessException e) {
            e.printStackTrace();
            logger.error("get redis error,key :{}",key);
        }
        return obj !=null?obj.toString():null;
    }


    private boolean setNX(final String key,final String value){
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback() {
               @Override
               public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                   StringRedisSerializer serializer = new StringRedisSerializer();
                   Boolean sucess = redisConnection.setNX(serializer.serialize(key), serializer.serialize(value));
                   redisConnection.close();
                   return sucess;
               }
           });
        } catch (DataAccessException e) {
            e.printStackTrace();
            logger.error("setNx redis error,key:{}",key);
        }
        return obj !=null?(Boolean)obj:false;
    }

    private String getSet(final String key,final String value){
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection redisConnection) {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] ret = redisConnection.getSet(serializer.serialize(key), serializer.serialize(value));;
                    redisConnection.close();
                    return serializer.deserialize(ret);
                }
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
            logger.error("get redis error,key :{}",key);
        }
        return obj !=null?(String)obj:null;
    }


    /**
     * 获得lock
     * 实现思路:主要是使用了redis的setnx命令，缓存了锁
     * redis缓存的key是锁的key，全局共享，value是锁的到期时间(注意:这里把国旗时间放在value上了，没有时间上设置超时时间)
     * 执行过程：
     * 1、通过setnx尝试设置某个key的值，成功(当前没有这个锁)则返回，成功获得锁
     * 2、锁已经存在则获取锁的到期时间和当前时间进行比较，超时的话，则设置新的值
     */


}
