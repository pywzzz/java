import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderzzz {
    public static void main(String[] args) throws IOException {

        // ���news.txt�ı��벻��Ĭ�ϵ�UTF-8�ˡ�����ANSI�����������ת���Ļ�����ȡʱ���Ǹ�����
        String filePath = "file/InputStreamReader/news.txt";

        // ��FileInputStreamת����InputStreamReader��ָ������Ϊgbk
        InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "gbk");

        // ��BufferedReaderȥ��ȡ�Ƚϸ�ЧЩ
        BufferedReader br = new BufferedReader(isr);

        // ����������Ҳ��д��һ��
        // BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "gbk"));

        String s = br.readLine();
        System.out.println(s);

        br.close();// �ر���
    }
}
