package com.gyh.manhattan;

import com.gyh.manhattan.common.ConstParam;
import com.gyh.manhattan.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManhattanApplicationTests {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void findAllUsers()  {
        redisUtil.set("key","hello");
    }


    @Test
    public void findAllUsers2()  {
        System.out.println("get key value:"+ redisUtil.get("c19d536b-b9c4-4a5d-8aaa-b103d12d5e82"));
    }
}
