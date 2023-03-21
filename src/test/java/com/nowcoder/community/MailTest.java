package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
public class MailTest {
    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void  testTextMail(){
        mailClient.sendMail("1293044240@qq.com","呵呵","我是呵呵");


    }

    @Test
    public void  testHtmlMail(){
        Context context = new Context();
        context.setVariable("username","tom");
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);
        mailClient.sendMail("1293044240@qq.com","html",content);
    }
}
