package ���߳�;

//�̵߳Ĳ��
public class Thread06 {
    public static void main(String[] args) throws InterruptedException {
        E e = new E();
        e.start();
        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            System.out.println("main�߳� ==== " + i);
            if (i == 5) {
                System.out.println("-------------��ʱ�����̲߳��");
                // e.join(); // �����̲߳�ӣ�����Ƕ��壬һ���ɹ�
                Thread.yield();// �����һ���ˣ����������ڴ�ɶ�Ĳ����ţ��ǲ岻��ȥ��
            }
        }
    }
}

class E extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("���߳� ������ " + i);

        }
    }
}
