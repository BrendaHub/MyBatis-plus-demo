package com.mp.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Fun Description //TODO
 * @Date 2020/5/23 22:31 23
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Data
public class UserInfo {

    private Long id;
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    @TableField(condition = "%s&lt;#{%s}")
    private Integer age;
    private String email;
    private Long managerId;
    private LocalDateTime createTime;

}
