package ���߳�;
//������ܼ��ַ���
public class Thread05 {
    public static void main(String[] args) throws InterruptedException {
        Z t = new Z();
        t.setName("ʥ��Ȼ");
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000); 
            System.out.println("hi" + "..." + (i + 1));
        }
        System.out.println(t.getName() + "���߳����ȼ�Ϊ" + t.getPriority());
        t.interrupt(); // ǿ�д������
    }
}

class Z extends Thread {
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "..." + i);
            }
            System.out.println(Thread.currentThread().getName() + "..." + "����");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {// ����һ���ж��쳣��������ֹ��
                System.out.println(Thread.currentThread().getName() + "��interrupt��");
            }
        }
    }
}
