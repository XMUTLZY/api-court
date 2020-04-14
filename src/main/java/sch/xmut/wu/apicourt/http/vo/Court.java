package sch.xmut.wu.apicourt.http.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Created by wu on 2020/04/14
 */
public class Court implements Serializable {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("arena_id")
    private Integer arenaId;
    @JsonProperty("court_name")
    private String courtName;
    @JsonProperty("cover_image")
    private String coverImage;
    @JsonProperty("rent_work")
    private Double rentWork;
    @JsonProperty("rent_weekend")
    private Double rentWeekend;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("score")
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
