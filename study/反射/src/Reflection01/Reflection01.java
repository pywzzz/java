package Reflection01;

import java.lang.reflect.Method;

public class Reflection01 {
    public static void main(String[] args) throws Exception {
        m1();
        m2();
        m3();
    }

    // m1��һ�㷽������
    public static void m1() {
        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            cat.hi();
        }
        long end = System.currentTimeMillis();
        System.out.println("һ�㷽����ʱ��" + (end - start));
    }

    // m2�����÷������ȥ����
    public static void m2() throws Exception {
        Class cls = Class.forName("Reflection01.Cat");
        Object o = cls.newInstance();
        Method hi = cls.getMethod("hi");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            hi.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("������ƺ�ʱ��" + (end - start));
    }

    // m3���Ż�������÷������ȥ����
    public static void m3() throws Exception {
        Class cls = Class.forName("Reflection01.Cat");
        Object o = cls.newInstance();
        Method hi = cls.getMethod("hi");
        hi.setAccessible(true); // ȡ�������÷������ȥ���÷���ʱ�������ķ��ʼ��Ӷ����Ч��
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            hi.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("�Ż�������ƺ�ʱ��" + (end - start));
    }

}
