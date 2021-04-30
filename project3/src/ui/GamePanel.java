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
    BufferedImage bg = ImageUtil.getImg("/img/background/����1.jpg");
    Mario mario = new Mario();
    List<Mushroom> ms = new ArrayList<Mushroom>(); // ���弯���������Ϸ������Ģ��

    public GamePanel() {// ���캯��
        new Thread() {// �����������̣߳��ó���һֱ����Ģ��
            public void run() {
                while (true) {
                    mushroomEnter(); // ����Ģ��
                    for (int i = 0; i < ms.size(); i++) {// ��Ģ���ƶ�����
                        Mushroom m = ms.get(i); // ����һ��Ģ��
                        if (!m.isDie) {// ���Ģ��û������
                            m.x -= 5; // ����Ģ�������ƶ�
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
        new Thread() {// ����������һ���̣߳���������µĴ�ֱ�����˶�
            public void run() {
                while (true) {
                    mario.step();// �����������ֱ�����˶�
                    caimogu(); // ���������Ƿ�ȵ���Ģ��
                    try {
                        Thread.sleep(20);// ���߳�����ָ���ĺ�����
                        repaint(); // ˢ�»���
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }

                }

            }
        }.start();

        setBackground(Color.black);
        KeyAdapter adapter = new KeyAdapter() { // ��������������
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
                    mario.jump();// ��������������˶��ĳ�ʼ�ٶ�
                }
                repaint(); // ˢ�½��棬���������ʾ���µ�λ����
            }
        };
        addKeyListener(adapter); // �����������뵽����������
    };

    int index = 0; // ��¼mushroomEnter()����ִ�еĴ���

    public void mushroomEnter() { // ����һ��Ģ���ķ���
        index++; // ÿִ��һ�Σ�index��1
        if (index % 200 == 0) { // �������ÿִ��20�Σ�����һ��Ģ������Ӧ�����Ǹ��߳��е�Thread.sleep(20)
            Mushroom m = new Mushroom();
            ms.add(m);
        }

    }

    public void paint(Graphics g) {

        super.paint(g);
        /*
         * g.setColor(Color.GREEN); g.setFont(new Font("�п�", Font.BOLD, 50)); //
         * Font��һ���������������ͣ��ڶ����ǼӴ֡�б��ɶ�ģ��������Ǵ�С g.drawString("zzz", 100, 100);
         */

        g.drawImage(bg, 0, 0, null);// g.drawImage(ͼƬ,������,������,���,�߶�,null) ��ȸ߶������������Բ�д
        g.drawImage(mario.img, mario.x, mario.y, mario.w, mario.h, null);
        for (int i = 0; i < ms.size(); i++) {
            Mushroom m = ms.get(i);
            g.drawImage(m.img, m.x, m.y, m.w, m.h, null);
        }
    }

    public void caimogu() {
        /* ����Ģ�����п��ܱ�����²ȵ� */
        /* �ҵ����е�Ģ����������½��бȽ� */
        for (int i = 0; i < ms.size(); i++) {
            Mushroom m = ms.get(i); // ��ȡһ��Ģ��
            
            if (m.caiBy(mario)) { // ������Ģ��������²ȵ�
                mario.jump();// ����ȵ��ˣ������������һ��
                m.goDie(); // �ȵ���֮����Ģ������
                /* ������Ģ����������ʾһ��ʱ������ʧ */
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() { // ��Ҫ��ʱ����ʲô��д�����run()������
                        ms.remove(m);  //��Ģ����Ģ��������ɾ��
                    }

                }, 1000);
            }
        }
    }



}