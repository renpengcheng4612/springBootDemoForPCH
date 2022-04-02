package com.pc.sz.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("users")
public class Users {
    /**
     * 主键id 用户id
     */
    @TableId("id")
    private String userId;

    /**
     * 用户名 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码 密码
     */
    private String password;

    /**
     * 昵称 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 头像
     */
    private String face;

    /**
     * 手机号 手机号
     */
    private String mobile;

    /**
     * 邮箱地址 邮箱地址
     */
    private String email;

    /**
     * 性别 性别 1:男  0:女  2:保密
     */
    private Integer sex;

    /**
     * 生日 生日
     */
    private Date birthday;

    /**
     * 创建时间 创建时间
     */

    private Date createdTime;

    /**
     * 更新时间 更新时间
     */

    private Date updatedTime;

    /**
     * 标记
     */
    @TableField(exist = false)
    private String remark;

    /**
     * 年龄
     */
    private Integer age;

}