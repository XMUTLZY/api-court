package sch.xmut.wu.apicourt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wu on 2020/05/01
 */
@Controller
@RequestMapping("/view")
public class ViewController {
    @RequestMapping("/index")
    public String adminIndex() {
        return "/adminIndex";
    }
}