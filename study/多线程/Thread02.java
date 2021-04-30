package 多线程;
/* 下面通过继承Runnable接口的方式来实现多线程 */
public class Thread02 {



    public static void main(String[] args) {
        Dog dog = new Dog();
        /* 但这儿实现接口来弄多线程的话，这儿的dog是没有start方法的，即不能dog.start()如此去实现多线程 */

        /* 你得这么去弄 */
        Thread thread = new Thread(dog); // 把一个实现了Runnable接口的对象（这儿即dog）放到Thread中
        thread.start(); // 然后去调用start方法开启多线程
/* 
*因为可以先创建对象然后再调start方法，所以用接口的方法可以实现资源共享（所以一般都用接口实现多线程）
*就，你去new Thread对象的时候，括号里面可以填相同的一个东西，即：
*Thread thread1 = new Thread(dog);
*Thread thread2 = new Thread(dog);
*thread1.start();
*thread2.start();
 */
/* 
*也可：
*new Thread(dog).start();
*new Thread(dog).start();
 */
        /* 通过这两步能够去实现下面的run方法是，这儿的底层使用了个叫静态代理的设计模式 */

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

/* 下面来说说这个静态代理模式 */

class ThreadProxy implements Runnable { // 这儿我们用ThreadProxy来模拟Thread的工作过程，下面的你就让ThreadProxy当成Thread来看吧

    private Runnable target = null;

    @Override
    public void run() {
        if (target != null) {
            target.run(); // 这儿即 dog.run();
        }
    }

    public ThreadProxy(Runnable target) { // 这儿对应上面的：Thread thread = new Thread(dog);
        this.target = target;
    }

    public void start() { // 对应上面的：thread.start();
        start0(); // start方法调用strat0方法
    }

    public void start0() {
        run();

        /*
         * start0方法再去调用上面弄的run方法：
         * 
         * public void run() { if (target != null) { target.run(); } }
         */
    }

}