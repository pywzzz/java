package Class01;

import java.io.Serializable;

public class AllTypeClass {
    public static void main(String[] args) {
        Class<String> cls1 = String.class;// �ⲿ��
        Class<Serializable> cls2 = Serializable.class; // �ӿ�
        Class<Integer[]> cls3 = Integer[].class;// ����
        Class<float[][]> cls4 = float[][].class;// ��ά����
        Class<Deprecated> cls5 = Deprecated.class;// ע��
        Class<Thread.State> cls6 = Thread.State.class;// ö��
        Class<Long> cls7 = long.class;
        Class<Void> cls8 = void.class;
        Class<Class> cls9 = Class.class;
        System.out.println(cls1);
        System.out.println(cls2);
        System.out.println(cls3);
        System.out.println(cls4);
        System.out.println(cls5);
        System.out.println(cls6);
        System.out.println(cls7);
        System.out.println(cls8);
        System.out.println(cls9);
    }
}
