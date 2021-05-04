package Class01;

public class GetClass {
    public static void main(String[] args) throws Exception {

        // 1.����Class.forName�����
        String classAllPath = "Class01.Car";
        Class<?> cls1 = Class.forName(classAllPath);
        System.out.println(cls1);

        // 2.ͨ�� ����.class �������
        // �����ڲ������ݣ���֮ǰ�� Constructor constructor2 = cls.getConstructor(String.class);
        Class cls2 = Car.class;
        System.out.println(Car.class);

        // 3.ͨ�� ����.getClass() �������
        // �������Ѿ��ж���ʵ�����ڵ������
        Car car = new Car();
        Class cls3 = car.getClass();
        System.out.println(cls3);

        // 4.ͨ����������������
        ClassLoader classLoader = car.getClass().getClassLoader(); // �ȵõ�car���������
        Class cls4 = classLoader.loadClass(classAllPath);// ͨ����������õ�Class����
        System.out.println(cls4);

        // cls1��cls2��cls3��cls4��ʵ���Ǵ���ڶ��е�ͬһ������
        System.out.println(cls1.hashCode());
        System.out.println(cls2.hashCode());
        System.out.println(cls3.hashCode());
        System.out.println(cls4.hashCode());

        // 5.�����������ͣ�int��char��boolean��float��double��byete��long��short�������·����õ�Class�����
        Class<Integer> integerClass = int.class;
        Class<Character> characterClass = char.class;
        Class<Boolean> booleanClass = boolean.class;
        System.out.println(integerClass);
        System.out.println(characterClass);
        System.out.println(booleanClass);

        // 6.�����������͵İ�װ�����ͨ�� .TYPE �õ�Class�����
        Class<Integer> type1 = Integer.TYPE;
        Class<Character> type2 = Character.TYPE;
        Class<Boolean> type3 = Boolean.TYPE;
        System.out.println(type1);
        System.out.println(type2);
        System.out.println(type3);

        // ����ָ�����ͬһ��
        System.out.println(integerClass.hashCode());
        System.out.println(type1.hashCode());
        System.out.println(characterClass.hashCode());
        System.out.println(type2.hashCode());
        System.out.println(booleanClass.hashCode());
        System.out.println(type3.hashCode());
    }
}
