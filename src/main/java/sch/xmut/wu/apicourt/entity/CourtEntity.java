package sch.xmut.wu.apicourt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by wu on 2020/04/13
 */
@Table(name = "court")
@Entity
public class CourtEntity implements Serializable {
    public static final Integer STATUS_UNBOOK = 0;      //不可预约
    public static final Integer STATUS_BOOK = 1;        //可预约
    public static final Integer STATUS_DELETE = 2;      //已删除（封闭）

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "arena_id")
    private Integer arenaId;
    @Column(name = "court_name")
    private String courtName;
    @Column(name = "cover_image")
    private String coverImage;
    @Column(name = "rent_work")
    private Double rentWork;
    @Column(name = "rent_weekend")
    private Double rentWeekend;
    @Column(name = "status")
    private Integer status = STATUS_BOOK;
    @Column(name = "score")
    private Double score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArenaId() {
        return arenaId;
    }

    public void setArenaId(Integer arenaId) {
        this.arenaId = arenaId;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Double getRentWork() {
        return rentWork;
    }

    public void setRentWork(Double rentWork) {
        this.rentWork = rentWork;
    }

    public Double getRentWeekend() {
        return rentWeekend;
    }

    public void setRentWeekend(Double rentWeekend) {
        this.rentWeekend = rentWeekend;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
