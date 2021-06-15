package com.again.start.storage.aliyun;

import com.again.start.storage.FileStorageClient;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RequiredArgsConstructor
public class AliyunOssClient implements FileStorageClient, InitializingBean, DisposableBean {

	private final String endpoint;

	private final String accessKey;

	private final String accessSecret;

	private final String bucketName;

	private OSS client;

	private static String HEADER_NO_CACHE = "no-cache";

	private final static ByteArrayInputStream FOLDER_CONTENT = new ByteArrayInputStream(new byte[0]);

	/**
	 * 文件上传
	 * @param objectName 存储对象名称
	 * @param inputStream 文件输入流
	 * @return
	 */
	@Override
	public String putObject(String objectName, InputStream inputStream) {

		client.putObject(bucketName, objectName, inputStream);
		return objectName;
	}

	/**
	 * 文件删除
	 * @param objectName 存储对象名称
	 */
	@Override
	public void deleteObject(String objectName) {
		if (client.doesObjectExist(bucketName, objectName)) {
			client.deleteObject(bucketName, objectName);
		}
	}

	/**
	 * 复制文件
	 * @param sourceObjectName 原对象名
	 * @param targetObjectName 目标对象名
	 */
	@Override
	public void copyObject(String sourceObjectName, String targetObjectName) {
		client.copyObject(bucketName, sourceObjectName, bucketName, targetObjectName);
	}

	/**
	 * 创建文件夹
	 * @param folderName
	 */
	@Override
	public void createFolder(String folderName) {
		if (!client.doesObjectExist(bucketName, folderName)) {
			client.putObject(bucketName, folderName, FOLDER_CONTENT);
		}
	}

	/*
	 * @Override public String uploadFile(String folderName, File file) { ObjectMetadata
	 * objectMetadata = new ObjectMetadata();
	 * objectMetadata.setCacheControl(HEADER_NO_CACHE); PutObjectResult putObjectResult =
	 * client.putObject(bucketName, folderName + file.getName(), file, objectMetadata);
	 * System.out.println(putObjectResult); return folderName; }
	 */
	@Override
	public void afterPropertiesSet() {
		org.springframework.util.Assert.hasText(endpoint, "endpoint 为空");
		org.springframework.util.Assert.hasText(accessKey, "Oss accessKey为空");
		org.springframework.util.Assert.hasText(accessSecret, "Oss accessSecret为空");
		client = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);
	}

	@Override
	public void destroy() {
		if (this.client != null) {
			this.client.shutdown();
		}
	}

}
