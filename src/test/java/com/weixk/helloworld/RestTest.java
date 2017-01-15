package com.weixk.helloworld;

import com.weixk.helloworld.domain.User;
import org.junit.After;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author weixk
 * @version Created time 17/1/16. Last-modified time 17/1/16.
 */
public class RestTest {
    @Test
    public void testRestAPI() {
        String url = "http://localhost:8080/user/rest/all";
        RestTemplate restTemplate = new RestTemplate();
        List result = restTemplate.getForObject(url, List.class);
        System.out.println(result.toString());
    }
}
