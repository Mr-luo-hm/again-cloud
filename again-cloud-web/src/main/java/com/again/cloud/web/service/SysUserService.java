package com.again.cloud.web.service;

import com.again.cloud.biz.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysUserService extends IService<SysUser> {

	/**
	 * 根据用户名查询用户
	 * @param username 用户名
	 * @return SysUser- q
	 */
	SysUser getByUsername(String username);

}
