package com.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mp.entity.UserInfo;
import com.mp.mapper.UserInfoMapper;
import com.mp.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * @Fun Description //TODO  这样就完成了一个mp下的基本版本的service
 * @Date 2020/5/25 00:45 25
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
}
