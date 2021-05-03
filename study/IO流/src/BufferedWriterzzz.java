import java.io.BufferedWriter;
import java.io.FileWriter;

//BufferedWriter是按字符处理的，如果用它处理二进制文件（即字节文件）的话，可能会出现文件的损坏
public class BufferedWriterzzz {
    public static void main(String[] args) throws Exception {
        String filePath = "file/BufferedWriter/news.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));// FileWriter(filePath,true)
                                                                                     // 则表示以追加的方式写入
        bufferedWriter.write("测试1。");
        bufferedWriter.newLine(); // 插入一个和系统相关的换行符
        bufferedWriter.write("测试2。。");
        bufferedWriter.newLine();
        bufferedWriter.write("测试3。。。");
        bufferedWriter.newLine();
        bufferedWriter.close();// 记得关闭，要不写不进去的
    }
}
