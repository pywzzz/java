import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamzzz {
    public static void main(String[] args) throws IOException {

        // ��Ĭ�������PrintStream������ݵ�λ���Ǳ�׼��������������ʾ���ϣ�
        PrintStream out = System.out;

        // ������print����ȥ���
        out.print("test");

        // ��Ϊprint��Դ��ʹ�õ���write�������������ǿ���ֱ�ӵ���write���������д�ӡ�������
        out.write("��������������".getBytes());

        // ��setOut�������Ըı������λ��
        // ��ôһ��֮�󣬽����ŵ����System.out.println("������������");�Ͳ������������̨�ˣ��������뵽news.txt����ļ���
        System.setOut(new PrintStream("file/PrintStream/news.txt"));
        System.out.println("������������");

        out.close();
    }
}
