package 多线程;

//线程的插队
public class Thread06 {
    public static void main(String[] args) throws InterruptedException {
        E e = new E();
        e.start();
        for (int i = 0; i < 20; i++) {
            Thread.sleep(1000);
            System.out.println("main线程 ==== " + i);
            if (i == 5) {
                System.out.println("-------------这时让子线程插队");
                // e.join(); // 让子线程插队，这个是恩插，一定成功
                Thread.yield();// 这个不一定了，这个是如果内存啥的不紧张，是插不进去的
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
            System.out.println("子线程 。。。 " + i);

        }
    }
}
