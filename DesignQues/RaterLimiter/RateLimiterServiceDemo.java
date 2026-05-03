package DesignQues.RaterLimiter;

import DesignQues.RaterLimiter.model.RateLimiterType;

public class RateLimiterServiceDemo {
    
    public static void main(String[] args) throws Throwable{
    //    RateLimiterService.RunRateLimiterService(RateLimiterType.FIXED_WINDOW);
    //    RateLimiterService.RunRateLimiterService(RateLimiterType.TOKEN_BUCKET);
       RateLimiterService.RunRateLimiterService(RateLimiterType.SLIDING_WINDOW_LOG);
    }
}
