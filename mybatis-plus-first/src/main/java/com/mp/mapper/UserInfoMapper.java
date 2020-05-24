package com.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mp.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Fun Description //TODO
 * @Date 2020/5/23 22:33 23
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select * from user_info ${ew.customSqlSegment}")
    List<UserInfo> selectConsumerSQL(@Param(Constants.WRAPPER) Wrapper<UserInfo> wrapper);
}
