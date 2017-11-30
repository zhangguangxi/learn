package com.zgx.redis.distributed;

/**
 * 获取锁之后需要处理的逻辑
 * @param <T>
 */
public interface AquireLockWorker<T> {
        T invokeAfterLockAquire() throws Exception;
}
