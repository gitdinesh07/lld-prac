package DesignQues.bookmyshow.interfaces;


public interface LockProvider{

    public abstract boolean TryLock(String key,String userId, int ttlSeconds);

    public abstract void Unlock(String key);

    public abstract boolean  IsLockExpired(String key);

    public abstract boolean IsLockedBy(String key, String userId);
}