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

    // 下面用一般方法来读取mysql.properties这个文件，并得到ip、user、pwd
    public static void simple1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("file/Properties/mysql.properties"));
        String line = "";
        while ((line = br.readLine()) != null) {// 因为你不知道具体要读什么。所以去循环读取
            String[] split = line.split("=");
            System.out.println(split[0] + "的值为：" + split[1]);
        }
        br.close();
    }

    // 下面用Properties类来读取mysql.properties这个文件
    public static void simple2() throws FileNotFoundException, IOException {
        // Properties类是个专门用于读写配置文件的集合类
        // 它读取的配置文件要有专门的格式：键=值
        // 其中键值对不需要有空格，值不需要引号括起来（默认类型是String）
        Properties properties = new Properties();
        properties.load(new FileReader("file/Properties/mysql.properties"));// 加载指定的配置文件
        properties.list(System.out);// 把键值对显示到屏幕
        String user = properties.getProperty("user");// 根据键获得对应的值
        System.out.println("test。。。" + user);
    }

    // 下面甩Properties类来创建配置文件并修改其内容
    public static void simple3() throws FileNotFoundException, IOException {
        Properties properties = new Properties();

        // setProperty这个方法，如果键是不存在，那就是新建这个键值对；如果不存在，那就是修改的效果
        properties.setProperty("用户user", "刘家豪");// 存储时会把中文转换成其对应的unicode码值
        properties.setProperty("pwd", "ac2312");

        // 将上面你弄的存储到指定文件当中
        properties.store(new FileOutputStream("file/Properties/test.properties"), "这是一个注释");// 第二个参数可以为null

    }
}
