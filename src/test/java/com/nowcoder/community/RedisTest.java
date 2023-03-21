package com.nowcoder.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testString(){
        String redisKey = "test:count";
//        redisTemplate.opsForValue().set(redisKey,1);
        System.out.println(redisTemplate.opsForValue().get(redisKey));
        System.out.println(redisTemplate.opsForValue().increment(redisKey));
        System.out.println(redisTemplate.opsForValue().decrement(redisKey));

    }

    @Test
    public void testHash(){
        String redisKey = "test:user";
        redisTemplate.opsForHash().put(redisKey,"id",1);
        redisTemplate.opsForHash().put(redisKey,"username","张三");

        System.out.println(redisTemplate.opsForHash().get(redisKey,"id"));
        System.out.println(redisTemplate.opsForHash().get(redisKey,"username"));
    }

    @Test
    public void testList(){
        String redisKey = "test:list";
        redisTemplate.opsForList().leftPush(redisKey,"v1");
        redisTemplate.opsForList().leftPush(redisKey,"v2");
        redisTemplate.opsForList().leftPush(redisKey,"v3");

        System.out.println(redisTemplate.opsForList().range(redisKey,0,-1));
        System.out.println(redisTemplate.opsForList().index(redisKey,0));
        System.out.println(redisTemplate.opsForList().size(redisKey));

        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));

    }

    @Test
    public void testSets(){
        String redisKey = "test:teachers";
        redisTemplate.opsForSet().add(redisKey,"刘备","关羽","张飞");

        System.out.println(redisTemplate.opsForSet().size(redisKey));
        System.out.println(redisTemplate.opsForSet().members(redisKey));
        System.out.println(redisTemplate.opsForSet().pop(redisKey));
    }

    @Test
    public void testZSet(){
        String redisKey = "test:student";
        redisTemplate.opsForZSet().add(redisKey,"唐",80);
        redisTemplate.opsForZSet().add(redisKey,"呵呵",90);
        redisTemplate.opsForZSet().add(redisKey,"哈哈",100);
        redisTemplate.opsForZSet().add(redisKey,"嘿嘿",110);

        System.out.println(redisTemplate.opsForZSet().zCard(redisKey)); //统计
        System.out.println(redisTemplate.opsForZSet().score(redisKey,"呵呵")); //查看分数
        System.out.println(redisTemplate.opsForZSet().rank(redisKey,"呵呵")); //查看排名 小到大
        System.out.println(redisTemplate.opsForZSet().reverseRank(redisKey,"呵呵")); //查看排名 大到小
        System.out.println(redisTemplate.opsForZSet().range(redisKey,0,-1)); //小到大
        System.out.println(redisTemplate.opsForZSet().reverseRange(redisKey,0,-1)); //大到小

    }
    @Test
    public void testKeys(){
        redisTemplate.delete("test:user");
        redisTemplate.hasKey("test:user");

        redisTemplate.expire("test:student",10, TimeUnit.SECONDS);

    }

    //多次访问同一个key
    @Test
    public void testBoundOperations(){
        String redisKey = "test:count";
        //BoundXXXXXOperations
//        BoundHashOperations
//        BoundListOperations
        BoundValueOperations operations = redisTemplate.boundValueOps(redisKey);
        operations.increment();
        operations.increment();
        operations.increment();
        operations.increment();
        System.out.println(operations.get());
    }
    //编程式事务
    @Test
    public void tetTransaction(){
        Object obj = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String redisKey = "test:tx";
                operations.multi(); //开启事务

                operations.opsForSet().add(redisKey,"张三");
                operations.opsForSet().add(redisKey,"李四");
                operations.opsForSet().add(redisKey,"王五");
                System.out.println(operations.opsForSet().members(redisKey)); //无效

                return operations.exec(); //提交事务
            }
        });

        System.out.println(obj);
    }

}
