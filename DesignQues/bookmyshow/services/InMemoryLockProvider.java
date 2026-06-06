package DesignQues.bookmyshow.services;

import DesignQues.bookmyshow.interfaces.LockProvider;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InMemoryLockProvider implements LockProvider {

    private final Map<String, Expiry> lock = new ConcurrentHashMap<>();
    private final ScheduledExecutorService  sweeper = Executors.newSingleThreadScheduledExecutor();

    public InMemoryLockProvider() {
        sweeper.scheduleAtFixedRate(this::Sweep, 1, 1, TimeUnit.MINUTES);
    }

    private void Sweep(){
        long now = System.currentTimeMillis();
        lock.entrySet().removeIf(e -> e.getValue().deadline <= now);
    }

    @Override
    public boolean TryLock(String key,String userId, int ttlMs) {
       var now = System.currentTimeMillis();
        var expiry = new Expiry(userId, now+ttlMs);
        return this.lock.compute(key, (k,v)-> (v == null || v.deadline <= now ) ? expiry : v ) == expiry;
    }

    @Override
    public void Unlock(String key) {
        lock.remove(key);
    }

    @Override
    public boolean IsLockExpired(String key) {
        var expiry = lock.get(key);
        return expiry != null && expiry.deadline < System.currentTimeMillis();
    }

    @Override
    public boolean IsLockedBy(String key, String userId) {
        var expiry = lock.get(key);
        return expiry != null && expiry.owner.equals(userId);
    }


    private static class Expiry{
        final long deadline;
        final String owner;

        Expiry(String user, long deadline ) {
            this.deadline = deadline;
            this.owner = user;
        }
        
    }
   
}
