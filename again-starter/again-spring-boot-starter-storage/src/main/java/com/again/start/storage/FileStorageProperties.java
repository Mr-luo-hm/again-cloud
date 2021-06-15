package com.again.start.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Hccake
 * @version 1.0
 * @date 2019/7/16 15:34
 */
@Data
@ConfigurationProperties(prefix = "file.storage")
public class FileStorageProperties {

	/**
	 * 使用的文件存储服务类型
	 */
	private FileStorageTypeEnum type;

	/**
	 * endpoint 服务地址 eg. http://oss-cn-qingdao.aliyuncs.com
	 */
	private String endpoint;

	/**
	 * 密钥key
	 */
	private String accessKey;

	/**
	 * 密钥Secret
	 */
	private String accessSecret;

	/**
	 * bucketName
	 */
	private String bucketName;

}
