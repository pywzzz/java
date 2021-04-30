package 多线程;
//这儿利用一个自己写的setLoop方法来退出进程
public class Thread04 {
    public static void main(String[] args) throws InterruptedException {
        T t1 = new T();
        t1.start();

        Thread.sleep(10000); // main线程先休眠10000ms（此时别的线程该干嘛干嘛）然后再去执下面的
        t1.setLoop(false);
    }
}

class T extends Thread {
    private int count = 0;
    private boolean loop = true;

    @Override
    public void run() {
        while (loop) {
            System.out.println("mark。。。" + (++count));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
