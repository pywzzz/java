package ReflecAccessProperty;

import java.lang.reflect.Field;

public class ReflecAccessProperty {
    public static void main(String[] args) throws Exception {
        // �Ȼ�ȡ��Student���Class����
        Class<?> stuClass = Class.forName("ReflecAccessProperty.Student");

        // ��������ʵ��
        Object o = stuClass.newInstance();

        // ���public����
        Field age = stuClass.getField("age"); // �õ�age�������
        age.set(o, 88);
        System.out.println(age.get(o)); // ���age���Ե�ֵ

        // ��÷�public����
        // ��Ϊ����˽�еģ����Ե���getDeclaredField()����������getField()��
        // getDeclaredField()���������ReflectionUtils�Ǹ����н��ܹ�����������õ����е���������
        Field name = stuClass.getDeclaredField("name");
        name.setAccessible(true);// ���÷�����Ա����ƽ⣬�Ӷ�����private����
        name.set(o, "sb"); // ��Ϊname���Ǹ�static���ͣ���������ĵ�һ������������Ϊnull
        System.out.println(name.get(o));// ��Ϊname���Ǹ�static���ͣ������������Ҳ����Ϊnull
    }
}

class Student {
    public int age;
    private static String name;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student [age=" + age + ",name=" + name + "]";
    }

}
