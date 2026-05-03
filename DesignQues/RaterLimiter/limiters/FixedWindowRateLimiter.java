package DesignQues.RaterLimiter.limiters;

import DesignQues.RaterLimiter.model.RateLimiterConfig;
import DesignQues.RaterLimiter.model.UserReqModel;
import java.util.Date;

public class FixedWindowRateLimiter extends RateLimiterAbstract {

    public FixedWindowRateLimiter(RateLimiterConfig config){
        super(config);
    }
    
    @Override
    public boolean isAllowReq(UserReqModel model) {
        long currRequest = new Date().getTime();
        model.setLastRequestAt(currRequest);
        boolean isAllowed = (model.getReqeustCount() == 0 && this.allowedCountByUserType(model.getUserType())>0); //if first request then allowed

        if(!isAllowed){
            isAllowed = (model.getReqWindowTill() > currRequest) && model.getReqeustCount() < this.allowedCountByUserType(model.getUserType());
        }

        //reset if window expire
        if(!isAllowed && model.getReqWindowTill() < currRequest){
            model.setReqWindowTill(0);
            model.setReqeustCount(0);
            isAllowed = true;
        }



        if (isAllowed){
            model.incReqeustCount();
            model.lastValidRequestAt = currRequest;
            if (model.getReqWindowTill() == 0){
                model.setReqWindowTill( this.getNewReqWindow());
            }
            isAllowed = true;
        }

        return isAllowed;
    }
    
}
