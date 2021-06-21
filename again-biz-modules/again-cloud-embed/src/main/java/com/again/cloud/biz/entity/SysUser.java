package com.again.cloud.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class SysUser {

	@TableId(value = "user_id", type = IdType.AUTO)
	private Integer userId;

	private String userName;

	private String password;

	private String token;

}