import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author create by 罗英杰 on 2021/8/23
 * @description:
 */
public class TestFileOutputStream {

	public static void main(String[] args) throws IOException {
		// 创建字节输入流、节点流方式读取文件
		FileInputStream fis = new FileInputStream("D:\\doc\\text.mp3");
		// 创建字节输入流、节点流方式输出文件
		FileOutputStream fos = new FileOutputStream("D:\\doc\\copy.mp3");
		// 根据文件大小做一个字节数组
		byte[] arr = new byte[fis.available()];
		// 将文件上的所有字节读取到数组中
		fis.read(arr);
		// 将数组中的所有字节一次写到了文件上
		fos.write(arr);
		fis.close();
		fos.close();
	}

}
