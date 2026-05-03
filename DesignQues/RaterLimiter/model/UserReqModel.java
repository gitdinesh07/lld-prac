package DesignQues.RaterLimiter.model;

public class UserReqModel {
    UserTier userType;
    int UserId;
    long lastRequestAt;
    long reqWindowTill;
    public long lastValidRequestAt;
    int reqeustCount;

    public UserReqModel(int userId, UserTier userType){
        this.UserId = userId;
        this.userType = userType;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public UserTier getUserType() {
        return userType;
    }

    public void setUserType(UserTier userType) {
        this.userType = userType;
    }

    public int getReqeustCount() {
        return reqeustCount;
    }

    public void setReqeustCount(int reqeustCount) {
        this.reqeustCount = reqeustCount;
    }

    public void incReqeustCount() {
        this.reqeustCount ++;
    }

    public long getLastRequestAt() {
        return lastRequestAt;
    }

    public void setLastRequestAt(long lastRequestAt) {
        this.lastRequestAt = lastRequestAt;
    }

    public long getReqWindowTill() {
        return reqWindowTill;
    }

    public void setReqWindowTill(long reqWindowTill) {
        this.reqWindowTill = reqWindowTill;
    }

}
