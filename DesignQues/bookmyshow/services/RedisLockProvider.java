package DesignQues.bookmyshow.services;

import DesignQues.bookmyshow.interfaces.LockProvider;

public class RedisLockProvider implements LockProvider{
    @Override
    public boolean TryLock(String key,String userId, int ttlSeconds) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Unlock(String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean IsLockExpired(String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean IsLockedBy(String key, String userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
}
