package ReflecAccessMethod;

import java.lang.reflect.Method;

public class ReflecAccessMethod {
    public static void main(String[] args) throws Exception {
        // �Ȼ�ȡ��User���Class����
        Class<?> bossCls = Class.forName("ReflecAccessMethod.Boss");

        // ��������ʵ��
        Object o = bossCls.newInstance();

        // ����public����
        Method hi = bossCls.getMethod("hi", String.class);// ��÷�����Ӧ�Ķ���
        hi.invoke(o, "test������"); // ����

        // ���÷�public����
        // ��Ϊ����˽�еģ����Ե���getDeclaredMethod()����������getMethod()��
        // getDeclaredMethod()���������ReflectionUtils�Ǹ����н��ܹ�����������õ����е���������
        Method say = bossCls.getDeclaredMethod("say", int.class, String.class, char.class);
        say.setAccessible(true);// ���÷�����Ա����ƽ⣬�Ӷ�����private����
        System.out.println(say.invoke(o, 6324, "��Ȼ", 'Ů')); // ����ʱ����Ϊ���Ǹ�static���������Ե�һ����������Ϊnull

        // �ڷ����У���������з���ֵ����ô���ǵķ���ֵͬһ����Object�����������Ͷ���Object�����գ�
        Object returnVal = say.invoke(o, 6324, "��Ȼ", 'Ů');
        System.out.println(returnVal.getClass());// �������е����ͻ��Ǻͷ�������ķ���������һ�µ�
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
