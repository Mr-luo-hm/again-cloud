package com.again.start.storage;

import java.io.InputStream;

public interface FileStorageClient {

	/**
	 * 文件上传
	 * @param objectName 存储对象名称
	 * @param inputStream 文件输入流
	 * @return 文件相对路径
	 */
	String putObject(String objectName, InputStream inputStream);

	/**
	 * 文件删除
	 * @param objectName 存储对象名称
	 */
	void deleteObject(String objectName);

	/**
	 * 复制文件
	 * @param sourceObjectName 原对象名
	 * @param targetObjectName 目标对象名
	 */
	void copyObject(String sourceObjectName, String targetObjectName);

	void createFolder(String folderName);

	/* String uploadFile(String folderName, File file); */

}
