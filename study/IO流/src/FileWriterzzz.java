import java.io.FileWriter;
import java.io.IOException;

public class FileWriterzzz {
    public static void main(String[] args) {
        writeFile();
    }

    public static void writeFile() {
        String filePath = "file/FileWriter/news.txt";
        FileWriter fileWriter = null;
        char chars[] = { 'a', 'b', 'c' };
        try {
            fileWriter = new FileWriter(filePath);

            // 写入单个字符
            fileWriter.write('H');

            // 写入指定字符数组
            fileWriter.write(chars);

            // 写入指定范围的字符数组
            fileWriter.write("测试一哈哈哈哈哈哈".toCharArray(), 2, 6); // toCharArray方法是让字符串转换成数组

            // 写入字符串
            fileWriter.write("你麻麻的");

            // 写入指定范围的字符串
            fileWriter.write("阿打算放上去", 1, 2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();// 一定要close或者flush这个流，才能真正写入到文件当中（这儿close相当于flush加上close，即刷新并关闭）
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
