package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class CommunityApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    DiscussPostMapper discussPostMapper;
    @Test
    void testMapper(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPost(0, 0, 10,0);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }
        int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);


    }
    @Autowired
    UserMapper userMapper;
    @Test
    void testUserMapper(){
        User user = userMapper.selectById(112);
        System.out.println(user);
        User liubei = userMapper.selectByName("liubei");
        System.out.println(liubei);
//        User user1 = new User(111, "张三", "123", "2222", "123@qq.com", 0, 1, "asdasd", "http://images.nowcoder.com/head/138t.png", new Date());
//        userMapper.insertUser(user1);
        userMapper.updateStatus(149,1);


    }

}
