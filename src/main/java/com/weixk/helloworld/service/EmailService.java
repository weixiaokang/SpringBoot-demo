package com.weixk.helloworld.service;

import com.weixk.helloworld.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author weixk
 * @version Created time 16/12/23. Last-modified time 16/12/23.
 */
@Service(value = "email")
public class EmailService {

    @Value("${me.templates.email.location}")
    private String tempPath;
    @Value("${me.email}")
    private String myEmail;
    @Autowired
    private JavaMailSender mailSender;
    public String sendTempEmail(String to, String token) {
        File file = new File(tempPath);
        if (!file.exists())
            return "邮件模板文件不存在";
        try {
            FileReader reader = new FileReader(file);
            int i = 0;
            StringBuilder builder = new StringBuilder();
            while ((i = reader.read()) >= 0) {
                builder.append((char) i);
            }
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(myEmail);
            helper.setTo(to);
            helper.setSubject("发送激活邮件");
            String content = "http://127.0.0.1:8080/auth/register?token=" + token;
            String text = builder.toString().replace("${content}", content);
            helper.setText(text, true);
            mailSender.send(message);
            return "信息已提交，发送激活邮件成功";
        } catch (FileNotFoundException e) {
            return "邮件模板文件不存在";
        } catch (IOException e) {
            return "邮件模板文件读取失败";
        } catch (MessagingException e) {
            return "创建mime邮件失败";
        }
    }
}
