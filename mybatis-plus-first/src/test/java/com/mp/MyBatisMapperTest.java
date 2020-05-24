package com.mp;

import com.mp.entity.User;
import com.mp.entity.User1;
import com.mp.mapper.User1Mapper;
import com.mp.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * @Fun Description //TODO
 * @Date 2020/5/23 16:14 23
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisMapperTest {

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired(required = false)
    User1Mapper user1Mapper;

    @Test
    public void testSelectItems() {
        User user = new User();
        user.setAge(30);
        user.setEmail("aaa@aa.com");
        user.setName("h2_name");
        int row = userMapper.insert(user);
        Assert.assertEquals(1, row);
        List<User> list = userMapper.selectList(null);
        list.stream().forEach(System.out::print);
//        Assert.assertEquals(5 , list.size());
    }

    @Test
    public void testUser1() {
        User1 user1 = new User1();
        user1.setName("User1Entity");
        user1.setCount(new Random().nextInt());
        user1.setCreateTime(LocalDateTime.now());
        user1.setRemark("备注字段， 不在表中");
        int row1 = user1Mapper.insert(user1);
        Assert.assertEquals(1, row1);
    }
}
