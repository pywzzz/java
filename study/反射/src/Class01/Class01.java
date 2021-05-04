package Class01;

import java.lang.reflect.Field;

public class Class01 {
    public static void main(String[] args) throws Exception {
        String classAllPath = "Class01.Car";

        // ��ȡ��Car���Ӧ��Class����
        // ��� <?> ��ʾ��ȷ����Java���ͣ����������Ҳû�¶���
        Class<?> cls = Class.forName(classAllPath);

        System.out.println(cls);// ���cls����Class���������
        System.out.println(cls.getClass());// ���cls����������
        System.out.println(cls.getPackage().getName());// ���cls���ڵİ�������
        System.out.println(cls.getName()); // ���cls�������ȫ����

        // ͨ��cls����һ�������ʵ��
        Car car = (Car) cls.newInstance();

        // ͨ�������ȡָ���Ĺ�������
        Field brand = cls.getField("brand");
        System.out.println(brand.get(car));

        // ͨ�������ָ��������ȥ��ֵ
        brand.set(car, "BMW");
        System.out.println(brand.get(car));

        // ͨ�����������е�����
        Field[] fields = cls.getFields();
        for (Field f : fields) {
            System.out.println(f.getName());
        }

    }
}
