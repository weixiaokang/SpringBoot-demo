package com.weixk.helloworld.web;

import com.weixk.helloworld.domain.Email;
import com.weixk.helloworld.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
    public Result<String> send(@Valid Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        mailSender.send(message);
        return new Result<>(1, "发送邮件成功");
    }

    @PostMapping(value = "/send")
    public Result<String> sendLink(@RequestParam String link) {
        File file = new File("/Users/www1/IdeaProjects/HelloWorld/target/classes/static/email.html");
        if (!file.exists())
            return new Result<>(0, "邮件模板文件不存在");
        try {
            FileReader reader = new FileReader(file);
            int i = 0;
            StringBuilder builder = new StringBuilder();
            while ((i = reader.read()) >= 0) {
                builder.append((char) i);
            }
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("15951911977@163.com");
            helper.setTo("876267672@qq.com");
            helper.setSubject("发送模板邮件");
            String text = builder.toString().replace("${content}", link);
            helper.setText(text, true);
            mailSender.send(message);
            return new Result<>(1, "发送邮件成功");
        } catch (FileNotFoundException e) {
            return new Result<>(0, "邮件模板文件不存在");
        } catch (IOException e) {
            return new Result<>(0, "邮件模板文件读取失败");
        } catch (MessagingException e) {
            return new Result<>(0, "创建mime邮件失败");
        }
    }
}
