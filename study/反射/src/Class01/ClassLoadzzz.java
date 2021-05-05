package Class01;

import java.lang.reflect.Method;
import java.util.Scanner;

public class ClassLoadzzz {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入key：");
        String key = scanner.next();
        switch (key) {
            case "1":
                Dog dog = new Dog();
                break;
            case "2":
                System.out.println("test1");
                break;
            case "3":
                Class cls = Class.forName("Person"); // 加载个叫Person的类
                Object o = cls.newInstance(); //
                Method m = cls.getMethods("hi");
                m.invoke(o);
                break;
            default:
                System.out.println("test2");
                break;
        }
    }
}
