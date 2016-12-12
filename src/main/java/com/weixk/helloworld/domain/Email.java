package com.weixk.helloworld.domain;

/**
 *
 * Created by weixk on 16/12/10.
 */
public class Email {
    @org.hibernate.validator.constraints.Email(message = "邮箱格式不正确")
    private String from;
    @org.hibernate.validator.constraints.Email(message = "邮箱格式不正确")
    private String to;
    private String subject;
    private String text;
    public Email() {

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
