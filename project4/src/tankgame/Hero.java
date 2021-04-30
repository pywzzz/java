package tankgame;

import java.util.Vector;



public class Hero extends Tank {
    Shot shot = null; // 定义一个shot对象，即一个射击线程
    Vector<Shot> shots = new Vector<>(); //用Vector存放多个子弹，从而实现发射多发子弹

    public Hero(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank() {

        if (shots.size()==5) {  //保证你同一时间只能存在发射5颗子弹
            return;
        }

        switch (getDirect()) { // 获取你的坦克的方向
        case 0:
            shot = new Shot(getX() + 20, getY(), 0);
            break;
        case 1:
            shot = new Shot(getX() + 60, getY() + 20, 1);
            break;
        case 2:
            shot = new Shot(getX() + 20, getY() + 60, 2);
            break;
        case 3:
            shot = new Shot(getX(), getY() + 20, 3);
            break;

        }

        shots.add(shot);//把新创建的shot放进shots中

        new Thread(shot).start(); // 启动这个射击线程
    }

}
