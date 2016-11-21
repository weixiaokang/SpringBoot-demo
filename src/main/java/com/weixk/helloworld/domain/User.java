package com.weixk.helloworld.domain;

/**
 * 用户类
 * Created by Weixk on 16/11/19.
 */
public class User {

    private Integer id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
