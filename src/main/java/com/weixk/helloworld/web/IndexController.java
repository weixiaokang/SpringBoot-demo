package com.weixk.helloworld.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 * Created by Weixk on 16/11/19.
 */
@RestController
public class IndexController {

    @Autowired
    private Environment environment;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Hello, SpringBoot!";
    }
}
