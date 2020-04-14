package sch.xmut.wu.apicourt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sch.xmut.wu.apicourt.http.request.UserBookRequest;
import sch.xmut.wu.apicourt.http.request.UserCollectRequest;
import sch.xmut.wu.apicourt.http.request.UserRequest;
import sch.xmut.wu.apicourt.http.response.BaseResponse;
import sch.xmut.wu.apicourt.http.response.UserCollectResponse;
import sch.xmut.wu.apicourt.service.UserService;

/**
 * Created by wu on 2020/04/13
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    //用户微信登录时，保存用户信息到数据库中 request：user_name、wechat_number、portrait
    @PostMapping(value = "/save/user-info")
    @ResponseBody
    public BaseResponse saveUserInfo(@RequestBody UserRequest request) {
        return userService.saveUserInfo(request);
    }

    //预定球场 request：court_id、book_time、book_long、money
    @PostMapping(value = "/book-court")
    @ResponseBody
    public BaseResponse book(@RequestBody UserBookRequest request) {
        return userService.book(request);
    }

    //收藏球馆 request：arena_id
    @PostMapping(value = "/collect-arena")
    @ResponseBody
    public BaseResponse collectArena(@RequestBody UserCollectRequest request) {
        return userService.collectArena(request);
    }

    //收藏的球馆列表
    @PostMapping(value = "/collect-list")
    @ResponseBody
    public UserCollectResponse collectList() {
        return userService.collectList();
    }
}
