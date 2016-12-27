package com.weixk.helloworld.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author weixk
 * @version Created time 16/12/27. Last-modified time 16/12/27.
 */
@Entity
@Table(name = "opt")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    private Long _id;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createtime")
    private Date createtime;
    @Column(name = "msg")
    @JSONField(serialize = false)
    private String msg;

    public Option() {

    }

    public Option(Date createtime, String msg) {
        this.createtime = createtime;
        this.msg = msg;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
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
}
