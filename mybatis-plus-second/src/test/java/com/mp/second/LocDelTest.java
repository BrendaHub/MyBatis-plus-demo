package com.mp.second;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.second.entity.User;
import com.mp.second.mappers.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @Fun Description //TODO 逻辑删除实现
 * @Date 2020/5/29 15:54 29
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@SpringBootTest
public class LocDelTest {

    @Autowired
   UserMapper userMapper;

    // 如何做到让某个字段永远不在查询结果里不出现；
    @Test
    public void loicDel(){
        int rows = userMapper.delete(Wrappers.<User>lambdaQuery().select(User::getName, User::getAge, User::getEmail).like(User::getName, "学"));
        System.out.println("逻辑删除条数： " + rows);
    }

    @Test
    public void loicSelect() {
        Page<User> pageList = userMapper.selectPage(new Page(1,2), Wrappers.<User>lambdaQuery().lt(User::getAge, 26) );
//        Page<Map<String,Object>> pageList = userMapper.selectMapsPage(new Page(1, 2), Wrappers.<User>lambdaQuery().lt(User::getAge, 26));
        System.out.println("pages is " + pageList.getPages());
        System.out.println("totals is " + pageList.getTotal());
        pageList.getRecords().stream().forEach(System.out::println);
    }

    @Test
    public void fillField() {
        User user = new User();
        user.setName("自动填充");
        user.setAge(20);
        user.setEmail("fillField@aa.com");

        int rows = userMapper.insert(user);
        Assert.assertEquals(1, rows);
    }

    // 乐观锁单元测试
    @Test
    public void versionTest() {
        // 先查出当前记录的版本号
        User user = userMapper.selectById(5l);
        Integer version = user.getVersion();

        // 更新这条记录
        user.setName("乐观锁测试");
        user.setFillField("乐购锁测试");

        int rows = userMapper.updateById(user);
        System.out.println(" 影响记录数： " + rows);
        Assert.assertEquals(1, rows);
    }

}
