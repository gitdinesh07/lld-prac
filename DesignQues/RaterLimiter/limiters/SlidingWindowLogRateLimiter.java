package DesignQues.RaterLimiter.limiters;

import DesignQues.RaterLimiter.model.*;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;


public class SlidingWindowLogRateLimiter extends RateLimiterAbstract {

   private final Map<Integer, Queue<Long>> rateLimiterLog = new ConcurrentHashMap<>();

    public SlidingWindowLogRateLimiter(RateLimiterConfig config) {
        super(config);
    }
    
    @Override
    public boolean isAllowReq(UserReqModel model) {

        AtomicBoolean allowed = new AtomicBoolean(false);
        var now = System.currentTimeMillis();

        rateLimiterLog.compute(model.getUserId(), (id,currUserLog)->{
            if (currUserLog == null) currUserLog = new ArrayDeque<>();
            
                if (currUserLog.size() >= this.allowedCountByUserType(model.getUserType())){
                    if ((now - currUserLog.peek())/1000 > this.getReqWindow()){
                        currUserLog.poll();
                    }
                }

                if (currUserLog.isEmpty() || (currUserLog.size() < this.allowedCountByUserType(model.getUserType()))){
                    currUserLog.add(now);
                    allowed.set(true);
                }
                return currUserLog;
            });

        return  allowed.get();
    }
}
