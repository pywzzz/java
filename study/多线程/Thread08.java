package 多线程;

//线程的六种状态
public class Thread08 {
    public static void main(String[] args) throws InterruptedException {
        SS ss = new SS();
        System.out.println(ss.getName() + "的状态为" + ss.getState());
        ss.start();
        while (Thread.State.TERMINATED != ss.getState()) {// 如果当前不是“终止”状态（即进程还没有结束）
            System.out.println(ss.getName() + "的状态为" + ss.getState());
            Thread.sleep(500);
        }
        System.out.println(ss.getName() + "的状态为" + ss.getState());
    }
}

class SS extends Thread {
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 10; i++) {
                System.out.println("hi  .." + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            break;
        }
    }
}
