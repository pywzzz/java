package 多线程;

/* 
*线程同步机制：
*即当有一个线程在对内存（也即数据）进行操作时，其他线程都不可以对这个内存地址进行操作，直至该线程完成曹邹，其他线程才可以对这个内存地址进行操作

*同步有许多实现方法，其中一种就是加互斥锁synchronized
*这个东西的缺点就是会导致代码的执行效率变低
*/

/* 
*下面几种操作会释放进程拿到的这个锁：
*1.当前线程的同步方法、同步代码块执行结束（上厕所，上完出来）
*2.当前线程在同步方法、同步代码块遇到break、return（期间有人叫你干的事情，没有正常上完，不得已出来）
*3.当前线程的同步方法、同步代码块出现了未处理的Error或Exception，导致异常结束（期间没带纸，没有正常上完，不得已出来）
*4.当前线程的同步方法、同步代码块执行了线程对象的wait方法，当前线程暂停并释放锁（觉得需要酝酿下，没有正常上完，不得已出来）（一会还会进去）

*下面几种操作不会释放锁：
*1.当前线程在执行同步方法、同步代码块时，程序调用了sleep、yield方法暂停当前线程的执行，该线程不会释放锁（在里面瞌睡了，没有出来）
*2.当前线程执行同步代码块时，其他线程调用了该线程的suspend方法将该线程挂起，该线程不会释放锁
*对了，suspend跟resume这俩方法都被抛弃了呃呃，狗都不用
 */

public class SellTicket {
    public static void main(String[] args) {

        // 这两种方法都会出现票数为负数的情况
        // 导致这种状况的原因就是，3个线程同时去if的条件，但如果就剩1张的话，它们三个还是同时去进sell方法中的if，这时候还都通过了if，所以负数了

        /* 用继承的方式 */
        // SellTicket01 sellTicket01 = new SellTicket01();
        // SellTicket01 sellTicket02 = new SellTicket01();
        // SellTicket01 sellTicket03 = new SellTicket01();
        // sellTicket01.start();
        // sellTicket02.start();
        // sellTicket03.start();

        /* 用接口的方式 */
        // SellTicket02 sellTicket02 = new SellTicket02();
        // new Thread(sellTicket02).start();
        // new Thread(sellTicket02).start();
        // new Thread(sellTicket02).start();

        /* 用同步的方式 */
        SellTicket03 sellTicket03 = new SellTicket03();
        new Thread(sellTicket03).start();
        new Thread(sellTicket03).start();
        new Thread(sellTicket03).start();
    }
}

class SellTicket01 extends Thread {
    private static int ticketNum = 100; // 一共100张票（加个static以到达共享的效果）

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                System.out.println("售票结束");
                break;
            }
            try {
                Thread.sleep(50); // 要是没卖完票，就每隔50ms卖一张
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("窗口" + Thread.currentThread().getName() + "卖出一张票" + "  剩余票数=" + (--ticketNum));
        }
    }
}

class SellTicket02 implements Runnable {
    private int ticketNum = 100; // 一共100张票（这儿就不用去static了，因为接口这种方法可以去共享线程）

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                System.out.println("售票结束");
                break;
            }
            try {
                Thread.sleep(50); // 要是没卖完票，就每隔50ms卖一张
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("窗口" + Thread.currentThread().getName() + "卖出一张票" + "  剩余票数=" + (--ticketNum));
        }
    }
}

// 在方法上加互斥锁
class SellTicket03 implements Runnable {
    private int ticketNum = 100; // 一共100张票（这儿就不用去static了，因为接口这种方法可以去共享线程）
    private boolean loop = true;

    public synchronized void sell() {// 给这个方法加互斥锁。此时，在同一时刻，只能有一个线程来执行sell方法
        if (ticketNum <= 0) {
            System.out.println("售票结束");
            loop = false;
            return;
        }
        try {
            Thread.sleep(50); // 要是没卖完票，就每隔50ms卖一张
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("窗口" + Thread.currentThread().getName() + "卖出一张票" + "  剩余票数=" + (--ticketNum));
    }

    @Override
    public void run() {
        while (loop) {
            sell(); // sell方法是一个同步方法
        }
    }
}

// 在代码块上加互斥锁
class SellTicket04 implements Runnable {
    private int ticketNum = 100; // 一共100张票（这儿就不用去static了，因为接口这种方法可以去共享线程）
    private boolean loop = true;

    public void sell() {

        synchronized (this) {// 这儿的sell是一个实例方法，这儿可以是this，也可以是其他对象（但都要求多个线程的锁对象为同一个）
            // 就，如果你锁对象不是同一个的话，就不能达到一堆线程去抢同一把锁这样的效果了
            // 如把这儿的this改成new Object() 的话，就又出现票数为负的情况了
            /*
             * 比如这儿也可把this换一下： Object object = new Object(); synchronized (object) {...}
             */
            if (ticketNum <= 0) {
                System.out.println("售票结束");
                loop = false;
                return;
            }
            try {
                Thread.sleep(50); // 要是没卖完票，就每隔50ms卖一张
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("窗口" + Thread.currentThread().getName() + "卖出一张票" + "  剩余票数=" + (--ticketNum));
        }

    }

    /*
     * 但是如果是类方法的话，在方法上加互斥锁和实例方法格式一样：public synchronized static void mm(){...}
     * 但是如果加载代码块中的，括号里面不能加this了，而只能是当前类本身 即：synchronized (SellTicket04.class) {...}
     */

    @Override
    public void run() {
        while (loop) {
            sell();
        }
    }
}
