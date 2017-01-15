package com.weixk.helloworld.web;

import com.weixk.helloworld.domain.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weixk
 * @version Created time 17/1/16. Last-modified time 17/1/16.
 * ps: fastjson不能转换成xml格式数据，需要把消息转换器HttpMessageConverters设置为jackson。
 * 即将Application.java中的fastJsonHttpMessageConverters注释掉。
 */
@RestController
public class MessageController {

    @GetMapping(value = "/message")
    public Message getMsg() {
        Message message = new Message();
        message.setId(1);
        message.setMsg("返回xml格式数据");
        return message;
    }
}
