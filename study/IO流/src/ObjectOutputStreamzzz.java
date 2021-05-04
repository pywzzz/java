import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectOutputStreamzzz {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // ���л��󣬱�����ļ���ʽ�����Ǵ��ı��ģ����ǰ������ĸ�ʽ�������
        // ���������ļ���׺������ģ����Ӻ�׺Ҳ�У�
        String filePath = "file/ObjectXXputStream/news.nmsl";

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));

        oos.writeInt(100); // writeInt��������int�Զ�תΪ��װ��Integer��Integerʵ����Serializable�ӿڣ����Կ������л���
        oos.writeBoolean(true);// writeBoolean��������boolean�Զ�תΪ��װ��Boolean��Booleanʵ����Serializable�ӿڣ����Կ������л���
        oos.writeChar('A');// writeChar��������char�Զ�תΪ��װ��Char��Charʵ����Serializable�ӿڣ����Կ������л���
        oos.writeDouble(9.5);// writeDouble��������double�Զ�תΪ��װ��Double��Doubleʵ����Serializable�ӿڣ����Կ������л���
        oos.writeUTF("����������");// Stringʵ����Serializable�ӿڣ����Կ������л�
        oos.writeObject(new Dog("���Һ�", 10)); // ����һ������
        oos.close();

    }
}

class Dog implements Serializable { // Ҫ�����л����͵�ȥʵ��Serializable����ӿ�
    private String name;
    private int age;

    public Dog(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {// ����д������������л�ʱ����������о�����Ϣ��ֻ���������Dog@25f38edc
        return "Dog{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

}
