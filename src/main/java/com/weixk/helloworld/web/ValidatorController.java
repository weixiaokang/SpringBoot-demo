package com.weixk.helloworld.web;

import com.weixk.helloworld.domain.UserDao;
import com.weixk.helloworld.domain.Result;
import com.weixk.helloworld.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**`
 *
 * Created by weixk on 16/12/3.
 */
@RestController
@RequestMapping(value = "/valid")
public class ValidatorController {
    @Autowired
    private UserDao userDao;

//    @RequestMapping(value = "/post-user", method = RequestMethod.POST)
//    public Result<User> postUser(@Valid User user, BindingResult result) {
//        if (result.hasErrors()) {
//            System.out.println("result error");
//            return new Result<User>(0, "数据格式非法", null);
//        } else {
//            User u = userDao.save(user);
//            if (user == null)
//                return new Result<User>(0, "添加用户失败", null);
//            return new Result<User>(1, "添加用户成功", user);
//        }
//    }
    @RequestMapping(value = "/post-user", method = RequestMethod.POST)
    public Result<User> postUser(@Validated User user) {
        return new Result<User>(0, "", null);
    }
}
