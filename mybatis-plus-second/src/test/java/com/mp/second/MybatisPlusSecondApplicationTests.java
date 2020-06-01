package com.mp.second;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.second.customer.CustomIdGenerator;
import com.mp.second.entity.User;
import com.mp.second.mappers.UserMapper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusSecondApplicationTests {

	@Autowired
	UserMapper userMapper;
	@Test
	@Ignore
	void contextLoads() {
	}

	@Test
	public void test() {
//		Assert.assertNotNull(userMapper);
//		List<User> userList = userMapper.selectList(null);

		LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
		lambdaQueryWrapper.like(User::getName, "om");
		List<User> userList = userMapper.selectList(lambdaQueryWrapper);
		userList.stream().forEach(System.out::println);
	}

	@Test
	public void selectByID() {
//		User user = userMapper.selectById(1);
		// 这个查询只能返回一条记录， 如果结果集>1条就会报错； 但是在service层，如果结果集有多条， 会返回第一条
		User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().like(User::getName, "o"));
		System.out.println(user);
	}

	@Test
	public void selectPage() {
		// 分页有二个情况， 一个是返回List<Object> 一个是返回List<Map<String,Object>>

		IPage<User> page = new Page<>(1, 2);
		LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery();
		lambdaQueryWrapper.ge(User::getAge, 0);
		IPage<User> userIPage = userMapper.selectPage(page, lambdaQueryWrapper);
		System.out.println(userIPage.getTotal());
		System.out.println(userIPage.getPages());
		userIPage.getRecords().stream().forEach(System.out::println);

	}

	@Test
	public void insert() {
		User user = new User();
//		user.setId((Long)customIdGenerator.nextId(user));
		user.setName("蚂蚁学技");
		user.setAge(20);
		user.setEmail("aasdf@loiter.com");

		int row = userMapper.insert(user);
		Assert.assertEquals(1, row);
	}

}
