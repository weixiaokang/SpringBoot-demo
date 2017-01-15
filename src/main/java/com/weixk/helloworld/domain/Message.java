package com.weixk.helloworld.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author weixk
 * @version Created time 17/1/16. Last-modified time 17/1/16.
 */
@XmlRootElement
public class Message {

    private long id;
    private String msg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
