import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterzzz {
    public static void main(String[] args) throws IOException {

        // �������������Ļ
        PrintWriter printWriter1 = new PrintWriter(System.out);
        printWriter1.print("��ma����");
        printWriter1.close();

        // ������������ļ�
        PrintWriter printWriter2 = new PrintWriter(new FileWriter("file/PrintWriter/news.txt"));
        printWriter2.print("��Ǯ�ж�����");
        printWriter2.close();
    }
}
