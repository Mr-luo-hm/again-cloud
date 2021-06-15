package com.again.cloud.search.service;

import org.elasticsearch.action.update.UpdateResponse;

import java.io.IOException;
import java.util.Map;

public interface SearchService {

	boolean createIndexMessage(String index) throws IOException;

	/**
	 * 根据id 更新单条数据某个字段
	 * @param index 索引
	 * @param map key为字段 value
	 * @param id 文档id
	 * @return UpdateResponse
	 * @throws IOException IOException
	 */
	UpdateResponse update(String index, Map<String, Object> map, String id) throws IOException;

}
