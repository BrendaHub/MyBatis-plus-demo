package com.mp;

import com.mp.entity.UserInfo2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @Fun Description //TODO
 * @Date 2020/5/24 23:49 24
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ARTest {

    //注意这里就不需要注入UserInfoMapper

    // 要使用AR操作CRUD需要二件条件：
    // 1、 entity 要 extend Model<T>
    // 2、 需要有与之对应的Mapper对象；
    @Test
    public void saveAR() {
        UserInfo2 userInfo = new UserInfo2();
        userInfo.setName("刘明明明");
        userInfo.setAge(20);
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setEmail("aaa@aa.com");
        userInfo.setManagerId(1088250446457389058l);

        boolean insertBool = userInfo.insert();
        System.out.println("记录插件结果： " + insertBool);
    }
}
