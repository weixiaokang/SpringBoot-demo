package com.weixk.helloworld.web;

import com.weixk.helloworld.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 发送邮件
 * Created by weixk on 16/12/10.
 */
@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/send")
    public void send(@Valid Email email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        mailSender.send(message);
    }
}
