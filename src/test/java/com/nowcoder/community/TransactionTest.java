package com.nowcoder.community;

import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionTest {
    @Autowired
    private AlphaService alphaService;
    @Test
    public void testSave1(){
        Object o = alphaService.save1();
        System.out.println(o);
    }
}
