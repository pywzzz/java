import java.io.File;
import java.io.IOException;

public class FileCreate {
    public static void main(String[] args) {
        create01();
        create02();
        create03();
    }

    // ֱ��ָ��·��ȥ�����ļ�
    public static void create01() {
        String filePath = "file/FileCreate/news1.txt";
        File file = new File(filePath);
        try {
            file.createNewFile();
            System.out.println("û�з����쳣������1�ɹ������ļ�");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ��ָ�����ļ�����ָ�����ļ�·��ȥ�����ļ�
    public static void create02() {
        File parentFile = new File("file/FileCreate");
        String fileName = "news2.txt";
        File file = new File(parentFile, fileName);
        try {
            file.createNewFile();
            System.out.println("û�з����쳣������2�ɹ������ļ�");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // ָ�����ļ�·�������ļ�·��ȥ�����ļ�
    public static void create03() {
        String parentFile = "file/FileCreate/";
        String fileName = "news3.txt";
        File file = new File(parentFile, fileName);
        try {
            file.createNewFile();
            System.out.println("û�з����쳣������3�ɹ������ļ�");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
