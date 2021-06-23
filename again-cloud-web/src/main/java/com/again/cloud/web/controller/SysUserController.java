package com.again.cloud.web.controller;

import com.again.cloud.biz.entity.SysUser;
import com.again.cloud.biz.result.Result;
import com.again.cloud.web.service.SysUserService;
import com.again.cloud.web.service.api.CacheService;
import com.again.cloud.web.utils.JacksonUtils;
import com.again.cloud.web.utils.UploadUtils;
import com.again.start.storage.FileStorageClient;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin
public class SysUserController {

	private final SysUserService sysUserService;

	private final CacheService cacheService;

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

	@GetMapping("/username")
	public List<SysUser> getUsername(String type) {
		System.out.println(type);
		ArrayList<SysUser> list = new ArrayList<>();
		for (int i = 10; i > 0; i--) {
			SysUser user = new SysUser();
			user.setUserId(i);
			user.setUserName(i + "ssss");
			list.add(user);
		}
		return list;
	}

	@GetMapping("/testRouteing")
	public String testRouteing() {
		System.out.println("aaaaaaaaaaaaaaaaaaaa");
		String aaa = cacheService.getFromRedis("aaa");
		return aaa;
	}

}
