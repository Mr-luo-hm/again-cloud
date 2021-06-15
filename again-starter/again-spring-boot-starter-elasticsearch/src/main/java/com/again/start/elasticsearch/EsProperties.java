package com.again.start.elasticsearch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "again.starter.es")
public class EsProperties {

	private String host;

	private Integer port;

	private Integer port1;

	private Integer port2;

	private String index;

	private String type;

	private String clusterName;

	private Integer tcpPort;

	private String userName;

	private String password;

}
