import java.io.FileInputStream;

import java.io.IOException;

//下面是一个一个字节地去读取，效率会比较低
public class FileInputStreamzzz {
    public static void main(String[] args) {
        System.out.println("test");
    }

    public static void readFile01() {
        String filePath = "file/FileInputStream/news.txt";
        FileInputStream fileInputStream = null;
        int readData = 0;
        try {
            fileInputStream = new FileInputStream(filePath);

            // read方法一次是读取1个字节，所以这儿用while循环去读
            while ((readData = fileInputStream.read()) != -1) {// read方法读取完毕后会返回-1
                System.out.print((char) readData); // 转换成char类型去输出
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // 下面利用上数组，去提高效率
    public static void readFile02() {
        String filePath = "file/FileInputStream/news.txt";
        byte[] buf = new byte[8];// 一次性读取8个字节
        FileInputStream fileInputStream = null;
        int readLen = 0;
        try {
            fileInputStream = new FileInputStream(filePath);

            // 这儿就是，如有11个字节，则第一次循环会读8个，第二次循环会读剩下的3个
            while ((readLen = fileInputStream.read(buf)) != -1) {// 读取完毕后仍会返回-1
                System.out.print(new String(buf, 0, readLen)); // 这儿不用强制转换的方法去读取了，而是利用String的一个构造方法的方式去显示
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
