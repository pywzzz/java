import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputStreamzzz {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        String filePath = "file/ObjectXXputStream/news.nmsl";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));

        // ��ȡ��˳��Ҫ�������л���˳��һ�£�������Exception
        System.out.println(ois.readInt());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readChar());
        System.out.println(ois.readDouble());
        System.out.println(ois.readUTF());

        // ����������Ҳ�ɣ� System.out.println(ois.readObject());
        Object dog = ois.readObject();
        System.out.println(dog);

        // ��������������ķ���ʱ������������Dog���е�getName����

        ois.close(); // �ǵùرա������˷���Դ
    }
}
