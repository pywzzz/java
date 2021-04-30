package ���߳�;
/* ����ͨ���̳�Runnable�ӿڵķ�ʽ��ʵ�ֶ��߳� */
public class Thread02 {



    public static void main(String[] args) {
        Dog dog = new Dog();
        /* �����ʵ�ֽӿ���Ū���̵߳Ļ��������dog��û��start�����ģ�������dog.start()���ȥʵ�ֶ��߳� */

        /* �����ôȥŪ */
        Thread thread = new Thread(dog); // ��һ��ʵ����Runnable�ӿڵĶ��������dog���ŵ�Thread��
        thread.start(); // Ȼ��ȥ����start�����������߳�
/* 
*��Ϊ�����ȴ�������Ȼ���ٵ�start�����������ýӿڵķ�������ʵ����Դ��������һ�㶼�ýӿ�ʵ�ֶ��̣߳�
*�ͣ���ȥnew Thread�����ʱ�����������������ͬ��һ������������
*Thread thread1 = new Thread(dog);
*Thread thread2 = new Thread(dog);
*thread1.start();
*thread2.start();
 */
/* 
*Ҳ�ɣ�
*new Thread(dog).start();
*new Thread(dog).start();
 */
        /* ͨ���������ܹ�ȥʵ�������run�����ǣ�����ĵײ�ʹ���˸��о�̬��������ģʽ */

    }
}

class Dog implements Runnable {
    int count = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("nmmd" + (++count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

    }

}

/* ������˵˵�����̬����ģʽ */

class ThreadProxy implements Runnable { // ���������ThreadProxy��ģ��Thread�Ĺ������̣�����������ThreadProxy����Thread������

    private Runnable target = null;

    @Override
    public void run() {
        if (target != null) {
            target.run(); // ����� dog.run();
        }
    }

    public ThreadProxy(Runnable target) { // �����Ӧ����ģ�Thread thread = new Thread(dog);
        this.target = target;
    }

    public void start() { // ��Ӧ����ģ�thread.start();
        start0(); // start��������strat0����
    }

    public void start0() {
        run();

        /*
         * start0������ȥ��������Ū��run������
         * 
         * public void run() { if (target != null) { target.run(); } }
         */
    }

}