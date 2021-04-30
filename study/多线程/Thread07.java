package 多线程;

/* 
*用户线程：也叫工作线程。当线程的任务执行完或者通知方式结束
*守护线程：一般视为用户线程服务的。当所有用户线程结束时，守护线程自动结束。常见的就是垃圾回收机制
 */

//下面将子线程设置为守护线程
public class Thread07 {
    public static void main(String[] args) throws InterruptedException {

        ZZ zz = new ZZ();
        zz.setDaemon(true); // 将zz设置为守护线程
        zz.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("main进程在执行");
            Thread.sleep(1000);
        }
    }
}

class ZZ extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("子线程正在执行。。。");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
