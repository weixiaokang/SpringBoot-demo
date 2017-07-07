package com.weixk.helloworld.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页
 * Created by Weixk on 16/11/19.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/thymeleaf/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("content", "Hello, StringBoot!");
        return "index";
    }
    @RequestMapping(value = "/jsp/index", method = RequestMethod.GET)
    public String jspIndex(Model model) {
        model.addAttribute("content", "It's JSP file");
        return "jsp/index";
    }
}
