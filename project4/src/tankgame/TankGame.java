package tankgame;

import javax.swing.JFrame;
import java.awt.event.*;
import java.util.Scanner;

public class TankGame extends JFrame {
    MyPanel mp = null; // 先声明一下这张画
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }

    public TankGame() {
        System.out.println("请输入选择  1：新游戏   2：继续上一局");
        String key = scanner.next();
        mp = new MyPanel(key);

        // 将mp放到thread中并启动线程
        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp); // 将mp这张画放到画框当中
        this.setSize(1300, 750);
        this.addKeyListener(mp); // 让JFrame监听mp的键盘事件
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置成点击叉号就退出程序

        this.addWindowListener(new WindowAdapter() { // 通过这堆东西来实现关闭窗口时候可以自动调用keepRecord方法
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
