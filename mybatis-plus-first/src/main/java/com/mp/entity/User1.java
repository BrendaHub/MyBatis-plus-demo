package com.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Fun Description //TODO
 * @Date 2020/5/23 19:08 23
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@Data
// 在entity 与 表不是按规则进行匹配的就需要使用tableanem注解进行对定
@TableName(value = "USERINFO", schema = "PUBLIC")
public class User1 {
    @TableId // 指定这个字段为表的主键ID，这样mp就会自增ID
    private Long id;
    @TableField(value = "name")
    private String name;
    private Integer count;
    private LocalDateTime createTime;

    // 这个字段不与表中的字段有对应关系；
    // 这样mp提供了三个解决方案：
    // 1 在定义属性时指定一个关键 transient 如
        // private transient String remark;
    // 2 设置属性为status 字段， 如：
        // private static String remark; 但是这里需要注意， static属性lombok不会生成get/set
    // 3 采用mp的注解TableField（exist = false) 表示这个属性不在表中, 如：
        //  @TableField(exist = false)
        //  private  String remark;
    @TableField(exist = false)
    private  String remark;

}
