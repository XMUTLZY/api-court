package sch.xmut.wu.apicourt.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wu on 2020/04/14
 */
public class UserCollectRequest {
    @JsonProperty("arena_id")
    private Integer arenaId;

    public Integer getArenaId() {
        return arenaId;
    }

    public void setArenaId(Integer arenaId) {
        this.arenaId = arenaId;
    }
}
