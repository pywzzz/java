package ui;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    BufferedImage bg = ImageUtil.getImg("/img/background/背景1.jpg");
    Mario mario = new Mario();
    List<Mushroom> ms = new ArrayList<Mushroom>(); // 定义集合来存放游戏中所以蘑菇

    public GamePanel() {// 构造函数
        new Thread() {// 创建并启动线程，让程序一直出现蘑菇
            public void run() {
                while (true) {
                    mushroomEnter(); // 创建蘑菇
                    for (int i = 0; i < ms.size(); i++) {// 让蘑菇移动起来
                        Mushroom m = ms.get(i); // 创建一个蘑菇
                        if (!m.isDie) {// 如果蘑菇没有死亡
                            m.x -= 5; // 则让蘑菇向左移动
                        }

                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }

                }
            }
        }.start();
        new Thread() {// 创建并启动一个线程，控制马里奥的垂直上抛运动
            public void run() {
                while (true) {
                    mario.step();// 让马里奥做垂直上抛运动
                    caimogu(); // 检测马里奥是否踩到了蘑菇
                    try {
                        Thread.sleep(20);// 让线程休眠指定的毫秒数
                        repaint(); // 刷新画面
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }

                }

            }
        }.start();

        setBackground(Color.black);
        KeyAdapter adapter = new KeyAdapter() { // 创建键盘适配器
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_A) {
                    mario.dir = App.DIR_LEFT;
                    mario.x -= mario.mvSpeed;
                    mario.changeImg();
                }
                if (code == KeyEvent.VK_D) {
                    mario.dir = App.DIR_RIGHT;
                    mario.x += mario.mvSpeed;
                    mario.changeImg();
                }
                if (code == KeyEvent.VK_SPACE) {
                    mario.jump();// 设置马里奥上抛运动的初始速度
                }
                repaint(); // 刷新界面，让马里奥显示在新的位置上
            }
        };
        addKeyListener(adapter); // 将适配器加入到监听器当中
    };

    int index = 0; // 记录mushroomEnter()方法执行的次数

    public void mushroomEnter() { // 创建一个蘑菇的方法
        index++; // 每执行一次，index加1
        if (index % 200 == 0) { // 这个方法每执行20次，创建一个蘑菇。对应上面那个线程中的Thread.sleep(20)
            Mushroom m = new Mushroom();
            ms.add(m);
        }

    }

    public void paint(Graphics g) {

        super.paint(g);
        /*
         * g.setColor(Color.GREEN); g.setFont(new Font("行楷", Font.BOLD, 50)); //
         * Font第一个参数是字体类型，第二个是加粗、斜体啥的，第三个是大小 g.drawString("zzz", 100, 100);
         */

        g.drawImage(bg, 0, 0, null);// g.drawImage(图片,横坐标,纵坐标,宽度,高度,null) 宽度高度这俩参数可以不写
        g.drawImage(mario.img, mario.x, mario.y, mario.w, mario.h, null);
        for (int i = 0; i < ms.size(); i++) {
            Mushroom m = ms.get(i);
            g.drawImage(m.img, m.x, m.y, m.w, m.h, null);
        }
    }

    public void caimogu() {
        /* 所有蘑菇都有可能被马里奥踩到 */
        /* 找到所有的蘑菇，和马里奥进行比较 */
        for (int i = 0; i < ms.size(); i++) {
            Mushroom m = ms.get(i); // 获取一个蘑菇
            
            if (m.caiBy(mario)) { // 如果这个蘑菇被马里奥踩到
                mario.jump();// 如果踩到了，就让马里奥跳一下
                m.goDie(); // 踩到了之后，让蘑菇死亡
                /* 下面让蘑菇死亡后显示一段时间后就消失 */
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() { // 需要计时器做什么就写在这个run()方法中
                        ms.remove(m);  //将蘑菇从蘑菇集合中删除
                    }

                }, 1000);
            }
        }
    }



}