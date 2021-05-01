
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamzzz {
    public static void main(String[] args) {
        writeFile();
    }

    // 将数据写入到文件当中
    // 如果这个文件不存在，则它会自动创建这个文件（创建的前提是目录得存在）
    public static void writeFile() {
        String filePath = "file/FileOutputStream/news.txt";
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(filePath, true); // 第2个参数不写的话则write方法会以覆盖的形式写入

            // 写入一个字节
            fileOutputStream.write('h'); // 虽然write方法的参数是int，但是单个字符的char和int之间是会自动转换的，所以这儿不用去强制转换了

            // 写入字符串
            String str = "hello,world";
            fileOutputStream.write(str.getBytes());// getBytes这个方法可以让字符串转换成个字节数组

            // 另一种写入字符串的方法
            fileOutputStream.write(str.getBytes(), 1, 4); // 从str的第1个位置写到第4个位置

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
