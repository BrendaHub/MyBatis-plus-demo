package com.mp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import sun.security.pkcs11.Secmod;

import java.time.LocalDateTime;

/**
 * @Fun Description //TODO
 * @Date 2020/5/24 23:45 24
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Data
@TableName("user_info")
@EqualsAndHashCode(callSuper = false)
// 要使用AR activeRecord 需要实现一个mobile接口, 直接通过实例不可以完成CRUD， 可以看ARTest测试类
public class UserInfo2 extends Model<UserInfo2> {
    private static final long serialVersionUID = 5132840209279558335L;
    @TableId( type = IdType.ASSIGN_ID) // 这个是首选 的雪花主键算法
    private Long id;
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    @TableField(condition = "%s&lt;#{%s}")
    private Integer age;
    private String email;
    private Long managerId;
    private LocalDateTime createTime;

}
