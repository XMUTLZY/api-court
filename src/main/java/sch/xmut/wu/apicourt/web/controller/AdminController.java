package sch.xmut.wu.apicourt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sch.xmut.wu.apicourt.http.request.ArenaRequest;
import sch.xmut.wu.apicourt.http.request.UserRequest;
import sch.xmut.wu.apicourt.http.response.BaseResponse;
import sch.xmut.wu.apicourt.http.response.LayerResponse;
import sch.xmut.wu.apicourt.http.response.OssImageResponse;
import sch.xmut.wu.apicourt.service.ArenaService;
import sch.xmut.wu.apicourt.service.UserService;

/**
 * Created by wu on 2020/05/01
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArenaService arenaService;

    @GetMapping("/user-list")
    @ResponseBody
    public LayerResponse userList(@RequestParam("limit") Integer limit, @RequestParam("page") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.Direction.ASC, "id");
        return userService.getUserList(pageable);
    }

    @PostMapping("/user-search")
    @ResponseBody
    public LayerResponse userSearch(@RequestBody UserRequest userRequest) {
        return userService.findUser(userRequest);
    }

    @GetMapping("/arena-list")
    @ResponseBody
    public LayerResponse arenaList(@RequestParam("limit") Integer limit, @RequestParam("page") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.Direction.ASC, "id");
        return arenaService.getArenaList(pageable);
    }

    //上传图片
    @PostMapping("/image-upload-oss")
    @ResponseBody
    public OssImageResponse imageUploadOss(@RequestParam("file") MultipartFile file) {
        return arenaService.imageUploadOss(file);
    }

    @PostMapping("/arena-add")
    @ResponseBody
    public BaseResponse arenaAdd(@RequestBody ArenaRequest arenaRequest) {
        return arenaService.arenaAdd(arenaRequest);
    }

    @GetMapping("/arena-delete")
    @ResponseBody
    public BaseResponse arenaDelete(@RequestParam Integer arenaId) {
        return arenaService.arenaDelete(arenaId);
    }
}
