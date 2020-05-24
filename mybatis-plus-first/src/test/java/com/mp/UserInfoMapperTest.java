package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.mp.entity.User;
import com.mp.entity.UserInfo;
import com.mp.mapper.UserInfoMapper;
import com.mp.mapper.UserMapper;
import net.minidev.json.JSONValue;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Fun Description //TODO
 * @Date 2020/5/23 22:33 23
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoMapperTest {

    @Autowired(required = false)
    UserInfoMapper userInfoMapper;


    @Test
    public void testSelectWrapper() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        // 这种apply的使用可能会有SQL注入的风险
//        queryWrapper.apply("date_format('create_time','%Y-%m-%d') = '2019-02-14'")
        // 实际项目中建议使用这种apply的使用方工， 采用占位符的方式；
        queryWrapper.apply("date_format('create_time','%Y-%m-%d') = {0} ",  "2019-02-14")
                .inSql("manager_id", "select id from user_info where name like '%王%'");

        List<UserInfo> userInfoList = userInfoMapper.selectList(queryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }

    @Test
    public void testSelectWrapper1() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").and(wq->wq.lt("age", 40)
                .or().isNotNull("email"));
        List<UserInfo> userInfoList = userInfoMapper.selectList(queryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }

    @Test
    public void testSelectWrapper2() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(wq -> wq.lt("age", 30).or().isNotNull("email"))
                .likeRight("name", "王");
        List<UserInfo> userInfoList = userInfoMapper.selectList(queryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }

    @Test
    public void testSelectWrapper3() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30,31,32,34));
        List<UserInfo> userInfoList = userInfoMapper.selectList(queryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }

    @Test
    public void testSelectWrapper2Supper() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "age")
                .nested(wq -> wq.lt("age", 30).or().isNotNull("email"))
                .likeRight("name", "王");
        List<UserInfo> userInfoList = userInfoMapper.selectList(queryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }

    // 通过这种方式排除查询出来的列名
    @Test
    public void testSelectWrapper2Supper2() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .nested(wq -> wq.lt("age", 30).or().isNotNull("email"))
                .likeRight("name", "王")
                .select(UserInfo.class, column -> !column.getColumn().equals("create_time") &&
                        !column.getColumn().equals("manager_id"));
        List<UserInfo> userInfoList = userInfoMapper.selectList(queryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }

    @Test
    public void testContitionQuery() {
        String name = "boss";
        Integer age = 34;
        contitionQuery(name, age);
    }

    private void contitionQuery(String name, Integer age) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name)
                .ge(age > 20 && age < 100, "age", age);
        List<UserInfo> userInfoList = userInfoMapper.selectList(queryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }

    // 通过构造查询Entity的查询对象进行查询
    @Test
    public void testSelectWhereEntity() {
        UserInfo userInfoWhere = new UserInfo();
        // 这里默认是等值， 如果需要like的查询； 就需要在实例上进行注解进行注明
        // 如 :  @TableField(condition = SqlCondition.LIKE)
        userInfoWhere.setName("李"); // 通过构造查询实例，实现条件精确查询
        // 可以指定数值类型的属性进行不同规则的查询；
        userInfoWhere.setAge(40);
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>(userInfoWhere);
        List<UserInfo> userInfoList = userInfoMapper.selectList(queryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }

    @Test
    public void testALLEQ() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        Map<String, Object> param = new HashMap<>();
        param.put("name", "王天风");
        param.put("age", 25);
//        queryWrapper.allEq(param);
//        queryWrapper.allEq(param, false); // 表示如果条件为null时表示不把这个条件放在查询where中
        queryWrapper.allEq((k,v)->!k.equals("name"), param);  // 可以通过前面lambd表达式进行条件过滤

        List<UserInfo> userInfoList = userInfoMapper.selectList(queryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }

    @Test
    public void testSelectMaps() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        Map<String, Object> param = new HashMap<>();
        param.put("name", "");
        param.put("age", "");
        param.put("id", 1087982257332887553l);
        queryWrapper.allEq((k,v)-> !"".equals(param.get(k)),param);
        List<Map<String, Object>> userInfoList = userInfoMapper.selectMaps(queryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }

    @Test
    public void testSelectMaps2() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) as avg_age", "min(age) as min_age", "max(age) as max_age")
                .groupBy("manager_id").having("max(age) < {0}", 500);
        List<Map<String, Object>> userInfoList = userInfoMapper.selectMaps(queryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }

    // selectObjs 注意不管返回多少列信息， 他只会返回第一列内容
    @Test
    public void testSelectObjs() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) as avg_age", "min(age) as min_age", "max(age) as max_age")
                .groupBy("manager_id").having("max(age) < {0}", 500);
        List<Object> userInfoList = userInfoMapper.selectObjs(queryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }

    @Test
    public void testSelectLambda() {
        // 构建lambda式的三种方式,如以下三种方式：
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new QueryWrapper<UserInfo>().lambda();
//        LambdaQueryWrapper<UserInfo>  lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
//        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper2 = Wrappers.<UserInfo>lambdaQuery();
        lambdaQueryWrapper.like(UserInfo::getName, "王").ge(UserInfo::getAge, 20);

        List<Map<String, Object>> userInfoList = userInfoMapper.selectMaps(lambdaQueryWrapper);
        userInfoList.stream().forEach(System.out::println);

    }

    @Test
    public void testSelectLambda2() {
        // 构建lambda式的三种方式,如以下三种方式：
//        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new QueryWrapper<UserInfo>().lambda();
//        LambdaQueryWrapper<UserInfo>  lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper2 = Wrappers.<UserInfo>lambdaQuery();
        lambdaQueryWrapper2.likeRight(UserInfo::getName, "王")
                            .and(item -> item.ge(UserInfo::getAge, 20).or().isNotNull(UserInfo::getEmail));

        List<Map<String, Object>> userInfoList = userInfoMapper.selectMaps(lambdaQueryWrapper2);
        userInfoList.stream().forEach(System.out::println);

    }

    @Test
    public void testSelectLambda3() {
        List<UserInfo> userInfoList = new LambdaQueryChainWrapper<UserInfo>(userInfoMapper)
                .like(UserInfo::getName, "雨")
                .ge(UserInfo::getAge, 20)
                .list();
        userInfoList.stream().forEach(System.out::println);
    }

    @Test
    public void testSelectLambda3One() {
        UserInfo userInfo = new LambdaQueryChainWrapper<UserInfo>(userInfoMapper)
//                .like(UserInfo::getName, "雨")
                .eq(UserInfo::getId, 1088248166370832385l)
                .one();
//        userInfoList.stream().forEach(System.out::println);
        System.out.println(JSONValue.toJSONString(userInfo));
    }

    // 自定义查询wrapper
    @Test
    public void testSelectLambdaCustomerSQL() {
        LambdaQueryWrapper<UserInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.likeRight(UserInfo::getName, "王");
        List<UserInfo> userInfoList = userInfoMapper.selectConsumerSQL(lambdaQueryWrapper);
        userInfoList.stream().forEach(System.out::println);
    }
}
