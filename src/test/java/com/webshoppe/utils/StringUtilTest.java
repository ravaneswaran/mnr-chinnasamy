package com.webshoppe.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringUtilTest {

    @Autowired
    private StringUtil stringUtil;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.stringUtil);
    }

    @Test
    public void testIsEmpty(){
        String testStr = "";

        boolean result = this.stringUtil.isEmpty(testStr);

        Assert.assertTrue(result);
    }

    @Test
    public void testGetResourceAsString() throws IOException {
        String resourcePath = "mail-templates/signup-verification-mail.html";

        String result = this.stringUtil.getResourceAsString(resourcePath);

        Assert.assertNotNull(result);
    }
}
