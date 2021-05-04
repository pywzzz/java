import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputStreamzzz {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        String filePath = "file/ObjectXXputStream/news.nmsl";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));

        // 读取的顺序要和你序列化的顺序一致，否则会出Exception
        System.out.println(ois.readInt());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readChar());
        System.out.println(ois.readDouble());
        System.out.println(ois.readUTF());

        // 下面那两行也可： System.out.println(ois.readObject());
        Object dog = ois.readObject();
        System.out.println(dog);

        // 如果我们想调用类的方法时候，如这儿想调用Dog类中的getName方法

        ois.close(); // 记得关闭。以免浪费资源
    }
}
