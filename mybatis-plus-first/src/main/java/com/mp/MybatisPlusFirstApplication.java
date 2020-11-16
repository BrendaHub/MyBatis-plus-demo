package com.mp;

import com.loiter.easy.redis.annotation.EnableRedis;
import com.loiter.easy.redis.constants.RedisModeConstants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mp.mapper")
@EnableRedis(value = RedisModeConstants.REDIS_SINGLE)
public class MybatisPlusFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusFirstApplication.class, args);
    }

}
