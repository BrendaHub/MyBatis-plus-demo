package com.mp.second.customer;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.mp.second.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Fun Description //TODO
 * @Date 2020/5/29 15:20 29
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Component
@Slf4j
public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        if(entity instanceof User) {
            log.info("nextId is " + ((User) entity).getAge() + 1);
            Long nextId = 0l;

            return nextId = new Long(System.currentTimeMillis());
        }
        return new Random(1000).nextInt();
    }

    @Override
    public String nextUUID(Object entity) {
        return null;
    }
}
