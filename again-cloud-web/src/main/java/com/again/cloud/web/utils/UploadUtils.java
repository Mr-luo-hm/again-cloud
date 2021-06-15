package com.again.cloud.web.utils;

import com.again.start.storage.FileStorageClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UploadUtils {

	/**
	 * 文件上传
	 * @param file 文件内容
	 * @param certification 文件目录
	 * @return
	 * @throws IOException
	 */
	public static String uploadFile(FileStorageClient fileStorageClient, String ossUrl, MultipartFile file,
			String certification, String tenantGuid) throws IOException {

		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String string = uuid + "." + suffix;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String dateTime = sdf.format(date);
		byte[] bytes = file.getBytes();
		InputStream in = new ByteArrayInputStream(bytes);
		String s1 = tenantGuid + "/" + certification + "/" + dateTime + "/" + string;
		fileStorageClient.putObject(s1, in);
		return ossUrl + "/" + s1;
	}

	/**
	 * oss上传程序
	 * @param file 文件内容
	 * @param type 路径
	 * @return
	 */
	public static String uploadProcedure(FileStorageClient fileStorageClient, String ossUrl, MultipartFile file,
			String type, byte[] bytes) {

		String fileName = file.getOriginalFilename();
		InputStream in = new ByteArrayInputStream(bytes);
		String s1 = type + "/" + fileName;
		fileStorageClient.putObject(s1, in);
		return ossUrl + "/" + s1;
	}

}
