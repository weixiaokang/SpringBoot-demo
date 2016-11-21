package com.weixk.helloworld.web;

import com.weixk.helloworld.domain.User;
import org.springframework.web.bind.annotation.*;

/**
 * 用户资源控制器
 * Created by Weixk on 16/11/19.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/{id}")
    public User getUserByIdFromPath(@PathVariable int id) {
        return getUserById(id);
    }

    @RequestMapping(value = "/user_id={id}")
    public User getUserByIdFromPathWithKey(@PathVariable(value = "id") int uid) {
        return getUserById(uid);
    }

    @RequestMapping(value = "/find-user")
    public User getUserByIdFromQueryParam(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null) id = 0;
        return getUserById(id);
    }

    @RequestMapping(value = "/find-user", method = RequestMethod.POST)
    public @ResponseBody User getUserByIdFromBodyParam(@RequestParam(value = "id") int id) {
        return getUserById(id);
    }

    private User getUserById(int id) {
        switch (id) {
            case 1:
                return new User(1, "小明");
            case 2:
                return new User(2, "小红");
            default:
                return new User(3, "老王");
        }
    }
}
