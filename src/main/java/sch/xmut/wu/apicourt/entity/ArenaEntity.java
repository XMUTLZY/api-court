package sch.xmut.wu.apicourt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wu on 2020/04/13
 */
@Table(name = "arena")
@Entity
public class ArenaEntity implements Serializable {
    public static final Integer STATUS_REST = 0;        //休息
    public static final Integer STATUS_BUSY = 1;        //营业
    public static final Integer STATUS_DELETE = 2;      //倒闭(删除)
    public static final Integer RECOMMEND_NO = 0;       //不推荐
    public static final Integer RECOMMEND_YES = 1;      //推荐

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "boss_mobile")
    private String bossMobile;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "cover_image")
    private String coverImage;
    @Column(name = "status")
    private Integer status = STATUS_BUSY;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "is_recommend")
    private Integer isRecommend = RECOMMEND_NO;

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
}
