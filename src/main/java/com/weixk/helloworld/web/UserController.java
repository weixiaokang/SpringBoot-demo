package com.weixk.helloworld.web;

import com.alibaba.fastjson.JSON;
import com.weixk.helloworld.dao.UserDao;
import com.weixk.helloworld.domain.Result;
import com.weixk.helloworld.domain.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户资源控制器
 * Created by Weixk on 16/11/19.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/add")
    public Result<User> add(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {
        User user = new User(name, email);
        user = userDao.save(user);
        if (user == null)
            return new Result<User>(0, "添加用户失败", null);
        return new Result<User>(1, "添加用户成功", user);
    }

    @RequestMapping(value = "/{id}")
    public Result<User> getUserByIdFromPath(@PathVariable long id) {
        if (id <= 0)
            return new Result<User>(0, "id不能小于0", null);
        User user = userDao.findOne(id);
        if (user == null)
            return new Result<User>(0, "用户不存在", null);
        return new Result<User>(1, "查询成功", user);
    }

    @RequestMapping(value = "/user_id={id}")
    public Result<User> getUserByIdFromPathWithKey(@PathVariable(value = "id") long uid) {
        if (uid <= 0)
            return new Result<User>(0, "id不能小于0", null);
        User user = userDao.findOne(uid);
        if (user == null)
            return new Result<User>(0, "用户不存在", null);
        return new Result<User>(1, "查询成功", user);
    }

    @RequestMapping(value = "/find-user")
    public Result<User> getUserByIdFromQueryParam(@RequestParam(value = "id", required = false) Long id) {
        if (id == null)
            return new Result<User>(0, "id不能为空", null);
        if (id <= 0)
            return new Result<User>(0, "id不能小于0", null);
        User user = userDao.findOne(id);
        if (user == null)
            return new Result<User>(0, "用户不存在", null);
        return new Result<User>(1, "查询成功", user);
    }

    @RequestMapping(value = "/find-user", method = RequestMethod.POST)
    public Result<User> getUserByIdFromBodyParam(@RequestParam(value = "id") long id) {
        if (id <= 0)
            return new Result<User>(0, "id不能小于0", null);
        User user = userDao.findOne(id);
        if (user == null)
            return new Result<User>(0, "用户不存在", null);
        return new Result<User>(1, "查询成功", user);
    }

    @RequestMapping(value = "/find-by-name")
    public Result<List<User>> getUserByName(@RequestParam(value = "name") String name) {
        if (name == null || name.length() == 0)
            return new Result<List<User>>(0, "用户名不能为空", null);
        List<User> users = userDao.findUserByName(name);
        if (users == null)
            return new Result<List<User>>(0, "用户不存在", null);
        return new Result<List<User>>(1, "查询成功", users);
    }

    @RequestMapping(value = "/find-by-n")
    public Result<List<User>> getUserByN(@RequestParam(value = "name") String name) {
        if (name == null || name.length() == 0)
            return new Result<List<User>>(0, "用户名不能为空", null);
        List<User> users = userDao.findUserByN(name);
        if (users == null)
            return new Result<List<User>>(0, "用户不存在", null);
        return new Result<List<User>>(1, "查询成功", users);
    }

    @RequestMapping(value = "/find-by-e")
    public Result<List<User>> getUserByE(@RequestParam(value = "email") String email) {
        if (email == null || email.length() == 0)
            return new Result<List<User>>(0, "邮箱不能为空", null);
        List<User> users = userDao.findUserByE(email);
        if (users == null)
            return new Result<List<User>>(0, "用户不存在", null);
        return new Result<List<User>>(1, "查询成功", users);
    }

    @RequestMapping(value = "/find-by-ne")
    public Result<List<User>> getUserByE(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {
        if (name == null || name.length() == 0)
            return new Result<List<User>>(0, "用户名不能为空", null);
        if (email == null || email.length() == 0)
            return new Result<List<User>>(0, "邮箱不能为空", null);
        List<User> users = userDao.findUserByNE(name, email);
        if (users == null)
            return new Result<List<User>>(0, "用户不存在", null);
        return new Result<List<User>>(1, "查询成功", users);
    }
}
