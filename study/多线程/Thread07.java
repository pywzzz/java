package ���߳�;

/* 
*�û��̣߳�Ҳ�й����̡߳����̵߳�����ִ�������֪ͨ��ʽ����
*�ػ��̣߳�һ����Ϊ�û��̷߳���ġ��������û��߳̽���ʱ���ػ��߳��Զ������������ľ����������ջ���
 */

//���潫���߳�����Ϊ�ػ��߳�
public class Thread07 {
    public static void main(String[] args) throws InterruptedException {

        ZZ zz = new ZZ();
        zz.setDaemon(true); // ��zz����Ϊ�ػ��߳�
        zz.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("main������ִ��");
            Thread.sleep(1000);
        }
    }
}

class ZZ extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("���߳�����ִ�С�����");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
