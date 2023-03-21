package com.nowcoder.community;

import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SensitiveTest {
    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Test
    public void testSensitiveFilter(){
        String text = "这里可以赌博,可以嫖娼,可以吸毒,可以开票,哈哈";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }

    @Test
    public void test(){
        String aaa = CommunityUtil.md5("aaa");
        System.out.println(aaa);
    }
}
