package ���߳�;

//�̵߳�����״̬
public class Thread08 {
    public static void main(String[] args) throws InterruptedException {
        SS ss = new SS();
        System.out.println(ss.getName() + "��״̬Ϊ" + ss.getState());
        ss.start();
        while (Thread.State.TERMINATED != ss.getState()) {// �����ǰ���ǡ���ֹ��״̬�������̻�û�н�����
            System.out.println(ss.getName() + "��״̬Ϊ" + ss.getState());
            Thread.sleep(500);
        }
        System.out.println(ss.getName() + "��״̬Ϊ" + ss.getState());
    }
}

class SS extends Thread {
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 10; i++) {
                System.out.println("hi  .." + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            break;
        }
    }
}
