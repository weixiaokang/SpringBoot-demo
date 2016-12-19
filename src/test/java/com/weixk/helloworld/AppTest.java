package com.weixk.helloworld;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * Unit test for simple Application.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest
{
    @Autowired
    private Environment environment;
    @Test
    public void test() {
        System.out.println(environment.getProperty("me.test.str"));
    }
}
