package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.MessageMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private MessageMapper mapper;
    @Test
    public void testInsertLoginTicket(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }
    @Test
    public void testSelectLoginTicket(){
        LoginTicket abc = loginTicketMapper.selectByTicket("abc");
        System.out.println(abc);
        int abc1 = loginTicketMapper.updateStatus("abc", 1);
        LoginTicket abc2 = loginTicketMapper.selectByTicket("abc");
        System.out.println(abc2);
    }

    @Test
    public void testUpdateUserHeader(){
        userMapper.updateHeader(159,"http://images.nowcoder.com/head/11t.png");
    }
    @Test
    public void testInsertDiscussPost(){
        DiscussPost post = new DiscussPost();
        post.setUserId(2);
        post.setTitle("HEHEHE");
        post.setContent("hahaha");
        discussPostMapper.insertDiscussPost(post);
    }

    @Test
    public void testSelectDiscussPost(){
        System.out.println(discussPostMapper.selectDiscussPostById(109));
    }
    @Test
    public void testMessageMapper(){
        List<Message> messages = mapper.selectConversations(111, 0, 20);
        for (Message message : messages) {
            System.out.println(message);
        }
        int i = mapper.selectConversationCount(111);
        System.out.println(i);

        List<Message> messages1 = mapper.selectLetters("111_112", 0, 10);
        System.out.println("-------");
        for (Message message : messages1) {
            System.out.println(messages1);
        }

        int i1 = mapper.selectLetterCount("111_112");
        System.out.println(i1);

        int i2 = mapper.selectLetterUnreadCount(131, "111_131");
        System.out.println(i2);


    }
}
