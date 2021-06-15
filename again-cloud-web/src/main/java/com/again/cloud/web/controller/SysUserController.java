package com.again.cloud.web.controller;

import com.again.cloud.biz.entity.SysUser;
import com.again.cloud.web.service.SysUserService;
import com.again.cloud.web.service.api.CacheService;
import com.again.cloud.web.utils.JacksonUtils;
import com.again.cloud.web.utils.UploadUtils;
import com.again.start.storage.FileStorageClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SysUserController {

	private final SysUserService sysUserService;

	private final CacheService cacheService;

	private final FileStorageClient fileStorageClient;

	@GetMapping("/load")
	public SysUser loadUserByUsername(String username) {
		SysUser sysUser = sysUserService.getByUsername(username);
		if (sysUser == null) {
			log.error("登陆：用户名错误，用户名：{}", username);
			throw new RuntimeException("username error!");
		}
		cacheService.set(sysUser.getUserName(), JacksonUtils.toJson(sysUser));
		return sysUser;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		String uploadFile = UploadUtils.uploadFile(fileStorageClient, "again-cloud.oss-cn-shanghai.aliyuncs.com", file,
				"test", "test");
		return "success";
	}

}
