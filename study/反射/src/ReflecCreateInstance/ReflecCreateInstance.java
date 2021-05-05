package ReflecCreateInstance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflecCreateInstance {
    public static void main(String[] args) throws Exception {

        // 先获取到User类的Class对象
        Class<?> userClass = Class.forName("ReflecCreateInstance.User");

        // 通过public的无参构造器创建实例
        Object o1 = userClass.newInstance();
        System.out.println(o1);

        // 通过public的有参构造器创建实例
        Constructor<?> constructor1 = userClass.getConstructor(String.class); // 先去获得你要用到的构造器
        Object o2 = constructor1.newInstance("李四");// 然后再去创造实例，并传入实参
        System.out.println(o2);

        // 通过非public的有参构造器创建实例
        // 因为这是私有的，所以得用getDeclaredConstructor()方法而不用getConstructor()了
        // getDeclaredConstructor()这个方法在ReflectionUtils那个包中介绍过，这个可以拿到所有的构造器来着
        Constructor<?> constructor2 = userClass.getDeclaredConstructor(int.class, String.class);
        constructor2.setAccessible(true);// 利用反射可以暴力破解，从而访问private构造器
        Object o3 = constructor2.newInstance(100, "王五"); // 创建实例对象
        System.out.println(o3);
    }
}

class User {
    private int age = 10;
    private String name = "张三";

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    private User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [age=" + age + ", name=" + name + "]";
    }

}
