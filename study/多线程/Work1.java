package 多线程;

//按下Q，然后还得回车，然后才能退出进程（而不是单单只按一下Q）
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
        System.out.println("Test1线程退出。。。");
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
            System.out.println("输入Q结束进程：");
            char key = scanner.next().toUpperCase().charAt(0);
            if (key == 'Q') {
                test1.setLoop(false);
                System.out.println("Test2线程退出。。。");
                break;
            }
        }
    }
}