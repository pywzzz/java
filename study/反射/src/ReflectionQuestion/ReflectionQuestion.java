package ReflectionQuestion;

import java.io.FileInputStream;

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
    }
}
