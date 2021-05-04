import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Propertieszzz {
    public static void main(String[] args) throws IOException {
        simple3();
    }

    // ������һ�㷽������ȡmysql.properties����ļ������õ�ip��user��pwd
    public static void simple1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("file/Properties/mysql.properties"));
        String line = "";
        while ((line = br.readLine()) != null) {// ��Ϊ�㲻֪������Ҫ��ʲô������ȥѭ����ȡ
            String[] split = line.split("=");
            System.out.println(split[0] + "��ֵΪ��" + split[1]);
        }
        br.close();
    }

    // ������Properties������ȡmysql.properties����ļ�
    public static void simple2() throws FileNotFoundException, IOException {
        // Properties���Ǹ�ר�����ڶ�д�����ļ��ļ�����
        // ����ȡ�������ļ�Ҫ��ר�ŵĸ�ʽ����=ֵ
        // ���м�ֵ�Բ���Ҫ�пո�ֵ����Ҫ������������Ĭ��������String��
        Properties properties = new Properties();
        properties.load(new FileReader("file/Properties/mysql.properties"));// ����ָ���������ļ�
        properties.list(System.out);// �Ѽ�ֵ����ʾ����Ļ
        String user = properties.getProperty("user");// ���ݼ���ö�Ӧ��ֵ
        System.out.println("test������" + user);
    }

    // ����˦Properties�������������ļ����޸�������
    public static void simple3() throws FileNotFoundException, IOException {
        Properties properties = new Properties();

        // setProperty���������������ǲ����ڣ��Ǿ����½������ֵ�ԣ���������ڣ��Ǿ����޸ĵ�Ч��
        properties.setProperty("�û�user", "���Һ�");// �洢ʱ�������ת�������Ӧ��unicode��ֵ
        properties.setProperty("pwd", "ac2312");

        // ��������Ū�Ĵ洢��ָ���ļ�����
        properties.store(new FileOutputStream("file/Properties/test.properties"), "����һ��ע��");// �ڶ�����������Ϊnull

    }
}
