
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamzzz {
    public static void main(String[] args) {
        writeFile();
    }

    // ������д�뵽�ļ�����
    // �������ļ������ڣ��������Զ���������ļ���������ǰ����Ŀ¼�ô��ڣ�
    public static void writeFile() {
        String filePath = "file/FileOutputStream/news.txt";
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(filePath, true); // ��2��������д�Ļ���write�������Ը��ǵ���ʽд��

            // д��һ���ֽ�
            fileOutputStream.write('h'); // ��Ȼwrite�����Ĳ�����int�����ǵ����ַ���char��int֮���ǻ��Զ�ת���ģ������������ȥǿ��ת����

            // д���ַ���
            String str = "hello,world";
            fileOutputStream.write(str.getBytes());// getBytes��������������ַ���ת���ɸ��ֽ�����

            // ��һ��д���ַ����ķ���
            fileOutputStream.write(str.getBytes(), 1, 4); // ��str�ĵ�1��λ��д����4��λ��

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
