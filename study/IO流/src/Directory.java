import java.io.File;

public class Directory {
    public static void main(String[] args) {
        System.out.println("test");
    }

    // ɾ��һ��ָ�����ļ�
    public static void m1() {
        String filePath = "file/Directory/news.txt";
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) { // ���ɾ���ķ����᷵��һ��booleanֵ
                System.out.println("ɾ���ɹ�");
            } else {
                System.out.println("ɾ��ʧ��");
            }
        } else {
            System.out.println("���ļ�������");
        }
    }

    // ɾ��һ��ָ����Ŀ¼
    public static void m2() {
        String filePath = "file/Directory1";
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) { // ���ɾ���ķ����᷵��һ��booleanֵ
                System.out.println("ɾ���ɹ�");
            } else {
                System.out.println("ɾ��ʧ��");
            }
        } else {
            System.out.println("��Ŀ¼������");
        }
    }

    // �����ļ���
    public static void m3() {
        String directoryPath = "file/Directory";
        File file = new File(directoryPath);
        if (file.exists()) {
            System.out.println(directoryPath + " ����ļ����Ѿ������ˡ�����");
        } else {
            if (file.mkdirs()) {// ��������ڴ����༶Ŀ¼���� mkdir() ֻ�ܴ���һ��Ŀ¼
                System.out.println(directoryPath + " �����ɹ�������");
            } else {
                System.out.println(directoryPath + " ����ʧ�ܡ�����");
            }
        }
    }

}
