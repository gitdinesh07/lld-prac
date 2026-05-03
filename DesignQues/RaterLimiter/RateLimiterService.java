package DesignQues.RaterLimiter;

import DesignQues.RaterLimiter.limiters.RateLimiterAbstract;
import DesignQues.RaterLimiter.model.RateLimiterConfig;
import DesignQues.RaterLimiter.model.RateLimiterType;
import DesignQues.RaterLimiter.model.UserReqModel;
import DesignQues.RaterLimiter.model.UserTier;

public class RateLimiterService {
    
    public static void RunRateLimiterService(RateLimiterType limiterType) throws Throwable {
         
        UserReqModel freeUser = new UserReqModel(1, UserTier.FREE);
        UserReqModel paidUser = new UserReqModel(2, UserTier.PAID);

        var config = new RateLimiterConfig();
        config.freeUserMaxReq = 2;
        config.windowInSeconds = 6;
        RateLimiterAbstract rls =  RateLimiterFactory.createRateLimiter(limiterType, config);

        for(int i=1;i<20;i++){

            System.out.println("freeUser count:"+i+ " ALLOWED:"+rls.isAllowReq(freeUser));
            // System.out.println("paid count:"+i+ " ALLOWED:"+rls.isAllowReq(paidUser));

            // Thread.sleep(2000);
            if (i== 15 || i== 17 || i== 12 || i== 10  || i== 9){
                Thread.sleep(2000);
            }
        }
    }
}
