package ReflectionQuestion;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectionQuestion {
    public static void main(String[] args) throws Exception {

        // 我们想根据配置文件re.properties中的信息，去创建Cat对象并调用其方法hi

        // 1.一般方法
        // Cat cat = new Cat();
        // cat.hi();

        // 2.利用反射
        // 先用Properties类去读取配置信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/ReflectionQuestion/re.properties"));
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();

        Class cls = Class.forName(classfullpath); // 再去加载类，返回Class类的对象cls

        Object o = cls.newInstance(); // 通过cls来得到你要加载的类（这儿即ReflectionQuestion.Cat的Cat类）的实例对象
        System.out.println(o.getClass());

        Method method1 = cls.getMethod(methodName);// 通过cls得到你加载的类（即Cat）中的methodName对应的方法对象（在反射中，将方法也当成对象）
        method1.invoke(o);// 调用方法对象method1对应的hi这个方法

        // Field对象表示某个成员变量
        Field namField = cls.getField("age");// getField方法不能获取到私有属性
        System.out.println(namField.get(o));

        // Constructor对象表示某个构造器
        Constructor constructor1 = cls.getConstructor(); // 若括号中没有参数，则得到默认的构造器
        System.out.println(constructor1);
        Constructor constructor2 = cls.getConstructor(String.class); // 括号中加参数可以获得指定的构造器
        System.out.println(constructor2);

    }
}
