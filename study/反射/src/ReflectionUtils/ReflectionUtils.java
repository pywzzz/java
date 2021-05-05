package ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static void main(String[] args) throws Exception {
        System.out.println("test");
    }

    public static void api1() throws Exception {
        Class<?> personCls = Class.forName("ReflectionUtils.Person");
        System.out.println("---------------------------");
        // �õ�ȫ����
        System.out.println(personCls.getName());
        System.out.println("---------------------------");

        // ��ȡ������
        System.out.println(personCls.getSimpleName());
        System.out.println("---------------------------");

        // �������public���ε����ԣ��������໹�и���
        Field[] fields = personCls.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("---------------------------");

        // ��ȡ���������е�����
        Field[] declarFields = personCls.getDeclaredFields();
        for (Field declarField : declarFields) {
            System.out.println(declarField.getName());
        }
        System.out.println("---------------------------");

        // ��ȡ����public���εķ��������������Լ������
        Method[] methods = personCls.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println("---------------------------");

        // ��ȡ���������еķ���
        Method[] declaredMethods = personCls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
        System.out.println("---------------------------");

        // ��ȡ����������public���εĹ�����
        Constructor<?>[] constructors = personCls.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor.getName());
        }
        System.out.println("---------------------------");

        // ��ȡ���������й�����
        Constructor<?>[] declaredConstructors = personCls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor.getName());
        }
        System.out.println("---------------------------");

        // ��Package��ʽ�������ڰ�����Ϣ
        System.out.println(personCls.getPackage());
        System.out.println("---------------------------");

        // ��Class��ʽ���ظ�����Ϣ
        Class<?> superClass = personCls.getSuperclass();
        System.out.println(superClass);
        System.out.println("---------------------------");

        // ��Class[]��ʽ���ؽӿ���Ϣ
        Class<?>[] interfaces = personCls.getInterfaces();
        for (Class<?> aa : interfaces) {
            System.out.println(aa);
        }
        System.out.println("---------------------------");

        // ��Annotation[]��ʽ����ע����Ϣ
        Annotation[] annotations = personCls.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }

    public static void api2() throws Exception {
        Class<?> personCls = Class.forName("ReflectionUtils.Person");

        // getDeclaredFields()����ȡ��������������
        // getModifiers()����int��ʽ�������η���Ĭ�����η�Ϊ0��publicΪ1��privateΪ2��protectedΪ4��staticΪ8��finalΪ16�������������η������򷵻ص�ֵΪ���Ǹ��Զ�Ӧ��ֵ����ӣ�
        // getModifiers()������������Ե����Ͷ�Ӧ��Class����
        Field[] declaredFields = personCls.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.print(field.getName() + "������Ե����η�ֵΪ��" + field.getModifiers());
            System.out.println("�����������Ͷ�Ӧ��Class����Ϊ��" + field.getType());
        }

        System.out.println("---------------------------");

        // getModifiers()����int��ʽ�������η���Ĭ�����η�Ϊ0��publicΪ1��privateΪ2��protectedΪ4��staticΪ8��finalΪ16�������������η������򷵻ص�ֵΪ���Ǹ��Զ�Ӧ��ֵ����ӣ�
        // getReturnType()����������������ص����Ͷ�Ӧ��Class����
        Method[] declaredMethods = personCls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.print(declaredMethod.getName() + "������������η�ֵΪ��" + declaredMethod.getModifiers());
            System.out.println("���Ҹ÷������ص����Ͷ�Ӧ��Class����Ϊ��" + declaredMethod.getReturnType());
        }

        System.out.println("---------------------------");

        // getParameterTypes()����Class[]����ʽ������������βε����Ͷ�Ӧ��Class��������β�Ϊ����ɶҲ�������
        for (Method declaredMethod : declaredMethods) {
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println(declaredMethod.getName() + "����������β�Ϊ��" + parameterType);
            }
        }

        System.out.println("---------------------------");

        // getModifiers()����int��ʽ�������η���Ĭ�����η�Ϊ0��publicΪ1��privateΪ2��protectedΪ4��staticΪ8��finalΪ16�������������η������򷵻ص�ֵΪ���Ǹ��Զ�Ӧ��ֵ����ӣ�

        Constructor<?>[] declaredConstructors = personCls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor.getName());
        }
    }

    public static void api3() throws Exception {
        Class<?> personCls = Class.forName("ReflectionUtils.Person");

        // getModifiers()����int��ʽ�������η���Ĭ�����η�Ϊ0��publicΪ1��privateΪ2��protectedΪ4��staticΪ8��finalΪ16�������������η������򷵻ص�ֵΪ���Ǹ��Զ�Ӧ��ֵ����ӣ�
        // getParameterTypes()����Class[]����ʽ��������������βε����Ͷ�Ӧ��Class��������β�Ϊ����ɶҲ�������
        Constructor<?>[] declaredConstructors = personCls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor.getName() + "������������βε����Ͷ�Ӧ��Class����Ϊ��");
            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println(parameterType);
            }
            System.out.println();
        }
    }

}

interface IA {
}

interface IB {
}

class A {
    public String hobby;

    public void hi() {

    }

    public A() {
    }
}

@Deprecated
class Person extends A implements IA, IB {
    public String name;
    protected static int age;
    String job;
    private double sal;

    public Person() {
    }

    public Person(String name) {
    }

    private Person(String name, int age) {
    }

    public void m1(String name, int age, double sal) {

    }

    protected String m2() {
        return null;
    }

    void m3() {

    }

    private void m4() {

    }
}
