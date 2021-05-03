
import java.io.FileReader;
import java.io.IOException;

//这个还可以读取字符，所以这儿你read汉字时也不会出现乱码的情况
//FileInputStream啥的只能读取一个个字节是，它是用来读取音乐等文件的，这些文件一个一个字节读取不会有损耗的情况出现

public class FileReaderzzz {
    public static void main(String[] args) {
        System.out.println("test");
    }

    // 下面是以单个字符单个字符地去读取文件
    public static void readFile01() {
        String filePath = "file/FileReader/news.txt";
        FileReader fileReader = null;
        int data = 0;
        try {
            fileReader = new FileReader(filePath);
            while ((data = fileReader.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    // 下面是使用字符数组去读取文件
    public static void readFile02() {
        String filePath = "file/FileReader/news.txt";
        FileReader fileReader = null;
        int readLen = 0;
        char[] buf = new char[8];
        try {
            fileReader = new FileReader(filePath);
            while ((readLen = fileReader.read(buf)) != -1) {
                System.out.print(new String(buf, 0, readLen));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}
