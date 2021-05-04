package Class01;

public class GetClass {
    public static void main(String[] args) throws Exception {

        // 1.利用Class.forName获得类
        String classAllPath = "Class01.Car";
        Class<?> cls1 = Class.forName(classAllPath);
        System.out.println(cls1);

        // 2.通过 类名.class 来获得类
        // 常用于参数传递：如之前的 Constructor constructor2 = cls.getConstructor(String.class);
        Class cls2 = Car.class;
        System.out.println(Car.class);

        // 3.通过 对象.getClass() 来获得类
        // 常用于已经有对象实例存在的情况下
        Car car = new Car();
        Class cls3 = car.getClass();
        System.out.println(cls3);

        // 4.通过类加载器来获得类
        ClassLoader classLoader = car.getClass().getClassLoader(); // 先得到car的类加载器
        Class cls4 = classLoader.loadClass(classAllPath);// 通过类加载器得到Class对象
        System.out.println(cls4);

        // cls1、cls2、cls3、cls4其实都是存放在堆中的同一个对象
        System.out.println(cls1.hashCode());
        System.out.println(cls2.hashCode());
        System.out.println(cls3.hashCode());
        System.out.println(cls4.hashCode());

        // 5.基本数据类型（int、char、boolean、float、double、byete、long、short）按如下方法得到Class类对象
        Class<Integer> integerClass = int.class;
        Class<Character> characterClass = char.class;
        Class<Boolean> booleanClass = boolean.class;
        System.out.println(integerClass);
        System.out.println(characterClass);
        System.out.println(booleanClass);

        // 6.基本数据类型的包装类可以通过 .TYPE 得到Class类对象
        Class<Integer> type1 = Integer.TYPE;
        Class<Character> type2 = Character.TYPE;
        Class<Boolean> type3 = Boolean.TYPE;
        System.out.println(type1);
        System.out.println(type2);
        System.out.println(type3);

        // 它们指向的是同一个
        System.out.println(integerClass.hashCode());
        System.out.println(type1.hashCode());
        System.out.println(characterClass.hashCode());
        System.out.println(type2.hashCode());
        System.out.println(booleanClass.hashCode());
        System.out.println(type3.hashCode());
    }
}
