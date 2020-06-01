package com.mp.second.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import sun.jvm.hotspot.oops.FieldType;

/**
 * @Fun Description //TODO
 * @Date 2020/5/29 13:56 29
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Data
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableLogic // 表示逻辑删除标记字段
    @TableField(select = false)  // 表示所有查询当前实例时不会返在这个字段
    private Integer delete;
    @TableField(fill = FieldFill.INSERT)  // 表示在插入数据时自动填充信息； 可以用于记日志功能；
    private String fillField;
    @Version // 表示乐观锁 支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
    private Integer version;
}
