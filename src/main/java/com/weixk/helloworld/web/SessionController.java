package com.weixk.helloworld.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by weixk on 17/8/25.
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/uid")
    public String uid(HttpSession session) {
        UUID uuid = (UUID) session.getAttribute("uuid");
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        session.setAttribute("uuid", uuid);
        return session.getId();
    }
}
