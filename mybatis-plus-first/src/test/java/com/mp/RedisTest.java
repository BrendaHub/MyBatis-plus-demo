package com.mp;

import com.loiter.easy.redis.annotation.EnableRedis;
import com.loiter.easy.redis.constants.RedisModeConstants;
import com.loiter.easy.redis.template.EasyRedisTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author loiter
 * @date 2020/11/15 21:23
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableRedis
public class RedisTest {

    @Autowired
    private EasyRedisTemplate easyRedisTemplate;

    @Test
    public void testSet() {
        System.out.println(easyRedisTemplate);
        easyRedisTemplate.set("helloWorld", "helloWorld");
    }
}
