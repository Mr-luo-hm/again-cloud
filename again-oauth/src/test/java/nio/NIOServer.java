package nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author create by 罗英杰 on 2021/8/23
 * @description:
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        //Service端的Channel，监听端口的
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverChannel.configureBlocking(false);
        //nio的api规定这样赋值端口
        serverChannel.bind(new InetSocketAddress(8000));
        //显示Channel是否已经启动成功，包括绑定在哪个地址上
        System.out.println("服务端启动成功，监听端口为8000，等待客户端连接..." + serverChannel.getLocalAddress());
        //声明selector选择器
        Selector selector = Selector.open();
        //这句话的含义，是把selector注册到Channel上面，
        //每个客户端来了之后，就把客户端注册到Selector选择器上,默认状态是Accepted
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);


        //创建buffer缓冲区，声明大小是1024，底层使用数组来实现的
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        RequestHandler requestHandler = new RequestHandler();

        //轮询，服务端不断轮询，等待客户端的连接
        //如果有客户端轮询上来就取出对应的Channel，没有就一直轮询
        while (true) {
            int select = selector.select();
            if (select == 0) {
                continue;
            }
            //有可能有很多，使用Set保存Channel
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                //使用SelectionKey来获取连接了客户端和服务端的Channel
                SelectionKey key = iterator.next();
                //判断SelectionKey中的Channel状态如何，如果是OP_ACCEPT就进入
                if (key.isAcceptable()) {
                    //从判断SelectionKey中取出Channel
                    ServerSocketChannel channel = (ServerSocketChannel)
                            key.channel();
                    //拿到对应客户端的Channel
                    SocketChannel clientChannel = channel.accept();
                    //把客户端的Channel打印出来

                    System.out.println("客户端通道信息打印：" + clientChannel.getRemoteAddress());
                    //设置客户端的Channel设置为非阻塞
                    clientChannel.configureBlocking(false);
                    //操作完了改变SelectionKey中的Channel的状态OP_READ
                    clientChannel.register(selector, SelectionKey.OP_READ);
                }
                //到此轮训到的时候，发现状态是read，开始进行数据交互
                if (key.isReadable()) {
                    //以buffer作为数据桥梁
                    SocketChannel channel = (SocketChannel) key.channel();
                    //数据要想读要先写，必须先读取到buffer里面进行操作
                    channel.read(buffer);
                    //进行读取
                    String request = new String(buffer.array()).trim();
                    buffer.clear();
                    //进行打印buffer中的数据
                    System.out.println(String.format("客户端发来的消息： %s : %s", channel.getRemoteAddress(), request));
                    //要返回数据的话也要先返回buffer里面进行返回
                    String response = requestHandler.handle(request);
                    //然后返回出去
                    channel.write(ByteBuffer.wrap(response.getBytes()));
                }
                iterator.remove();
            }
        }
    }
}
class RequestHandler {
    String handle(String request){
        return request;
    }
}
