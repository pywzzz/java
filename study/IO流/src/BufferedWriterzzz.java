import java.io.BufferedWriter;
import java.io.FileWriter;

//BufferedWriter�ǰ��ַ�����ģ������������������ļ������ֽ��ļ����Ļ������ܻ�����ļ�����
public class BufferedWriterzzz {
    public static void main(String[] args) throws Exception {
        String filePath = "file/BufferedWriter/news.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));// FileWriter(filePath,true)
                                                                                     // ���ʾ��׷�ӵķ�ʽд��
        bufferedWriter.write("����1��");
        bufferedWriter.newLine(); // ����һ����ϵͳ��صĻ��з�
        bufferedWriter.write("����2����");
        bufferedWriter.newLine();
        bufferedWriter.write("����3������");
        bufferedWriter.newLine();
        bufferedWriter.close();// �ǵùرգ�Ҫ��д����ȥ��
    }
}
