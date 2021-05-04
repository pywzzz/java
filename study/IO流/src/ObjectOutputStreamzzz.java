import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectOutputStreamzzz {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // 序列化后，保存的文件格式，不是纯文本的，而是按照它的格式来保存的
        // 这儿的这个文件后缀是任意的（不加后缀也行）
        String filePath = "file/ObjectXXputStream/news.nmsl";

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));

        oos.writeInt(100); // writeInt方法会让int自动转为包装类Integer（Integer实现了Serializable接口，所以可以序列化）
        oos.writeBoolean(true);// writeBoolean方法会让boolean自动转为包装类Boolean（Boolean实现了Serializable接口，所以可以序列化）
        oos.writeChar('A');// writeChar方法会让char自动转为包装类Char（Char实现了Serializable接口，所以可以序列化）
        oos.writeDouble(9.5);// writeDouble方法会让double自动转为包装类Double（Double实现了Serializable接口，所以可以序列化）
        oos.writeUTF("你麻麻死啦");// String实现了Serializable接口，所以可以序列化
        oos.writeObject(new Dog("刘家豪", 10)); // 保存一个对象
        oos.close();

    }
}

class Dog implements Serializable { // 要想序列化，就得去实现Serializable这个接口
    private String name;
    private int age;

    public Dog(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {// 不重写这个方法则反序列话时不会输出类中具体信息，只会输出个：Dog@25f38edc
        return "Dog{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

}
