import java.io.BufferedReader;

import java.io.FileReader;

//BufferedReader�ǰ��ַ�����ģ������������������ļ������ֽ��ļ����Ļ������ܻ�����ļ�����
public class BufferedReaderzzz {
    public static void main(String[] args) throws Exception {
        String filePath = "file/BufferedReader/news.txt";

        // BufferedReaderʵ������һ����װ�ࡣ�����ִ�л���FileReader����ڵ���
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line;

        // ���ж�ȡ�����֮ǰ���Ǹ��������ȡ������������ֳ���BufferedReader�����װ�����;�ˣ���ΪFileReader�฽������Щ�¹��ܣ�
        // �����������֮�󣬻�returnһ��null
        line = bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        // ����ر�BufferedReader�͵��ڹرսڵ���FileReader����ΪufferedReader��Դ���л�رսڵ�����
        bufferedReader.close();

    }
}
