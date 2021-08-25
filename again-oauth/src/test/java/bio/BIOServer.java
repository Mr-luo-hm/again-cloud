package bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author create by 罗英杰 on 2021/8/23
 * @description:
 */
public class BIOServer {

	public static void main(String[] args) {
		// 使用Socket进行网络通信
		ServerSocket server = null;
		Socket socket = null;
		// 基于字节流
		InputStream in = null;
		OutputStream out = null;
		try {
			server = new ServerSocket(8000);
			System.out.println("服务端启动成功，监听端口为8000，等待客户端连接...");
			while (true) {
				socket = server.accept(); // 等待客户端连接
				System.out.println("客户连接成功，客户信息为：" + socket.getRemoteSocketAddress());
				in = socket.getInputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				// 读取客户端的数据
				while ((len = in.read(buffer)) > 0) {
					System.out.println(new String(buffer, 0, len));
				}
				// 向客户端写数据
				out = socket.getOutputStream();
				out.write("hello!".getBytes());
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
