package com.weixk.helloworld.web;

import com.weixk.helloworld.domain.User;
import com.weixk.helloworld.domain.UserMongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author weixk
 * @version Created time 17/4/3. Last-modified time 17/4/3.
 */
@RestController
@RequestMapping(value = "mongo")
public class MongoController {

    @Autowired
    private UserMongoDao userMongoDao;
    @GetMapping(value = "save")
    public User save(@RequestParam String name) {
        User user = new User();
        user.setId(1L);     // mongodb没有自增功能
        user.setNickname(name);
        return userMongoDao.save(user);
    }

    @GetMapping(value = "find")
    public List<User> find(@RequestParam String name) {
        return userMongoDao.findUserByNickname(name);
    }
}
