package com.mp;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mp.entity.UserInfo;
import com.mp.mapper.UserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Fun Description //TODO
 * @Date 2020/5/24 23:24 24
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateUserInfoTest {

    @Autowired(required = false)
    UserInfoMapper userInfoMapper;

    @Test
    public void updateById() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1087982257332887553l);
        userInfo.setName("陈鸿鸿");
        userInfo.setManagerId(1088250446457389058l);

       int rows =  userInfoMapper.updateById(userInfo);
        System.out.println("更新记录数： " + rows);
    }

    @Test
    public void updateByWrapper() {
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "陈鸿鸿").eq("age", 40);

        UserInfo userInfo = new UserInfo();
        userInfo.setName("chj");
        userInfo.setEmail("aa2@aa.com");

        int rows = userInfoMapper.update(userInfo, updateWrapper);
        System.out.println("更新记录： " + rows);
    }
}
