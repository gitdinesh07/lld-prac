package DesignQues.RaterLimiter;

import DesignQues.RaterLimiter.limiters.FixedWindowRateLimiter;
import DesignQues.RaterLimiter.limiters.RateLimiterAbstract;
import DesignQues.RaterLimiter.limiters.SlidingWindowLogRateLimiter;
import DesignQues.RaterLimiter.limiters.TokenBucketRateLimiter;
import DesignQues.RaterLimiter.model.RateLimiterConfig;
import DesignQues.RaterLimiter.model.RateLimiterType;

public class RateLimiterFactory {
    
    public static RateLimiterAbstract createRateLimiter(RateLimiterType limiterType, RateLimiterConfig config){
        switch (limiterType) {
            case RateLimiterType.FIXED_WINDOW:
                return new FixedWindowRateLimiter(config);
            case RateLimiterType.TOKEN_BUCKET:
                return new TokenBucketRateLimiter(config);
            case RateLimiterType.SLIDING_WINDOW_LOG:
                return new SlidingWindowLogRateLimiter(config);
            default:
                throw new AssertionError();
        }
    }
}
