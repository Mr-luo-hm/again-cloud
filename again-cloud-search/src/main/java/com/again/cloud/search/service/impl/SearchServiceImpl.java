package com.again.cloud.search.service.impl;

import com.again.cloud.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchServiceImpl implements SearchService {

	private final RestHighLevelClient restHighLevelClient;

	@Override
	public boolean createIndexMessage(String index) throws IOException {
		// 创建索引
		CreateIndexRequest request = new CreateIndexRequest(index);
		// 2、设置索引的settings
		request.settings(Settings.builder()
				// 分片数
				.put("index.number_of_shards", 5)
				// 副本数
				.put("index.number_of_replicas", 1)

		);

		// 3、设置索引的mapping 默认文档类型_doc
		request.mapping("{\n" + "      \"properties\": {\n" + "        \"action_type\": {\n"
				+ "          \"type\": \"long\"\n" + "        },\n" + "        \"chat_type\": {\n"
				+ "          \"type\": \"long\"\n" + "        },\n" + "        \"content\": {\n"
				+ "          \"type\": \"text\",\n" + "          \"fields\": {\n" + "            \"keyword\": {\n"
				+ "              \"type\": \"keyword\",\n" + "              \"ignore_above\": 256\n" + "            }\n"
				+ "          }\n" + "        },\n" + "        \"content_type\": {\n" + "          \"type\": \"long\"\n"
				+ "        },\n" + "        \"create_time\": {\n" + "          \"type\": \"date\"\n" + "        },\n"
				+ "        \"current_whats_id\": {\n" + "          \"type\": \"text\",\n" + "          \"fields\": {\n"
				+ "            \"keyword\": {\n" + "              \"type\": \"keyword\",\n"
				+ "              \"ignore_above\": 256\n" + "            }\n" + "          }\n" + "        },\n"
				+ "        \"friend_whats_id\": {\n" + "          \"type\": \"text\",\n" + "          \"fields\": {\n"
				+ "            \"keyword\": {\n" + "              \"type\": \"keyword\",\n"
				+ "              \"ignore_above\": 256\n" + "            }\n" + "          }\n" + "        },\n"
				+ "        \"id\": {\n" + "          \"type\": \"long\"\n" + "        },\n" + "        \"msg_id\": {\n"
				+ "          \"type\": \"text\",\n" + "          \"fields\": {\n" + "            \"keyword\": {\n"
				+ "              \"type\": \"keyword\",\n" + "              \"ignore_above\": 256\n" + "            }\n"
				+ "          }\n" + "        },\n" + "        \"origin_type\": {\n" + "          \"type\": \"text\",\n"
				+ "          \"fields\": {\n" + "            \"keyword\": {\n"
				+ "              \"type\": \"keyword\",\n" + "              \"ignore_above\": 256\n" + "            }\n"
				+ "          }\n" + "        },\n" + "        \"quoted_msg_svrid\": {\n"
				+ "          \"type\": \"text\",\n" + "          \"fields\": {\n" + "            \"keyword\": {\n"
				+ "              \"type\": \"keyword\",\n" + "              \"ignore_above\": 256\n" + "            }\n"
				+ "          }\n" + "        },\n" + "        \"send_time\": {\n" + "          \"type\": \"long\"\n"
				+ "        },\n" + "        \"sender_whats_id\": {\n" + "          \"type\": \"text\",\n"
				+ "          \"fields\": {\n" + "            \"keyword\": {\n"
				+ "              \"type\": \"keyword\",\n" + "              \"ignore_above\": 256\n" + "            }\n"
				+ "          }\n" + "        },\n" + "        \"tenant_id\": {\n" + "          \"type\": \"long\"\n"
				+ "        },\n" + "        \"user_id\": {\n" + "          \"type\": \"long\"\n" + "        }\n"
				+ "      }\n" + "}",

				XContentType.JSON);

		// 5、 发送请求
		// 5.1 同步方式发送请求
		CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
		// 6、处理响应
		boolean acknowledged = createIndexResponse.isAcknowledged();
		boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
		return acknowledged;
	}

	@Override
	public UpdateResponse update(String index, Map<String, Object> map, String id) throws IOException {
		UpdateRequest request = new UpdateRequest(index, "_doc", id).doc(map);
		UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
		return updateResponse;
	}

}
