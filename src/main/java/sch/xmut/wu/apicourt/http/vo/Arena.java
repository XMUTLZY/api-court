package sch.xmut.wu.apicourt.http.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wu on 2020/04/13
 */
public class Arena implements Serializable {
    private Integer id;
    @JsonProperty("boss_mobile")
    private String bossMobile;
    private String name;
    private String address;
    @JsonProperty("cover_image")
    private String coverImage;
    private Integer status;
    @JsonProperty("start_time")
    private Date startTime;
    @JsonProperty("end_time")
    private Date endTime;
    @JsonProperty("is_recommend")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer isRecommend;
    @JsonProperty("total_score")    //综合
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double totalScore;
    @JsonProperty("single_score")    //评分
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double singleScore;
    @JsonProperty("price")    //价格
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double price;
    @JsonProperty("status_str")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String statusStr;
    @JsonProperty("recommend_str")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String recommendStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBossMobile() {
        return bossMobile;
    }

    public void setBossMobile(String bossMobile) {
        this.bossMobile = bossMobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(Double singleScore) {
        this.singleScore = singleScore;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getRecommendStr() {
        return recommendStr;
    }

    public void setRecommendStr(String recommendStr) {
        this.recommendStr = recommendStr;
    }
}
