import java.io.File;

public class FileInformation {
    public static void main(String[] args) {
        info();
    }

    public static void info() {
        File file = new File("file/FileInformation/news.txt"); // �ߴ���һ������
        System.out.println(file.getName()); // ��ȡ�ļ���
        System.out.println(file.getAbsolutePath()); // ��ȡ����·��
        System.out.println(file.getParent()); // ��ø���Ŀ¼
        System.out.println(file.length()); // ����ļ���С�����ֽ�Ϊ��λ��UTF-8�����£�һ������3���ֽڣ�һ����ĸ1���ֽڣ�
        System.out.println(file.exists()); // �Ƿ����
        System.out.println(file.isFile());// �Ƿ�Ϊ�ļ�
        System.out.println(file.isDirectory()); // �Ƿ�Ϊ�ļ���

    }

}
