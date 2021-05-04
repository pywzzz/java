package Reflection01;

import java.lang.reflect.Method;

public class Reflection01 {
    public static void main(String[] args) throws Exception {
        m1();
        m2();
    }

    // m1是一般方法调用
    public static void m1() {
        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            cat.hi();
        }
        long end = System.currentTimeMillis();
        System.out.println("一般方法耗时：" + (end - start));
    }

    // m2是利用反射机制去调用
    public static void m2() throws Exception {
        Class cls = Class.forName("Reflection01.Cat");
        Object o = cls.newInstance();
        Method hi = cls.getMethod("hi");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            hi.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("反射机制耗时：" + (end - start));
    }

}
