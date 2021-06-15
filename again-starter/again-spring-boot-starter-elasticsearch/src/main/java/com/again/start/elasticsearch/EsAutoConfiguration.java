package com.again.start.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@EnableConfigurationProperties({ EsProperties.class })
public class EsAutoConfiguration {

	private final static String TYPE = "http";

	@Bean
	private RestHighLevelClient getClient(EsProperties properties) {

		return new RestHighLevelClient(RestClient
				.builder(new HttpHost(properties.getHost(), properties.getPort(), TYPE),
						new HttpHost(properties.getHost(), properties.getPort1(), TYPE),
						new HttpHost(properties.getHost(), properties.getPort2(), TYPE))
				.setHttpClientConfigCallback(httpClientBuilder -> {
					httpClientBuilder.disableAuthCaching();
					CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
					credentialsProvider.setCredentials(AuthScope.ANY,
							new UsernamePasswordCredentials(properties.getUserName(), properties.getPassword()));
					return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
				}));
	}

}
