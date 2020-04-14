package sch.xmut.wu.apicourt.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import sch.xmut.wu.apicourt.http.vo.Arena;
import java.util.List;

/**
 * Created by wu on 2020/04/14
 */
public class UserCollectResponse extends BaseResponse {
    @JsonProperty("arena_list")
    private List<Arena> arenaList;

    public List<Arena> getArenaList() {
        return arenaList;
    }

    public void setArenaList(List<Arena> arenaList) {
        this.arenaList = arenaList;
    }
}
