package com.weixk.helloworld;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * Unit test for simple Application.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest
{
    private static final Logger log = LoggerFactory.getLogger(AppTest.class);
    @Autowired
    private Environment environment;
    @Test
    public void testProperty() {
        System.out.println(environment.getProperty("me.test.str"));
    }
    @Test
    public void testLog() {
        log.error("测试log日志打印！");
    }
    @Test
    public void testStaticFile() {
        String root = new File("").getAbsolutePath();
        String path = root + File.separator + "static/email.html";
        File file = new File(path);
        Assert.assertTrue(file.exists());
    }
}
