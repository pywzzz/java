package ���߳�;
/* ����ͨ���̳�Thread��ķ�ʽ��ʵ�ֶ��߳� */
public class Thread01 {

/* 
*�򿪽��̺󣬽��̻��ȿ���һ����main���̣߳���main������Ӧ���̣߳���main�̻߳�ȥִ�����Լ������߳�����
*main�߳����ִ��(main�̵߳��������ִ��main�����еĶ�����)��ϵĻ���main�̴߳�������Щ�̲߳���ֹͣ����Ȼ����main�̵߳Ļ����ϴ����ı���̣߳�����mian�������ǲ���ɵ�
*��mainִ����֮��ʣ�µ��߳���Ȼ��ִ��
*������������̶߳�ִ����Ϻ���� 
*/

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.start(); // �����߳�֮�������Զ�����run����

        /*
         * ���ֻ���ǵ���start���������������cat.run()�Ļ�����Ͳ���һ�����߳��ˣ���ʱ��;ʹ����ذ�run����һ����ͨ����ȥִ������
         * ֻ��ͨ��start������ӵ���run�Ļ���������һ�����̵߳�Ч��
         */

        /*
         * ��start������Դ���У�start��ȥ����start0���������start0�����Ǹ��ײ�ķ�����JVM������������start0���������ͨ��c����ʵ��        
         * start0�����ö��̵߳Ļ���ȥ������д������run()����
         * ��������ʵ��ʵ���̵߳�Ч����ͨ��start0������ʵ�ֵģ���������д�������Ǹ�run����         *
         */
    }
}


// Thread�е�run������ʵҲ����Thread�Դ��ģ�������ʵ��Runnable�ӿ�Ū������
class Cat extends Thread {
    int times = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("nmmsl" + (++times));
            try {
                Thread.sleep(1000); // ���߳�����1000ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (times == 8) {
                break;// ��times=80����ִ����80��ʱ�����˳����whileѭ������ʱ�߳�Ҳ���˳�

            }
        }
    }
}
