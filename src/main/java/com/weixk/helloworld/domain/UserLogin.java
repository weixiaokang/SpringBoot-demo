package com.weixk.helloworld.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * Created by weixk on 16/12/10.
 */
public class UserLogin {
    @Email(message = "邮箱格式不正确")
    private String email;
    @NotEmpty(message = "密码不能为空")
    @Length(min = 8, max = 16, message = "密码长度为8~16字符")
    private String password;
    public UserLogin() {

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
