import java.util.Scanner;

//��׼������System.in�ĸ��ࡢ����������InputStream������������BufferedInputStream��Ĭ���豸�Ǽ���
//Scanner���Ǵ�System.in������ϻ�ôӼ������������Ϣ

//��׼�����System.out�ĸ��ࡢ����������PrintStream����������Ҳ��PrintStream��Ĭ���豸����ʾ��
//ƽ���õģ�System.out.println("xxx"); ����ʹ��out���������������ʾ����

public class SystemXX {
    public static void main(String[] args) {
        System.out.println("test");

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        System.out.println("test��" + next);
    }

}
