import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author create by 罗英杰 on 2021/8/23
 * @description:
 */
public class TestBufferedReader {
    public static void main(String[] args) throws IOException {
        int num = 0;
        //字符流接收使用的String数组
        String[] bufstring = new String[1024];
        //字符流、节点流打开文件类
        FileReader fr = new FileReader("D:\\doc\\test.txt");//文件必须存在
        //字符流、处理流读取文件类
        BufferedReader br = new BufferedReader(fr);
        //临时接收数据使用的变量
        String line = null;
        //BufferedReader.readLine()：单行读取，读取为空返回null
        while ((line = br.readLine()) != null) {
            bufstring[num] = line;
            num++;
        }
        br.close();//关闭文件
        for (int i = 0; i < num; i++) {
            System.out.println(bufstring[i]);
        }
    }
}
