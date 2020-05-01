package sch.xmut.wu.apicourt.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wu on 2020/04/13
 */
public class ArenaRequest {
    @JsonProperty("arena_id")
    private Integer arenaId;
    @JsonProperty("list_type")
    private Integer listType;   //球馆列表 0:今日推荐 1：综合 2：评分 3：价格
    @JsonProperty("arena_name")
    private String arenaName;
    @JsonProperty("cover_image")
    private String coverImage;
    @JsonProperty("boss_mobile")
    private String bossMobile;
    private String address;

    public Integer getArenaId() {
        return arenaId;
    }

    public void setArenaId(Integer arenaId) {
        this.arenaId = arenaId;
    }

    public Integer getListType() {
        return listType;
    }

    public void setListType(Integer listType) {
        this.listType = listType;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getBossMobile() {
        return bossMobile;
    }

    public void setBossMobile(String bossMobile) {
        this.bossMobile = bossMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
