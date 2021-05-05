package ReflecAccessMethod;

import java.lang.reflect.Method;

public class ReflecAccessMethod {
    public static void main(String[] args) throws Exception {
        // 先获取到User类的Class对象
        Class<?> bossCls = Class.forName("ReflecAccessMethod.Boss");

        // 创建对象实例
        Object o = bossCls.newInstance();

        // 调用public方法
        Method hi = bossCls.getMethod("hi", String.class);// 获得方法对应的对象
        hi.invoke(o, "test。。。"); // 调用

        // 调用非public方法
        // 因为这是私有的，所以得用getDeclaredMethod()方法而不用getMethod()了
        // getDeclaredMethod()这个方法在ReflectionUtils那个包中介绍过，这个可以拿到所有的属性来着
        Method say = bossCls.getDeclaredMethod("say", int.class, String.class, char.class);
        say.setAccessible(true);// 利用反射可以暴力破解，从而访问private方法
        System.out.println(say.invoke(o, 6324, "嘉然", '女')); // 调用时，因为这是个static方法，所以第一个参数可以为null

        // 在反射中，如果方法有返回值，那么它们的返回值同一都是Object（即编译类型都由Object来接收）
        Object returnVal = say.invoke(o, 6324, "嘉然", '女');
        System.out.println(returnVal.getClass());// 但是运行的类型还是和方法定义的返回类型是一致的
    }
}

class Boss {
    public int age;
    private static String name;

    public Boss() {
    }

    private static String say(int n, String s, char c) {
        return n + " " + s + " " + c;
    }

    public void hi(String s) {
        System.out.println(s);
    }

}
