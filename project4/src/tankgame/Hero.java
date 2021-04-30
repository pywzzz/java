package tankgame;

import java.util.Vector;



public class Hero extends Tank {
    Shot shot = null; // ����һ��shot���󣬼�һ������߳�
    Vector<Shot> shots = new Vector<>(); //��Vector��Ŷ���ӵ����Ӷ�ʵ�ַ���෢�ӵ�

    public Hero(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank() {

        if (shots.size()==5) {  //��֤��ͬһʱ��ֻ�ܴ��ڷ���5���ӵ�
            return;
        }

        switch (getDirect()) { // ��ȡ���̹�˵ķ���
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

        shots.add(shot);//���´�����shot�Ž�shots��

        new Thread(shot).start(); // �����������߳�
    }

}
