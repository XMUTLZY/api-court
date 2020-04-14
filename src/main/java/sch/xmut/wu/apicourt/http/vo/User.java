package sch.xmut.wu.apicourt.http.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 * Created by wu on 2020/04/13
 */
public class User {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("wechat_number")
    private String wechatNumber;
    @JsonProperty("portrait")
    private String portrait;
    @JsonProperty("create_time")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
