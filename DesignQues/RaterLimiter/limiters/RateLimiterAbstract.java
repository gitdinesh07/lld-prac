package DesignQues.RaterLimiter.limiters;

import DesignQues.RaterLimiter.model.RateLimiterConfig;
import DesignQues.RaterLimiter.model.UserReqModel;
import DesignQues.RaterLimiter.model.UserTier;
import java.util.Date;

public abstract class RateLimiterAbstract {
    

    private  int freeUserReq = 5;
    private  int paidUserReq = 10;
    private int reqWindowSeconds = 60;

    public RateLimiterAbstract( RateLimiterConfig config){
        if (config.freeUserMaxReq != 0){
            this.freeUserReq = config.freeUserMaxReq ;
        }
        if (config.paidUserMaxReq != 0){
            this.paidUserReq = config.paidUserMaxReq ;
        }
        if (config.windowInSeconds != 0){
            this.reqWindowSeconds = config.windowInSeconds;
        }
    }

    protected int allowedCountByUserType(UserTier userType){
        return userType == UserTier.FREE ? freeUserReq :paidUserReq;
    }

    protected long getNewReqWindow(){
        return  new Date().getTime()+(this.reqWindowSeconds*1000);
    }

    protected int getReqWindow(){return this.reqWindowSeconds;}

    public abstract boolean isAllowReq(UserReqModel model);
}
