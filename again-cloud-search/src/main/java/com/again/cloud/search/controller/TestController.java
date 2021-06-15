package com.again.cloud.search.controller;

import com.again.cloud.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/es")
public class TestController {

	private final SearchService searchService;

	/**
	 * 7.9 版本 删除了字段mapping source must be pairs of fieldnames and properties definition 校验
	 * @param index
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/createIndex")
	public String createIndex(String index) throws IOException {
		boolean indexMessage = searchService.createIndexMessage(index);
		return indexMessage ? "success" : "failed";
	}

	@GetMapping("/update")
	public String update(String index) throws IOException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("tenant_id", 789);
		searchService.update(index, map, "14364757");
		return "success";
	}

}
