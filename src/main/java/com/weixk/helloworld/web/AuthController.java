package com.weixk.helloworld.web;

import com.weixk.helloworld.domain.User;
import com.weixk.helloworld.domain.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author weixk
 * @version Created time 16/12/23. Last-modified time 16/12/23.
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private RedisTemplate<String, User> userRedisTemplate;
    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "/register")
    public String auth(@RequestParam String token, ModelMap model) {
        model.put("content", "注册成功!");
        User user = userRedisTemplate.opsForValue().get(token);
        if (user == null) {
            model.put("content", "链接有效期已过，请重新获取");
            return "authentication";
        }
        User findUser = userDao.findUserByEmail(user.getEmail());
        if (findUser != null) {
            model.put("content", "用户已注册");
            return "authentication";
        }
        user = userDao.save(user);
        if (user == null)
            model.put("content", "注册失败");
        return "authentication";
    }
}
