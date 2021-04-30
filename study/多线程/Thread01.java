package 多线程;
/* 下面通过继承Thread类的方式来实现多线程 */
public class Thread01 {

/* 
*打开进程后，进程会先开启一个叫main的线程（即main方法对应的线程），main线程会去执行你自己创的线程云云
*main线程如果执行(main线程的任务就是执行main方法中的东西云)完毕的话，main线程创建的那些线程不会停止，虽然是在main线程的基础上创建的别的线程，但是mian跟它们是不相干的
*即main执行完之后，剩下的线程仍然会执行
*程序会在所有线程都执行完毕后结束 
*/

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.start(); // 启动线程之后，它会自动调用run方法

        /*
         * 这儿只能是调用start方法啊，如果你是cat.run()的话，这就不是一个多线程了，这时你就就纯纯地把run看成一个普通方法去执行来着
         * 只有通过start方法间接调用run的话，才能有一个多线程的效果
         */

        /*
         * 在start方法的源码中，start会去调用start0方法，这个start0方法是个底层的方法，JVM虚拟器来调用start0这个方法，通过c语言实现        
         * start0方法用多线程的机制去调用你写出来的run()方法
         * 所以真正实现实多线程的效果是通过start0方法来实现的，而不是你写出来的那个run方法         *
         */
    }
}


// Thread中的run方法其实也不是Thread自带的，而是它实现Runnable接口弄出来的
class Cat extends Thread {
    int times = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("nmmsl" + (++times));
            try {
                Thread.sleep(1000); // 让线程休眠1000ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (times == 8) {
                break;// 当times=80，即执行了80次时，就退出这个while循环，这时线程也就退出

            }
        }
    }
}
