package ReflecCreateInstance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflecCreateInstance {
    public static void main(String[] args) throws Exception {

        // �Ȼ�ȡ��User���Class����
        Class<?> userClass = Class.forName("ReflecCreateInstance.User");

        // ͨ��public���޲ι���������ʵ��
        Object o1 = userClass.newInstance();
        System.out.println(o1);

        // ͨ��public���вι���������ʵ��
        Constructor<?> constructor1 = userClass.getConstructor(String.class); // ��ȥ�����Ҫ�õ��Ĺ�����
        Object o2 = constructor1.newInstance("����");// Ȼ����ȥ����ʵ����������ʵ��
        System.out.println(o2);

        // ͨ����public���вι���������ʵ��
        // ��Ϊ����˽�еģ����Ե���getDeclaredConstructor()����������getConstructor()��
        // getDeclaredConstructor()���������ReflectionUtils�Ǹ����н��ܹ�����������õ����еĹ���������
        Constructor<?> constructor2 = userClass.getDeclaredConstructor(int.class, String.class);
        constructor2.setAccessible(true);// ���÷�����Ա����ƽ⣬�Ӷ�����private������
        Object o3 = constructor2.newInstance(100, "����"); // ����ʵ������
        System.out.println(o3);
    }
}

class User {
    private int age = 10;
    private String name = "����";

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
