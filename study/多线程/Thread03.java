package 多线程;

/* 下面创造两个线程，一个线程每隔1s输出"aaa" ，执行10次；一个输bbb，5次*/
public class Thread03 {
    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();
        thread2.start();

    }
}

class T1 implements Runnable {
    int count = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("aaa" + (++count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (count == 10) {
                break;
            }
        }
    }

}

class T2 implements Runnable {
    int count = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("bbb" + (++count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (count == 5) {
                break;
            }
        }
    }

}
