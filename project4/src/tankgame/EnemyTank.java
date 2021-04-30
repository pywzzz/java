package tankgame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {

    Vector<Shot> shots = new Vector<>();// 在敌人的坦克类中使用Vector保存多个Shot
    Vector<EnemyTank> enemyTanks = new Vector<>(); // 这个东西用于获取敌人坦克，为避免坦克重叠做准备

    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    // 这个方法用于将MyPanel中的Vector<EnemyTank> enemyTanks = new Vector<>();这个成员
    // 设置到EnemyTank.java中的一个成员
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    // 下面的方法用于判断当前的这个敌人坦克是否和enemyTanks中的其他坦克发生重叠或碰撞
    public boolean isTouchEnemyTank() {

        // 下面的switch语句用于判断当前的这个敌人坦克的方向
        switch (this.getDirect()) {// 这儿不写 this. 直接写个getDirect()也行
        case 0:
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i); // 取出坦克
                if (enemyTank != this) { // 不和自己去比较（和自己比较永远都是碰撞的）
                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {// 如果敌人坦克的方向是上或者下
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {// 当前坦克左上角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                        if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {// 当前坦克右上角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                    }
                    if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {// 如果敌人坦克的方向是左或者右
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {// 当前坦克左上角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                        if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 60
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {// 当前坦克右上角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                    }
                }
            }
            break;
        case 1:
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i); // 取出坦克
                if (enemyTank != this) { // 不和自己去比较（和自己比较永远都是碰撞的）
                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {// 如果敌人坦克的方向是上或者下
                        if (this.getX() + 60 >= enemyTank.getX() + 60 && this.getX() <= enemyTank.getX() + 40
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {// 当前坦克右上角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                        if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 40
                                && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 60) {// 当前坦克右下角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                    }
                    if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {// 如果敌人坦克的方向是左或者右
                        if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {// 当前坦克右上角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                        if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60
                                && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 40) {// 当前坦克右下角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                    }
                }
            }
            break;
        case 2:
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i); // 取出坦克
                if (enemyTank != this) { // 不和自己去比较（和自己比较永远都是碰撞的）
                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {// 如果敌人坦克的方向是上或者下
                        if (this.getX() >= enemyTank.getX() + 60 && this.getX() <= enemyTank.getX() + 40
                                && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 60) {// 当前坦克左下角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                        if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40
                                && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 60) {// 当前坦克右下角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                    }
                    if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {// 如果敌人坦克的方向是左或者右
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 40) {// 当前坦克左下角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                        if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 60
                                && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 40) {// 当前坦克右下角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                    }
                }
            }
            break;
        case 3:
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i); // 取出坦克
                if (enemyTank != this) { // 不和自己去比较（和自己比较永远都是碰撞的）
                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {// 如果敌人坦克的方向是上或者下
                        if (this.getX() >= enemyTank.getX() + 60 && this.getX() <= enemyTank.getX() + 40
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {// 当前坦克左上角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 60) {// 当前坦克左下角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                    }
                    if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {// 如果敌人坦克的方向是左或者右
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {// 当前坦克左上角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 40) {// 当前坦克左下角坐标
                            return true; // 如果这些情况发生了那就是撞上了
                        }
                    }
                }
            }
            break;
        }

        return false;// 如果没有进入到这个switch语句，就是没有撞到，返回false

    }

    @Override
    public void run() {

        while (true) {

            // 下面的这个if代码来实现敌人能在自己的子弹消亡之后重新再发射子弹
            if (isLive && shots.size() == 0) {// 敌人坦克存活且这时子弹库里面没子弹（即size=0）（还可以通过size<5云让敌人坦克可以最多发射5发子弹）

                Shot s = null;

                switch (getDirect()) { // 判断敌人坦克的方向，然后创建相应的子弹
                case 0:
                    s = new Shot(getX() + 20, getY(), 0);
                    break;
                case 1:
                    s = new Shot(getX() + 60, getY() + 20, 1);
                    break;
                case 2:
                    s = new Shot(getX() + 20, getY() + 60, 2);
                    break;
                case 3:
                    s = new Shot(getX(), getY() + 20, 3);
                    break;
                }
                shots.add(s); // 把这个新创建的子弹加进子弹库
                new Thread(s).start(); // 启动这个新子弹的线程
            }

            switch (getDirect()) {
            case 0:
                for (int i = 0; i < 30; i++) {// 这个for是为了让坦克在一个方向上移动30个像素再变方向，这样看起来会更自然
                    if (getY() > 0 && !isTouchEnemyTank()) { // 防止出界以及没有碰撞到别的敌人坦克
                        moveUp();
                    }

                    try {
                        Thread.sleep(50); // 你不休眠的话，敌人坦克很短时间就执moveUp啥的好多好多次，你看都看不到
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case 1:
                for (int i = 0; i < 30; i++) {// 这个for是为了让坦克在一个方向上移动30个像素再变方向，这样看起来会更自然
                    if (getX() + 60 < 1000 && !isTouchEnemyTank()) {// 防止出界以及没有碰撞到别的敌人坦克
                        moveRight();
                    }
                    try {
                        Thread.sleep(50); // 你不休眠的话，敌人坦克很短时间就执moveUp啥的好多好多次，你看都看不到
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 30; i++) {// 这个for是为了让坦克在一个方向上移动30个像素再变方向，这样看起来会更自然
                    if (getY() + 60 < 750 && !isTouchEnemyTank()) {// 防止出界以及没有碰撞到别的敌人坦克
                        moveDown();
                    }
                    try {
                        Thread.sleep(50); // 你不休眠的话，敌人坦克很短时间就执moveUp啥的好多好多次，你看都看不到
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 30; i++) {// 这个for是为了让坦克在一个方向上移动30个像素再变方向，这样看起来会更自然
                    if (getX() > 0 && !isTouchEnemyTank()) {// 防止出界以及没有碰撞到别的敌人坦克
                        moveLeft();
                    }
                    try {
                        Thread.sleep(50); // 你不休眠的话，敌人坦克很短时间就执moveUp啥的好多好多次，你看都看不到
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;

            }

            setDirect((int) (Math.random() * 4)); // 随机设置敌人坦克方向
            if (!isLive) { // 如果死了的话
                break; // 退出线程
            }

        }
    }
}
