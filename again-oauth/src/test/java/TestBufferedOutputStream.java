import java.io.*;

/**
 * @author create by 罗英杰 on 2021/8/23
 * @description:
 */
public class TestBufferedOutputStream {//创建文件输入流对象,关联致青春.mp3

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("D:\\doc\\text.mp3");
        //创建缓冲区对fis装饰
        BufferedInputStream bis = new BufferedInputStream(fis);
        //创建输出流对象,关联copy.mp3
        FileOutputStream fos = new FileOutputStream("D:\\doc\\copy2.mp3");
        //创建缓冲区对fos装饰
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        //循环直接输出
        int i;
        while ((i = bis.read()) != -1) {
            bos.write(i);
        }
        bis.close();
        bos.close();
    }
}
