package com.zgx.redis.distributed;

/**
 * 获取锁管理
 */
public interface DistributeLocker {
    /**
     *  获取所
     * @param resourceName  锁的名称
     * @param worker        获取所有的处理类
     * @param <T>
     * @return              处理完具体业务逻辑需要返回的数据
     * @throws Exception
     */
    <T> T lock(String resourceName,AquireLockWorker<T> worker) throws UnableToAquireLockException,  Exception;

    /**
     * 获取锁
     * @param resourceName 锁的名称
     * @param worker       获取所有的处理类
     * @param lockTime     锁时间
     * @param <T>           处理完具体业务逻辑需要返回的数据
     * @return
     * @throws Exception
     */
    <T> T lock(String resourceName,AquireLockWorker<T> worker,int lockTime) throws UnableToAquireLockException,  Exception;
}

