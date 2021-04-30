package ���߳�;
//�̵߳����������������������������

//�ͣ�A˵�������Ǯ�Ҿ�ɱ����B˵����ɱ�����Ҿ͸���Ǯ
//�������������
public class Thread09 {
    public static void main(String[] args) {
        RR A = new RR(true);
        RR B = new RR(false);
        A.setName("A�߳�");
        B.setName("B�߳�");
        A.start();
        B.start();

    }
}

class RR extends Thread {
    static Object o1 = new Object(); // ��֤���̹߳���һ��������������ʹ��static
    static Object o2 = new Object();
    boolean flag;

    public RR(boolean flag) {// ���캯��
        this.flag = flag;
    }

    @Override
    public void run() {
        // 1.���flagΪtrue���߳�A�ͻ��ȵõ�/���� o1 ��������Ȼ���߳�A��ȥ������һ�����ͣ�����ȥ��ȡ o2 ������
        // 2.����߳�A�ò��� o2 �������������ͻ����Blocked״̬
        // 3.���flagΪtrue���߳�A�ͻ��ȵõ�/���� o2 ��������Ȼ���߳�A��ȥ������һ�����ͣ�����ȥ��ȡ o1 ������
        // 4.����߳�A�ò��� o1 �������������ͻ����Blocked״̬
        if (flag) {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + " ����1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " ����2");
                }
            }
        } else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " ����3");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + " ����4");
                }
            }
        }
    }
}