package ReflectionQuestion;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectionQuestion {
    public static void main(String[] args) throws Exception {

        // ��������������ļ�re.properties�е���Ϣ��ȥ����Cat���󲢵����䷽��hi

        // 1.һ�㷽��
        // Cat cat = new Cat();
        // cat.hi();

        // 2.���÷���
        // ����Properties��ȥ��ȡ������Ϣ
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/ReflectionQuestion/re.properties"));
        String classfullpath = properties.get("classfullpath").toString();
        String methodName = properties.get("method").toString();

        Class cls = Class.forName(classfullpath); // ��ȥ�����࣬����Class��Ķ���cls

        Object o = cls.newInstance(); // ͨ��cls���õ���Ҫ���ص��ࣨ�����ReflectionQuestion.Cat��Cat�ࣩ��ʵ������
        System.out.println(o.getClass());

        Method method1 = cls.getMethod(methodName);// ͨ��cls�õ�����ص��ࣨ��Cat���е�methodName��Ӧ�ķ��������ڷ����У�������Ҳ���ɶ���
        method1.invoke(o);// ���÷�������method1��Ӧ��hi�������

        // Field�����ʾĳ����Ա����
        Field namField = cls.getField("age");// getField�������ܻ�ȡ��˽������
        System.out.println(namField.get(o));

        // Constructor�����ʾĳ��������
        Constructor constructor1 = cls.getConstructor(); // ��������û�в�������õ�Ĭ�ϵĹ�����
        System.out.println(constructor1);
        Constructor constructor2 = cls.getConstructor(String.class); // �����мӲ������Ի��ָ���Ĺ�����
        System.out.println(constructor2);

    }
}
