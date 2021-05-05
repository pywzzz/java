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
        // 得到全类名
        System.out.println(personCls.getName());
        System.out.println("---------------------------");

        // 获取简单类名
        System.out.println(personCls.getSimpleName());
        System.out.println("---------------------------");

        // 获得所有public修饰的属性，包含本类还有父类
        Field[] fields = personCls.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("---------------------------");

        // 获取本类中所有的属性
        Field[] declarFields = personCls.getDeclaredFields();
        for (Field declarField : declarFields) {
            System.out.println(declarField.getName());
        }
        System.out.println("---------------------------");

        // 获取所有public修饰的方法，包含本类以及父类的
        Method[] methods = personCls.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println("---------------------------");

        // 获取本类中所有的方法
        Method[] declaredMethods = personCls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
        System.out.println("---------------------------");

        // 获取本类中所有public修饰的构造器
        Constructor<?>[] constructors = personCls.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor.getName());
        }
        System.out.println("---------------------------");

        // 获取本类中所有构造器
        Constructor<?>[] declaredConstructors = personCls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor.getName());
        }
        System.out.println("---------------------------");

        // 以Package形式返回所在包的信息
        System.out.println(personCls.getPackage());
        System.out.println("---------------------------");

        // 以Class形式返回父类信息
        Class<?> superClass = personCls.getSuperclass();
        System.out.println(superClass);
        System.out.println("---------------------------");

        // 以Class[]形式返回接口信息
        Class<?>[] interfaces = personCls.getInterfaces();
        for (Class<?> aa : interfaces) {
            System.out.println(aa);
        }
        System.out.println("---------------------------");

        // 以Annotation[]形式返回注解信息
        Annotation[] annotations = personCls.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }

    public static void api2() throws Exception {
        Class<?> personCls = Class.forName("ReflectionUtils.Person");

        // getDeclaredFields()：获取本类中所有属性
        // getModifiers()：以int形式返回修饰符。默认修饰符为0，public为1，private为2，protected为4，static为8，final为16（如果被多个修饰符修饰则返回的值为它们各自对应的值的相加）
        // getModifiers()：返回这个属性的类型对应的Class对象
        Field[] declaredFields = personCls.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.print(field.getName() + "这个属性的修饰符值为：" + field.getModifiers());
            System.out.println("，且它的类型对应的Class对象为：" + field.getType());
        }

        System.out.println("---------------------------");

        // getModifiers()：以int形式返回修饰符。默认修饰符为0，public为1，private为2，protected为4，static为8，final为16（如果被多个修饰符修饰则返回的值为它们各自对应的值的相加）
        // getReturnType()：返回这个方法返回的类型对应的Class对象
        Method[] declaredMethods = personCls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.print(declaredMethod.getName() + "这个方法的修饰符值为：" + declaredMethod.getModifiers());
            System.out.println("，且该方法返回的类型对应的Class对象为：" + declaredMethod.getReturnType());
        }

        System.out.println("---------------------------");

        // getParameterTypes()：以Class[]的形式返回这个方法形参的类型对应的Class对象（如果形参为空则啥也不输出）
        for (Method declaredMethod : declaredMethods) {
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println(declaredMethod.getName() + "这个方法的形参为：" + parameterType);
            }
        }

        System.out.println("---------------------------");

        // getModifiers()：以int形式返回修饰符。默认修饰符为0，public为1，private为2，protected为4，static为8，final为16（如果被多个修饰符修饰则返回的值为它们各自对应的值的相加）

        Constructor<?>[] declaredConstructors = personCls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor.getName());
        }
    }

    public static void api3() throws Exception {
        Class<?> personCls = Class.forName("ReflectionUtils.Person");

        // getModifiers()：以int形式返回修饰符。默认修饰符为0，public为1，private为2，protected为4，static为8，final为16（如果被多个修饰符修饰则返回的值为它们各自对应的值的相加）
        // getParameterTypes()：以Class[]的形式返回这个构造器形参的类型对应的Class对象（如果形参为空则啥也不输出）
        Constructor<?>[] declaredConstructors = personCls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor.getName() + "这个构造器的形参的类型对应的Class对象为：");
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
