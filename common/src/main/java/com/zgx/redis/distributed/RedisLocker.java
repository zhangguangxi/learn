package com.zgx.redis.distributed;

public class RedisLocker implements  DistributeLocker{
    private final  static String  LOCK_PREFIX="lock:";

    @Override
    public <T> T lock(String resourceName, AquireLockWorker<T> worker) throws UnableToAquireLockException, Exception {
        return null;
    }

    @Override
    public <T> T lock(String resourceName, AquireLockWorker<T> worker, int lockTime) throws UnableToAquireLockException, Exception {
        return null;
    }
}
