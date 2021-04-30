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

//为了让Panel不停地重绘子弹，需要让Mypanel实现Runnable接口，当成一个线程使用
public class MyPanel extends JPanel implements KeyListener, Runnable {
    Hero hero = null; // 初始化自己的坦克

    // 定义一个Vector，用于存放炸弹
    // 当子弹击中坦克时，加入一个Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();

    Vector<Node> nodes = new Vector<>();// 定义一个存放Node的Vector，用于恢复敌人坦克的坐标和方向

    Vector<EnemyTank> enemyTanks = new Vector<>(); // 初始化敌人的坦克，把他们放到Vector这个数组中
    int EnemyTankSize = 5; // 敌人坦克的数量

    // 定义三张图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) { // 这个key来让用户自己去选择是重开游戏还是读档

        // 下面来判断你那个存档文件是否存在
        // 如果存在，则正常执行；如果不存在，会提示：你能开启新游戏，不能继续上一局。（且会把key="1"）
        File file = new File(Recorder.getRecordFile());
        if (file.exists()) {
            nodes = Recorder.getNodesAndEnemyTankRec();// getNodesAndEnemyTankRec这个方法的return值就是nodes，没啥问题这儿
        } else {
            System.out.println("存档不存在，只能开启新游戏。。。");
            key = "1";
        }

        Recorder.setEnemyTanks(enemyTanks); // 将MyPanel对象的enemyTanks设置给Recorder的enemyTanks

        hero = new Hero(300, 500); // 初始化自己的坦克

        switch (key) {
        case "1": // 重新开始游戏
            for (int i = 0; i < EnemyTankSize; i++) { // 用for循环来初始化敌人的坦克
                EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);

                // 下面将enemyTanks设置给enemyTank，从而才能达到避免碰撞的效果
                enemyTank.setEnemyTanks(enemyTanks);

                enemyTank.setDirect(2);
                new Thread(enemyTank).start();// 启动敌人坦克的线程，让它们动起来
                Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect()); // 给敌人坦克加上一颗子弹
                enemyTank.shots.add(shot);// 将这些子弹加入到enemyTank中的Vector成员中
                new Thread(shot).start(); // 启动shot对象

                enemyTanks.add(enemyTank);
            }
            break;

        case "2": // 继续上局游戏
            for (int i = 0; i < nodes.size(); i++) { // 用for循环来初始化敌人的坦克

                Node node = nodes.get(i);

                EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());

                // 下面将enemyTanks设置给enemyTank，从而才能达到避免碰撞的效果
                enemyTank.setEnemyTanks(enemyTanks);

                enemyTank.setDirect(node.getDirect());
                new Thread(enemyTank).start();// 启动敌人坦克的线程，让它们动起来
                Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect()); // 给敌人坦克加上一颗子弹
                enemyTank.shots.add(shot);// 将这些子弹加入到enemyTank中的Vector成员中
                new Thread(shot).start(); // 启动shot对象

                enemyTanks.add(enemyTank);
            }
            break;

        default:
            System.out.println("你的输入有误。。。");
        }

        hero.setSpeed(10);

        // 下面来初始化图片对象
        // p37 15:26这儿，它这里用Panel而不是Mypanel不异常应该是因为它的用IDE把图片放到了一个自带的out文件夹的问题吧
        // 而你用的是vscode，所以不一样吧
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/resource/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/resource/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/resource/bomb_3.gif"));

        new AePlayWave("src/resource/111.wav").start();// 播放音乐

    }

    public void showInfo(Graphics g) {// 这个方法用于在界面上显示我方击毁敌人坦克的相关信息

        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25); // 宋体，加粗，字号为25
        g.setFont(font);

        g.drawString("累计击毁敌方坦克", 1020, 30);
        drawTank(1020, 60, g, 0, 0);
        g.setColor(Color.black); // 因为drawTank方法中改了一次画笔g的颜色，所以这儿还得改回来
        g.drawString(Recorder.getAllEnemyTankNum() + "", 1080, 100);

    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.fillRect(0, 0, 1000, 750); // 画一个填充矩形作为游戏背景，默认是黑色

        showInfo(g);

        if (hero != null && hero.isLive) { // 如果你的坦克存在且没有死
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1); // 就画出自己的坦克
        }

        for (int i = 0; i < hero.shots.size(); i++) { // 将hero的子弹集合shots遍历，取出，绘制
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive) { // 如果你的子弹不为空且存活
                g.draw3DRect(shot.x, shot.y, 2, 2, false); // 画出你的一颗子弹
            } else {// 如果该shot对象已经无效，那就从shots集合中拿掉
                hero.shots.remove(shot);
            }
        }

        // 如果bombs集合中有对象（即有炸弹），就花把它画出来
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i); // 取出炸弹
            if (bomb.life > 6) { // 根据当前的life值去画出对应的图片
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown(); // 让这个炸弹的生命值减少，达到一帧一帧的效果啥的，让爆炸的动画看起来更连贯
            if (bomb.life == 0) { // 如果生命值为0
                bombs.remove(bomb); // 那就把这个炸弹从集合中删除
            }

        }

        for (int i = 0; i < enemyTanks.size(); i++) { // 用for循环画出敌人的坦克
            EnemyTank enemyTank = enemyTanks.get(i); // 从Vector中取出坦克
            if (enemyTank.isLive) { // 如果敌人坦克没有死，才去画敌人坦克、敌人子弹啥的
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                for (int j = 0; j < enemyTank.shots.size(); j++) { // 这个用来画每辆敌人坦克中的子弹集合中的每一颗子弹
                    Shot shot = enemyTank.shots.get(j); // 取出这辆坦克中的一颗子弹
                    if (shot.isLive) { // 如果子弹存活
                        g.draw3DRect(shot.x, shot.y, 2, 2, false); // 画出子弹
                    } else {// 如果这个子弹死亡了
                        enemyTank.shots.remove(shot); // 那么就把这颗子弹从Vector中移除
                    }
                }
            }
        }
    }

    public void drawTank(int x, int y, Graphics g, int direct, int type) { // 弄一个专门画坦克的方法
        switch (type) {

        case 0: // 0代表敌人的坦克
            g.setColor(Color.cyan);
            break;

        case 1: // 1代表自己的坦克
            g.setColor(Color.yellow);
            break;

        }

        switch (direct) {
        case 0: // 方向向上
            g.fill3DRect(x, y, 10, 60, false); // 画坦克左履带
            g.fill3DRect(x + 30, y, 10, 60, false); // 画坦克右履带
            g.fill3DRect(x + 10, y + 10, 20, 40, false); // 画坦克身
            g.fillOval(x + 10, y + 20, 20, 20); // 画出坦克盖
            g.drawLine(x + 20, y + 30, x + 20, y); // 画出炮管
            break;
        case 1: // 方向向右
            g.fill3DRect(x, y, 60, 10, false); // 画坦克左履带
            g.fill3DRect(x, y + 30, 60, 10, false); // 画坦克右履带
            g.fill3DRect(x + 10, y + 10, 40, 20, false); // 画坦克身
            g.fillOval(x + 20, y + 10, 20, 20); // 画出坦克盖
            g.drawLine(x + 30, y + 20, x + 60, y + 20); // 画出炮管
            break;
        case 2: // 方向向下
            g.fill3DRect(x, y, 10, 60, false); // 画坦克左履带
            g.fill3DRect(x + 30, y, 10, 60, false); // 画坦克右履带
            g.fill3DRect(x + 10, y + 10, 20, 40, false); // 画坦克身
            g.fillOval(x + 10, y + 20, 20, 20); // 画出坦克盖
            g.drawLine(x + 20, y + 30, x + 20, y + 60); // 画出炮管
            break;
        case 3: // 方向向左
            g.fill3DRect(x, y, 60, 10, false); // 画坦克左履带
            g.fill3DRect(x, y + 30, 60, 10, false); // 画坦克右履带
            g.fill3DRect(x + 10, y + 10, 40, 20, false); // 画坦克身
            g.fillOval(x + 20, y + 10, 20, 20); // 画出坦克盖
            g.drawLine(x + 30, y + 20, x, y + 20); // 画出炮管
            break;
        }
    }

    // 多颗子弹判断是否击中时，就得把我们的子弹集合中所有的、每一颗子弹都取出来然后和敌人的子弹去做判断
    public void hitEnemyTank() {
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            // 下面来判断你的子弹是否击中了敌人坦克
            if (shot != null && shot.isLive) {// 当你的子弹还存活且，子弹不为空，即，你按了J发射子弹时，才进行下面的判断
                for (int i = 0; i < enemyTanks.size(); i++) { // 就遍历敌人所有坦克
                    EnemyTank enemyTank = enemyTanks.get(i); // 获得敌人坦克
                    hitTank(shot, enemyTank); // 调用你的方法去判是否击中
                }
            }
        }
    }

    // 判断自己的子弹是否击中坦克
    public void hitTank(Shot s, Tank tank) {
        switch (tank.getDirect()) {
        case 0: // 这儿不写东西是因为0和2的情况是一样的
        case 2:// 所以光写这儿的
            if (s.x > tank.getX() && s.x < tank.getX() + 40 && s.y > tank.getY() && s.y < tank.getY() + 60) { // 你的子弹碰到了敌人坦克
                s.isLive = false; // 你的子弹死亡
                tank.isLive = false; // 敌人坦克死亡
                enemyTanks.remove(tank); // 当坦克死亡后，将它从Vector集合中拿掉

                if (tank instanceof EnemyTank) {// 如果这个被击毁的坦克时敌方坦克的话
                    Recorder.addAllEnemyTankNum();// 当我方击毁了敌方坦克之后，就对addEnemyTank++
                }

                // 创建Bomb对象，将其加入到bombs集合中
                Bomb bomb = new Bomb(tank.getX(), tank.getY());
                bombs.add(bomb);
            }
            break;
        case 1:
        case 3:
            if (s.x > tank.getX() && s.x < tank.getX() + 60 && s.y > tank.getY() && s.y < tank.getY() + 40) { // 你的子弹碰到了敌人坦克
                s.isLive = false; // 你的子弹死亡
                tank.isLive = false; // 敌人坦克死亡
                enemyTanks.remove(tank); // 当坦克死亡后，将它从Vector集合中拿掉

                if (tank instanceof EnemyTank) {// 如果这个被击毁的坦克时敌方坦克的话
                    Recorder.addAllEnemyTankNum();// 当我方击毁了敌方坦克之后，就对addEnemyTank++
                }

                // 创建你的Bomb对象，将其加入到bombs集合中
                Bomb bomb = new Bomb(tank.getX(), tank.getY());
                bombs.add(bomb);
            }
            break;

        }
    }

    // 判断敌方坦克是否击中你
    public void hitHero() {

        for (int i = 0; i < enemyTanks.size(); i++) {// 先去遍历敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);// 然后取出敌人的坦克
            for (int j = 0; j < enemyTank.shots.size(); j++) { // 再去遍历没辆坦克的弹药库
                Shot shot = enemyTank.shots.get(j); // 再取出弹药库中的子弹
                // 下面去判断这颗子弹是否击中了你的坦克
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
            if (hero.getY() > 0) {// 防止出界
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if (hero.getX() + 60 < 1000) {// 防止出界
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if (hero.getY() + 60 < 750) {// 防止出界
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if (hero.getX() > 0) {// 防止出界
                hero.moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_J) { // 如果按J
            // 如果为空（即你还没有发射子弹）
            /*
             * 或者（子弹这个对象还存在）（就，你消亡的只是子弹这个线程，而子弹这个对象并没有消失）
             * （你不加这个的话，你发射一发后按J不会再发射了，即使这颗子弹进程已经由于碰到墙壁消亡了）
             */
            // if (hero.shot == null || !hero.shot.isLive) {//这一堆是发射一颗子弹
            // hero.shotEnemyTank(); // 开始射击
            // }
            hero.shotEnemyTank();// 发射多颗子弹

        }

        this.repaint(); // 重绘，要不然你上面写的都没用
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(100);// 每隔100ms，重绘一次，这时子弹就能移动起来了
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // // 下面来判断你的子弹是否击中了敌人坦克
            // if (hero.shot != null && hero.shot.isLive) {//
            // 当你的子弹还存活且，子弹不为空，即，你按了J发射子弹时，才进行下面的判断
            // for (int i = 0; i < enemyTanks.size(); i++) { // 就遍历敌人所有坦克
            // EnemyTank enemyTank = enemyTanks.get(i); // 获得敌人坦克
            // hitTank(hero.shot, enemyTank); // 调用你的方法去判是否击中
            // }
            // }

            // 上面是发射一颗子弹适应的情况
            hitEnemyTank(); // 这种情况是对应发射多颗子弹
            hitHero();// 判断敌人坦克是否击中我们
            this.repaint();
        }
    }

}
