package 多线程;
//线程的死锁（尽量避免这种情况发生）

//就：A说，你给我钱我就杀你吗，B说，你杀我吗我就给你钱
//这就是死锁呃呃
public class Thread09 {
    public static void main(String[] args) {
        RR A = new RR(true);
        RR B = new RR(false);
        A.setName("A线程");
        B.setName("B线程");
        A.start();
        B.start();

    }
}

class RR extends Thread {
    static Object o1 = new Object(); // 保证多线程共享一个对象，所以这里使用static
    static Object o2 = new Object();
    boolean flag;

    public RR(boolean flag) {// 构造函数
        this.flag = flag;
    }

    @Override
    public void run() {
        // 1.如果flag为true，线程A就会先得到/持有 o1 对象锁，然后线程A会去进行下一步，就，尝试去获取 o2 对象锁
        // 2.如果线程A得不到 o2 对象锁，那它就会进入Blocked状态
        // 3.如果flag为true，线程A就会先得到/持有 o2 对象锁，然后线程A会去进行下一步，就，尝试去获取 o1 对象锁
        // 4.如果线程A得不到 o1 对象锁，那它就会进入Blocked状态
        if (flag) {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + " 进入1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " 进入2");
                }
            }
        } else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " 进入3");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + " 进入4");
                }
            }
        }
    }
}