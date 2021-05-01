import java.io.FileInputStream;

import java.io.IOException;

//������һ��һ���ֽڵ�ȥ��ȡ��Ч�ʻ�Ƚϵ�
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

            // read����һ���Ƕ�ȡ1���ֽڣ����������whileѭ��ȥ��
            while ((readData = fileInputStream.read()) != -1) {// read������ȡ��Ϻ�᷵��-1
                System.out.print((char) readData); // ת����char����ȥ���
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

    // �������������飬ȥ���Ч��
    public static void readFile02() {
        String filePath = "file/FileInputStream/news.txt";
        byte[] buf = new byte[8];// һ���Զ�ȡ8���ֽ�
        FileInputStream fileInputStream = null;
        int readLen = 0;
        try {
            fileInputStream = new FileInputStream(filePath);

            // ������ǣ�����11���ֽڣ����һ��ѭ�����8�����ڶ���ѭ�����ʣ�µ�3��
            while ((readLen = fileInputStream.read(buf)) != -1) {// ��ȡ��Ϻ��Ի᷵��-1
                System.out.print(new String(buf, 0, readLen)); // �������ǿ��ת���ķ���ȥ��ȡ�ˣ���������String��һ�����췽���ķ�ʽȥ��ʾ
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
