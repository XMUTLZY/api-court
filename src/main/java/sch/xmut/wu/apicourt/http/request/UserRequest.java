package sch.xmut.wu.apicourt.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wu on 2020/04/13
 */
public class UserRequest {
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("wechat_number")
    private String wechatNumber;
    private String portrait;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
