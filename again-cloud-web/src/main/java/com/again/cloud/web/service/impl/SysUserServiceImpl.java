package com.again.cloud.web.service.impl;

import com.again.cloud.biz.entity.SysUser;
import com.again.cloud.biz.mapper.SysUserMapper;
import com.again.cloud.web.service.SysUserService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	/**
	 * 根据用户名查询用户
	 * @param username 用户名
	 * @return 系统用户
	 */
	@Override
	public SysUser getByUsername(String username) {
		return baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUserName, username));
	}

}
