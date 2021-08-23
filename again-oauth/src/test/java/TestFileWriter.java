import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author create by 罗英杰 on 2021/8/23
 * @description:
 */
public class TestFileWriter {
    public static void main(String[] args) throws IOException {
        //File是操作文件类
        File file = new File("D:\\doc\\test.txt");//文件必须存在
        //字符流、节点流写出文件类
        // new FileWriter(file,true);//这个true代表追加，不写就代表覆盖文件
        FileWriter out = new FileWriter(file, true);
        //写入的字节,\n代表换行
        String str = "\nholler";
        //写入
        out.write(str);
        out.close();
    }
}
