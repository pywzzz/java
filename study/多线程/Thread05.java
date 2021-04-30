package 多线程;
//这儿介绍几种方法
public class Thread05 {
    public static void main(String[] args) throws InterruptedException {
        Z t = new Z();
        t.setName("圣嘉然");
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000); 
            System.out.println("hi" + "..." + (i + 1));
        }
        System.out.println(t.getName() + "的线程优先级为" + t.getPriority());
        t.interrupt(); // 强行打断休眠
    }
}

class Z extends Thread {
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "..." + i);
            }
            System.out.println(Thread.currentThread().getName() + "..." + "休眠");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {// 捕获到一个中断异常（不是中止）
                System.out.println(Thread.currentThread().getName() + "被interrupt了");
            }
        }
    }
}
