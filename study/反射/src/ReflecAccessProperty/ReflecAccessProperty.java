package ReflecAccessProperty;

import java.lang.reflect.Field;

public class ReflecAccessProperty {
    public static void main(String[] args) throws Exception {
        // 先获取到Student类的Class对象
        Class<?> stuClass = Class.forName("ReflecAccessProperty.Student");

        // 创建对象实例
        Object o = stuClass.newInstance();

        // 获得public属性
        Field age = stuClass.getField("age"); // 拿到age这个属性
        age.set(o, 88);
        System.out.println(age.get(o)); // 输出age属性的值

        // 获得非public属性
        // 因为这是私有的，所以得用getDeclaredField()方法而不用getField()了
        // getDeclaredField()这个方法在ReflectionUtils那个包中介绍过，这个可以拿到所有的属性来着
        Field name = stuClass.getDeclaredField("name");
        name.setAccessible(true);// 利用反射可以暴力破解，从而访问private属性
        name.set(o, "sb"); // 因为name还是个static类型，所以这儿的第一个参数还可以为null
        System.out.println(name.get(o));// 因为name还是个static类型，所以这儿参数也可以为null
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
