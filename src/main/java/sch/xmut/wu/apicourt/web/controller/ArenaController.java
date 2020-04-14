package sch.xmut.wu.apicourt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sch.xmut.wu.apicourt.http.request.ArenaRequest;
import sch.xmut.wu.apicourt.http.response.ArenaResponse;
import sch.xmut.wu.apicourt.service.ArenaService;

/**
 * Created by wu on 2020/04/13
 */
@Controller
@RequestMapping(value = "/arena")
public class ArenaController {
    @Autowired
    private ArenaService arenaService;

    //球馆列表 request: list_type
    @PostMapping("/list")
    @ResponseBody
    public ArenaResponse list(@RequestBody ArenaRequest request) {
        return arenaService.list(request);
    }

    //球馆详情 request: arena_id
    @PostMapping("/detail")
    @ResponseBody
    public ArenaResponse detail(@RequestBody ArenaRequest request) {
        return arenaService.detail(request);
    }

    //球馆列表查询 request: arena_name
    @PostMapping("/search")
    @ResponseBody
    public ArenaResponse search(@RequestBody ArenaRequest request) {
        return arenaService.search(request);
    }
}
