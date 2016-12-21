package com.weixk.helloworld.domain;

import com.alibaba.fastjson.JSON;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import java.io.Serializable;

/**
 *
 * Created by weixk on 16/12/10.
 */
public class UserResgiter implements Serializable {

    private static final long serialVersionUID = -1L;
    @NotEmpty(message = "名字不能为空")
    @Length(max = 16, message = "昵称不能超过16个字符")
    private String nickname;
    @Email(message = "邮箱格式不正确")
    private String email;
    @NotEmpty(message = "密码不能为空")
    @Length(min = 8, max = 16, message = "密码长度为8~16字符")
    private String pwd;
    @NotEmpty(message = "确认密码不能为空")
    @Length(min = 8, max = 16, message = "密码长度为8~16字符")
    private String valid_pwd;

    public UserResgiter() {

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getValid_pwd() {
        return valid_pwd;
    }

    public void setValid_pwd(String valid_pwd) {
        this.valid_pwd = valid_pwd;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
