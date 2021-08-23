package bioThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author create by 罗英杰 on 2021/8/23
 * @description:
 */
public class BIOThreadPoolService {
    public static void main(String[] args) throws IOException {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        try {
            ServerSocket server = new ServerSocket(8000);
            System.out.println("服务端启动成功，监听端口为8000，等待客户端连接...");
            while (true) {
                Socket socket = server.accept();//等待客户连接
                System.out.println("客户连接成功，客户信息为：" + socket.getRemoteSocketAddress());
                //使用线程池中的线程去执行每个对应的任务
                executorService.execute(new Thread(new Runnable() {
                            public void run() {
                                try {
                                    InputStream in = socket.getInputStream();
                                    byte[] buffer = new byte[1024];
                                    int len = 0;
                                    //读取客户端的数据
                                    while ((len = in.read(buffer)) > 0) {
                                        System.out.println(new String(buffer, 0, len));
                                    }
                                    //向客户端写数据
                                    OutputStream out = socket.getOutputStream();
                                    out.write("hello".getBytes());
                                } catch (
                                        IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
