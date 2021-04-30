package tankgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

//Ϊ����Panel��ͣ���ػ��ӵ�����Ҫ��Mypanelʵ��Runnable�ӿڣ�����һ���߳�ʹ��
public class MyPanel extends JPanel implements KeyListener, Runnable {
    Hero hero = null; // ��ʼ���Լ���̹��

    // ����һ��Vector�����ڴ��ը��
    // ���ӵ�����̹��ʱ������һ��Bomb����bombs
    Vector<Bomb> bombs = new Vector<>();

    Vector<Node> nodes = new Vector<>();// ����һ�����Node��Vector�����ڻָ�����̹�˵�����ͷ���

    Vector<EnemyTank> enemyTanks = new Vector<>(); // ��ʼ�����˵�̹�ˣ������Ƿŵ�Vector���������
    int EnemyTankSize = 5; // ����̹�˵�����

    // ��������ͼƬ��������ʾ��ըЧ��
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) { // ���key�����û��Լ�ȥѡ�����ؿ���Ϸ���Ƕ���

        // �������ж����Ǹ��浵�ļ��Ƿ����
        // ������ڣ�������ִ�У���������ڣ�����ʾ�����ܿ�������Ϸ�����ܼ�����һ�֡����һ��key="1"��
        File file = new File(Recorder.getRecordFile());
        if (file.exists()) {
            nodes = Recorder.getNodesAndEnemyTankRec();// getNodesAndEnemyTankRec���������returnֵ����nodes��ûɶ�������
        } else {
            System.out.println("�浵�����ڣ�ֻ�ܿ�������Ϸ������");
            key = "1";
        }

        Recorder.setEnemyTanks(enemyTanks); // ��MyPanel�����enemyTanks���ø�Recorder��enemyTanks

        hero = new Hero(300, 500); // ��ʼ���Լ���̹��

        switch (key) {
        case "1": // ���¿�ʼ��Ϸ
            for (int i = 0; i < EnemyTankSize; i++) { // ��forѭ������ʼ�����˵�̹��
                EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);

                // ���潫enemyTanks���ø�enemyTank���Ӷ����ܴﵽ������ײ��Ч��
                enemyTank.setEnemyTanks(enemyTanks);

                enemyTank.setDirect(2);
                new Thread(enemyTank).start();// ��������̹�˵��̣߳������Ƕ�����
                Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect()); // ������̹�˼���һ���ӵ�
                enemyTank.shots.add(shot);// ����Щ�ӵ����뵽enemyTank�е�Vector��Ա��
                new Thread(shot).start(); // ����shot����

                enemyTanks.add(enemyTank);
            }
            break;

        case "2": // �����Ͼ���Ϸ
            for (int i = 0; i < nodes.size(); i++) { // ��forѭ������ʼ�����˵�̹��

                Node node = nodes.get(i);

                EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());

                // ���潫enemyTanks���ø�enemyTank���Ӷ����ܴﵽ������ײ��Ч��
                enemyTank.setEnemyTanks(enemyTanks);

                enemyTank.setDirect(node.getDirect());
                new Thread(enemyTank).start();// ��������̹�˵��̣߳������Ƕ�����
                Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect()); // ������̹�˼���һ���ӵ�
                enemyTank.shots.add(shot);// ����Щ�ӵ����뵽enemyTank�е�Vector��Ա��
                new Thread(shot).start(); // ����shot����

                enemyTanks.add(enemyTank);
            }
            break;

        default:
            System.out.println("����������󡣡���");
        }

        hero.setSpeed(10);

        // ��������ʼ��ͼƬ����
        // p37 15:26�������������Panel������Mypanel���쳣Ӧ������Ϊ������IDE��ͼƬ�ŵ���һ���Դ���out�ļ��е������
        // �����õ���vscode�����Բ�һ����
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/resource/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/resource/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/resource/bomb_3.gif"));

        new AePlayWave("src/resource/111.wav").start();// ��������

    }

    public void showInfo(Graphics g) {// ������������ڽ�������ʾ�ҷ����ٵ���̹�˵������Ϣ

        g.setColor(Color.black);
        Font font = new Font("����", Font.BOLD, 25); // ���壬�Ӵ֣��ֺ�Ϊ25
        g.setFont(font);

        g.drawString("�ۼƻ��ٵз�̹��", 1020, 30);
        drawTank(1020, 60, g, 0, 0);
        g.setColor(Color.black); // ��ΪdrawTank�����и���һ�λ���g����ɫ������������øĻ���
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);

    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.fillRect(0, 0, 1000, 750); // ��һ����������Ϊ��Ϸ������Ĭ���Ǻ�ɫ

        showInfo(g);

        if (hero != null && hero.isLive) { // ������̹�˴�����û����
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1); // �ͻ����Լ���̹��
        }

        for (int i = 0; i < hero.shots.size(); i++) { // ��hero���ӵ�����shots������ȡ��������
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive) { // �������ӵ���Ϊ���Ҵ��
                g.draw3DRect(shot.x, shot.y, 2, 2, false); // �������һ���ӵ�
            } else {// �����shot�����Ѿ���Ч���Ǿʹ�shots�������õ�
                hero.shots.remove(shot);
            }
        }

        // ���bombs�������ж��󣨼���ը�������ͻ�����������
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i); // ȡ��ը��
            if (bomb.life > 6) { // ���ݵ�ǰ��lifeֵȥ������Ӧ��ͼƬ
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown(); // �����ը��������ֵ���٣��ﵽһ֡һ֡��Ч��ɶ�ģ��ñ�ը�Ķ���������������
            if (bomb.life == 0) { // �������ֵΪ0
                bombs.remove(bomb); // �ǾͰ����ը���Ӽ�����ɾ��
            }

        }

        for (int i = 0; i < enemyTanks.size(); i++) { // ��forѭ���������˵�̹��
            EnemyTank enemyTank = enemyTanks.get(i); // ��Vector��ȡ��̹��
            if (enemyTank.isLive) { // �������̹��û��������ȥ������̹�ˡ������ӵ�ɶ��
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                for (int j = 0; j < enemyTank.shots.size(); j++) { // ���������ÿ������̹���е��ӵ������е�ÿһ���ӵ�
                    Shot shot = enemyTank.shots.get(j); // ȡ������̹���е�һ���ӵ�
                    if (shot.isLive) { // ����ӵ����
                        g.draw3DRect(shot.x, shot.y, 2, 2, false); // �����ӵ�
                    } else {// �������ӵ�������
                        enemyTank.shots.remove(shot); // ��ô�Ͱ�����ӵ���Vector���Ƴ�
                    }
                }
            }
        }
    }

    public void drawTank(int x, int y, Graphics g, int direct, int type) { // Ūһ��ר�Ż�̹�˵ķ���
        switch (type) {

        case 0: // 0������˵�̹��
            g.setColor(Color.cyan);
            break;

        case 1: // 1�����Լ���̹��
            g.setColor(Color.yellow);
            break;

        }

        switch (direct) {
        case 0: // ��������
            g.fill3DRect(x, y, 10, 60, false); // ��̹�����Ĵ�
            g.fill3DRect(x + 30, y, 10, 60, false); // ��̹�����Ĵ�
            g.fill3DRect(x + 10, y + 10, 20, 40, false); // ��̹����
            g.fillOval(x + 10, y + 20, 20, 20); // ����̹�˸�
            g.drawLine(x + 20, y + 30, x + 20, y); // �����ڹ�
            break;
        case 1: // ��������
            g.fill3DRect(x, y, 60, 10, false); // ��̹�����Ĵ�
            g.fill3DRect(x, y + 30, 60, 10, false); // ��̹�����Ĵ�
            g.fill3DRect(x + 10, y + 10, 40, 20, false); // ��̹����
            g.fillOval(x + 20, y + 10, 20, 20); // ����̹�˸�
            g.drawLine(x + 30, y + 20, x + 60, y + 20); // �����ڹ�
            break;
        case 2: // ��������
            g.fill3DRect(x, y, 10, 60, false); // ��̹�����Ĵ�
            g.fill3DRect(x + 30, y, 10, 60, false); // ��̹�����Ĵ�
            g.fill3DRect(x + 10, y + 10, 20, 40, false); // ��̹����
            g.fillOval(x + 10, y + 20, 20, 20); // ����̹�˸�
            g.drawLine(x + 20, y + 30, x + 20, y + 60); // �����ڹ�
            break;
        case 3: // ��������
            g.fill3DRect(x, y, 60, 10, false); // ��̹�����Ĵ�
            g.fill3DRect(x, y + 30, 60, 10, false); // ��̹�����Ĵ�
            g.fill3DRect(x + 10, y + 10, 40, 20, false); // ��̹����
            g.fillOval(x + 20, y + 10, 20, 20); // ����̹�˸�
            g.drawLine(x + 30, y + 20, x, y + 20); // �����ڹ�
            break;
        }
    }

    // ����ӵ��ж��Ƿ����ʱ���͵ð����ǵ��ӵ����������еġ�ÿһ���ӵ���ȡ����Ȼ��͵��˵��ӵ�ȥ���ж�
    public void hitEnemyTank() {
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            // �������ж�����ӵ��Ƿ�����˵���̹��
            if (shot != null && shot.isLive) {// ������ӵ�������ң��ӵ���Ϊ�գ������㰴��J�����ӵ�ʱ���Ž���������ж�
                for (int i = 0; i < enemyTanks.size(); i++) { // �ͱ�����������̹��
                    EnemyTank enemyTank = enemyTanks.get(i); // ��õ���̹��
                    hitTank(shot, enemyTank); // ������ķ���ȥ���Ƿ����
                }
            }
        }
    }

    // �ж��Լ����ӵ��Ƿ����̹��
    public void hitTank(Shot s, Tank tank) {
        switch (tank.getDirect()) {
        case 0: // �����д��������Ϊ0��2�������һ����
        case 2:// ���Թ�д�����
            if (s.x > tank.getX() && s.x < tank.getX() + 40 && s.y > tank.getY() && s.y < tank.getY() + 60) { // ����ӵ������˵���̹��
                s.isLive = false; // ����ӵ�����
                tank.isLive = false; // ����̹������
                enemyTanks.remove(tank); // ��̹�������󣬽�����Vector�������õ�

                if (tank instanceof EnemyTank) {// �����������ٵ�̹��ʱ�з�̹�˵Ļ�
                    Recorder.addAllEnemyTankNum();// ���ҷ������˵з�̹��֮�󣬾Ͷ�addEnemyTank++
                }

                // ����Bomb���󣬽�����뵽bombs������
                Bomb bomb = new Bomb(tank.getX(), tank.getY());
                bombs.add(bomb);
            }
            break;
        case 1:
        case 3:
            if (s.x > tank.getX() && s.x < tank.getX() + 60 && s.y > tank.getY() && s.y < tank.getY() + 40) { // ����ӵ������˵���̹��
                s.isLive = false; // ����ӵ�����
                tank.isLive = false; // ����̹������
                enemyTanks.remove(tank); // ��̹�������󣬽�����Vector�������õ�

                if (tank instanceof EnemyTank) {// �����������ٵ�̹��ʱ�з�̹�˵Ļ�
                    Recorder.addAllEnemyTankNum();// ���ҷ������˵з�̹��֮�󣬾Ͷ�addEnemyTank++
                }

                // �������Bomb���󣬽�����뵽bombs������
                Bomb bomb = new Bomb(tank.getX(), tank.getY());
                bombs.add(bomb);
            }
            break;

        }
    }

    // �жϵз�̹���Ƿ������
    public void hitHero() {

        for (int i = 0; i < enemyTanks.size(); i++) {// ��ȥ��������̹��
            EnemyTank enemyTank = enemyTanks.get(i);// Ȼ��ȡ�����˵�̹��
            for (int j = 0; j < enemyTank.shots.size(); j++) { // ��ȥ����û��̹�˵ĵ�ҩ��
                Shot shot = enemyTank.shots.get(j); // ��ȡ����ҩ���е��ӵ�
                // ����ȥ�ж�����ӵ��Ƿ���������̹��
                if (hero.isLive && shot.isLive) {
                    hitTank(shot, hero);
                }

            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            if (hero.getY() > 0) {// ��ֹ����
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if (hero.getX() + 60 < 1000) {// ��ֹ����
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if (hero.getY() + 60 < 750) {// ��ֹ����
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if (hero.getX() > 0) {// ��ֹ����
                hero.moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_J) { // �����J
            // ���Ϊ�գ����㻹û�з����ӵ���
            /*
             * ���ߣ��ӵ�������󻹴��ڣ����ͣ���������ֻ���ӵ�����̣߳����ӵ��������û����ʧ��
             * ���㲻������Ļ����㷢��һ����J�����ٷ����ˣ���ʹ����ӵ������Ѿ���������ǽ�������ˣ�
             */
            // if (hero.shot == null || !hero.shot.isLive) {//��һ���Ƿ���һ���ӵ�
            // hero.shotEnemyTank(); // ��ʼ���
            // }
            hero.shotEnemyTank();// �������ӵ�

        }

        this.repaint(); // �ػ棬Ҫ��Ȼ������д�Ķ�û��
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(100);// ÿ��100ms���ػ�һ�Σ���ʱ�ӵ������ƶ�������
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // // �������ж�����ӵ��Ƿ�����˵���̹��
            // if (hero.shot != null && hero.shot.isLive) {//
            // ������ӵ�������ң��ӵ���Ϊ�գ������㰴��J�����ӵ�ʱ���Ž���������ж�
            // for (int i = 0; i < enemyTanks.size(); i++) { // �ͱ�����������̹��
            // EnemyTank enemyTank = enemyTanks.get(i); // ��õ���̹��
            // hitTank(hero.shot, enemyTank); // ������ķ���ȥ���Ƿ����
            // }
            // }

            // �����Ƿ���һ���ӵ���Ӧ�����
            hitEnemyTank(); // ��������Ƕ�Ӧ�������ӵ�
            hitHero();// �жϵ���̹���Ƿ��������
            this.repaint();
        }
    }

}
