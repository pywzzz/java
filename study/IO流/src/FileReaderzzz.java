
import java.io.FileReader;
import java.io.IOException;

//��������Զ�ȡ�ַ������������read����ʱҲ���������������
//FileInputStreamɶ��ֻ�ܶ�ȡһ�����ֽ��ǣ�����������ȡ���ֵ��ļ��ģ���Щ�ļ�һ��һ���ֽڶ�ȡ��������ĵ��������

public class FileReaderzzz {
    public static void main(String[] args) {
        System.out.println("test");
    }

    // �������Ե����ַ������ַ���ȥ��ȡ�ļ�
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

    // ������ʹ���ַ�����ȥ��ȡ�ļ�
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
