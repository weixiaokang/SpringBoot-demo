package com.weixk.helloworld.web;

import com.weixk.helloworld.domain.*;

import com.weixk.helloworld.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, User> userRedisTemplate;
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/rest/{id}")
    public User getByIdFromPath(@PathVariable long id) {

        return userDao.findOne(id);
    }
    @RequestMapping(value = "/rest/all")
    public List<User> getAll() {
        return userDao.findAll();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<User> register(@Valid UserRegister userResgiter) {
        if (!userResgiter.getPwd().equals(userResgiter.getValid_pwd()))
            return new Result<>(0, "两次输入密码不一致");
        User user = userDao.findUserByEmail(userResgiter.getEmail());
        if (user != null)
            return new Result<>(0, "该邮箱已注册");
        String pwd = DigestUtils.md5DigestAsHex(userResgiter.getPwd().trim().getBytes());
        user = new User(userResgiter.getNickname(), userResgiter.getEmail(), pwd);
        String token = DigestUtils.md5DigestAsHex(userResgiter.getEmail().getBytes());
        userRedisTemplate.opsForValue().set(token, user, 30, TimeUnit.MINUTES);
        String result = emailService.sendTempEmail(userResgiter.getEmail(), token);
        return new Result<>(1, "信息已提交, 请点击邮箱中的链接激活");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<User> login(@Valid UserLogin userLogin) {
        User user = userDao.findUserByEmail(userLogin.getEmail());
        if (user == null)
            return new Result<>(0, "用户不存在，请检查邮箱是否正确");
        String pwd = DigestUtils.md5DigestAsHex(userLogin.getPassword().trim().getBytes());
        if (!pwd.equals(user.getPassword()))
            return new Result<>(0, "密码不正确");
        String baseToken = user.getId() + "&" + userLogin.getPassword() + "&" + System.currentTimeMillis();
        String token = DigestUtils.md5DigestAsHex(baseToken.getBytes());
        stringRedisTemplate.opsForValue().set(String.valueOf(user.getId()), token, 60, TimeUnit.SECONDS);
        user.setToken(token);
        user.setPassword(null);
        return new Result<>(1, "登录成功", user);
    }

    @RequestMapping(value = "/{id}")
    public Result<User> getUserByIdFromPath(@PathVariable long id) {
        if (id <= 0)
            return new Result<>(0, "id不能小于0", null);
        User user = userDao.findOne(id);
        if (user == null)
            return new Result<>(0, "用户不存在", null);
        return new Result<>(1, "查询成功", user);
    }
    @RequestMapping(value = "/all")
    public Result<List<User>> getAllUsers() {
        List<User> result = userDao.findAll();
        return new Result<>(1, "查询成功", result);
    }
    @RequestMapping(value = "/user_id={id}")
    public Result<User> getUserByIdFromPathWithKey(@PathVariable(value = "id") long uid) {
        if (uid <= 0)
            return new Result<>(0, "id不能小于0", null);
        User user = userDao.findOne(uid);
        if (user == null)
            return new Result<>(0, "用户不存在", null);
        return new Result<>(1, "查询成功", user);
    }

    @RequestMapping(value = "/find-user")
    public Result<User> getUserByIdFromQueryParam(@RequestParam(value = "id", required = false) Long id) {
        if (id == null)
            return new Result<>(0, "id不能为空", null);
        if (id <= 0)
            return new Result<>(0, "id不能小于0", null);
        User user = userDao.findOne(id);
        if (user == null)
            return new Result<>(0, "用户不存在", null);
        return new Result<>(1, "查询成功", user);
    }

    @PostMapping(value = "/find-by-name")
    public Result<List<User>> getUserByName(@RequestParam(value = "name") String name) {
        if (name == null || name.length() == 0)
            return new Result<>(0, "用户名不能为空", null);
        List<User> users = userDao.findUserByNickname(name);
        if (users == null)
            return new Result<>(0, "用户不存在", null);
        return new Result<>(1, "查询成功", users);
    }

    @RequestMapping(value = "/find-by-n")
    public Result<List<User>> getUserByN(@RequestParam(value = "name") String name) {
        if (name == null || name.length() == 0)
            return new Result<>(0, "用户名不能为空", null);
        List<User> users = userDao.findUserByN(name);
        if (users == null)
            return new Result<>(0, "用户不存在", null);
        return new Result<>(1, "查询成功", users);
    }

    @RequestMapping(value = "/find-by-e")
    public Result<List<User>> getUserByE(@RequestParam(value = "email") String email) {
        if (email == null || email.length() == 0)
            return new Result<>(0, "邮箱不能为空", null);
        List<User> users = userDao.findUserByE(email);
        if (users == null)
            return new Result<>(0, "用户不存在", null);
        return new Result<>(1, "查询成功", users);
    }

    @RequestMapping(value = "/find-by-ne")
    public Result<List<User>> getUserByE(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {
        if (name == null || name.length() == 0)
            return new Result<>(0, "用户名不能为空", null);
        if (email == null || email.length() == 0)
            return new Result<>(0, "邮箱不能为空", null);
        List<User> users = userDao.findUserByNE(name, email);
        if (users == null)
            return new Result<>(0, "用户不存在", null);
        return new Result<>(1, "查询成功", users);
    }

    @RequestMapping(value = "/post-user", method = RequestMethod.POST)
    public Result<User> postUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("result error");
            return new Result<>(0, "数据格式非法", null);
        } else {
            User u = userDao.save(user);
            if (u == null)
                return new Result<>(0, "添加用户失败", null);
            return new Result<>(1, "添加用户成功", user);
        }
    }
}
