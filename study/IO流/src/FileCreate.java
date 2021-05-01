import java.io.File;
import java.io.IOException;

public class FileCreate {
    public static void main(String[] args) {
        create01();
        create02();
        create03();
    }

    // 直接指定路径去创建文件
    public static void create01() {
        String filePath = "file/FileCreate/news1.txt";
        File file = new File(filePath);
        try {
            file.createNewFile();
            System.out.println("没有发生异常，方法1成功创建文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 先指定父文件，再指定字文件路径去创建文件
    public static void create02() {
        File parentFile = new File("file/FileCreate");
        String fileName = "news2.txt";
        File file = new File(parentFile, fileName);
        try {
            file.createNewFile();
            System.out.println("没有发生异常，方法2成功创建文件");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 指定父文件路径和子文件路径去创建文件
    public static void create03() {
        String parentFile = "file/FileCreate/";
        String fileName = "news3.txt";
        File file = new File(parentFile, fileName);
        try {
            file.createNewFile();
            System.out.println("没有发生异常，方法3成功创建文件");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
