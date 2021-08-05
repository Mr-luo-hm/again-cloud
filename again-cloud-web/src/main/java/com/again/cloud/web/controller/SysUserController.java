package com.again.cloud.web.controller;

import com.again.cloud.biz.entity.SysUser;
import com.again.cloud.biz.result.Result;
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
@CrossOrigin
public class SysUserController {

	private final FileStorageClient fileStorageClient;

	@PostMapping("/login")
	public Result<?> loadUserByUsername(@RequestBody SysUser sysUser) {
		if (sysUser == null) {
			log.error("登陆：用户名错误，用户名：{}", sysUser.getUserName());
			throw new RuntimeException("username error!");

		}
		else {
			if (sysUser.getUserName().equals("admin")) {
				sysUser.setToken(sysUser.getUserName());
				return Result.ok(sysUser);
			}
		}
		// cacheService.set(sysUser.getUserName(), JacksonUtils.toJson(sysUser));
		return Result.failed(sysUser);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		String uploadFile = UploadUtils.uploadFile(fileStorageClient, "again-cloud.oss-cn-shanghai.aliyuncs.com", file,
				"test", "test");
		return "success";
	}

}
