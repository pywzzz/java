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

            // д�뵥���ַ�
            fileWriter.write('H');

            // д��ָ���ַ�����
            fileWriter.write(chars);

            // д��ָ����Χ���ַ�����
            fileWriter.write("����һ������������".toCharArray(), 2, 6); // toCharArray���������ַ���ת��������

            // д���ַ���
            fileWriter.write("�������");

            // д��ָ����Χ���ַ���
            fileWriter.write("���������ȥ", 1, 2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();// һ��Ҫclose����flush���������������д�뵽�ļ����У����close�൱��flush����close����ˢ�²��رգ�
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
