package com.weixk.helloworld.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

/**
 * @author weixk
 * @version Created time 16/12/27. Last-modified time 16/12/27.
 */
@Entity
@Table(name = "opt")
public class Opt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createtime")
    private Date createtime;
    @Column(name = "msg")
    @JSONField(serialize = false)
    private String msg;

    @ManyToOne
    private User user;
    public Opt() {

    }

    public Opt(Date createtime, String msg) {
        this.createtime = createtime;
        this.msg = msg;
    }

    public Opt(Long id, Date createtime, String msg) {
        this.id = id;
        this.createtime = createtime;
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Opt{" +
                "id=" + id +
                ", createtime=" + createtime +
                ", msg='" + msg + '\'' +
                '}';
    }
}
