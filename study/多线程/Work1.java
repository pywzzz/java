package ���߳�;

//����Q��Ȼ�󻹵ûس���Ȼ������˳����̣������ǵ���ֻ��һ��Q��
import java.util.Scanner;

public class Work1 {
    public static void main(String[] args) {

        Test1 test1 = new Test1();
        Test2 test2 = new Test2(test1);
        test1.start();
        test2.start();
    }
}

class Test1 extends Thread {

    private boolean loop = true;

    @Override
    public void run() {
        while (loop) {
            System.out.println((int) (Math.random() * 100 + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Test1�߳��˳�������");
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}

class Test2 extends Thread {
    private Test1 test1;
    private Scanner scanner = new Scanner(System.in);

    public Test2(Test1 test1) {
        this.test1 = test1;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("����Q�������̣�");
            char key = scanner.next().toUpperCase().charAt(0);
            if (key == 'Q') {
                test1.setLoop(false);
                System.out.println("Test2�߳��˳�������");
                break;
            }
        }
    }
}