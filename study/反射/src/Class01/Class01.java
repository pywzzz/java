package Class01;

import java.lang.reflect.Field;

public class Class01 {
    public static void main(String[] args) throws Exception {
        String classAllPath = "Class01.Car";

        // 获取到Car类对应的Class对象
        // 这个 <?> 表示不确定的Java类型（不加上这个也没事儿）
        Class<?> cls = Class.forName(classAllPath);

        System.out.println(cls);// 输出cls所属Class对象的名字
        System.out.println(cls.getClass());// 输出cls的运行类型
        System.out.println(cls.getPackage().getName());// 输出cls所在的包的名字
        System.out.println(cls.getName()); // 输出cls所属类的全类名

        // 通过cls创建一个对象的实例
        Car car = (Car) cls.newInstance();

        // 通过反射获取指定的公有属性
        Field brand = cls.getField("brand");
        System.out.println(brand.get(car));

        // 通过反射给指定的属性去赋值
        brand.set(car, "BMW");
        System.out.println(brand.get(car));

        // 通过反射获得所有的属性
        Field[] fields = cls.getFields();
        for (Field f : fields) {
            System.out.println(f.getName());
        }

    }
}
