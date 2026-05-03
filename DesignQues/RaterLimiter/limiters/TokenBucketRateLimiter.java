package DesignQues.RaterLimiter.limiters;

import DesignQues.RaterLimiter.model.RateLimiterConfig;
import DesignQues.RaterLimiter.model.UserReqModel;
import DesignQues.RaterLimiter.model.UserTier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenBucketRateLimiter extends RateLimiterAbstract{

    private final Map<String, Integer> userTokenBucket = new ConcurrentHashMap<String,Integer>() ;
    private final Map<String, Long> userLastTokenRefilled = new HashMap<>();

    public TokenBucketRateLimiter(RateLimiterConfig config) {
        super(config);
    }

    @Override
    public boolean isAllowReq(UserReqModel model) {
       
        long now = System.currentTimeMillis();
       
        //atomic bool bcz stream (userTokenBucket.compute) does support only final type
        AtomicBoolean allowed = new AtomicBoolean(false);

       userTokenBucket.compute(String.valueOf(model.getUserId()),(id,availableTokens)->{
        //refill first so that we can get updated token count of user
        int currTokenCount = refilledToken(model, now);
        System.out.println("user: "+model.getUserId()+" token exist:"+currTokenCount);
        if (currTokenCount > 0){
            allowed.set(true);
            return currTokenCount - 1;//reduce token for current req
        }else{
            return  currTokenCount;// no token left
        }
       });

       return allowed.get();
    }

    //examole 0 1   2   3   4   5   6   7   8   9   10  11  12
    //refill rate lets assume 6 request in 60 seconds so refill rate is := 60/6 = 10 => for each 10 seconds refill 1 token 
    private int refilledToken(UserReqModel model, long now ){

        String userStr = String.valueOf(model.getUserId());

        int refillRate = getRefilledRate(model.getUserType());
        long lastRefillAt = userLastTokenRefilled.getOrDefault(userStr, now);
        if (! userLastTokenRefilled.containsKey(userStr)){
            userLastTokenRefilled.put(userStr, now);
            return  this.allowedCountByUserType(model.getUserType());
        }

        long elapsedSeonds = (now - lastRefillAt)/1000;
        int refillTokenCount = (int) (elapsedSeonds / refillRate);

        int currentToken = userTokenBucket.getOrDefault(userStr, this.allowedCountByUserType(model.getUserType()));
        //recheck refill token can not be above from awlloed max request count
        currentToken = Math.min(this.allowedCountByUserType(model.getUserType()), (currentToken +refillTokenCount ));

        if (refillTokenCount > 0 ) userLastTokenRefilled.put(userStr, now);

        return currentToken;
    }

    private int getRefilledRate(UserTier userType){
        return this.getReqWindow()/this.allowedCountByUserType(userType);
    }
    
}
