package com.weixk.helloworld.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户类
 * Created by Weixk on 16/11/19.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Transient
    private String token;

    public User() {

    }
    public User(String name, String email, String password) {
        this.nickname = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
