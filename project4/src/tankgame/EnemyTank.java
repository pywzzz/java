package tankgame;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {

    Vector<Shot> shots = new Vector<>();// �ڵ��˵�̹������ʹ��Vector������Shot
    Vector<EnemyTank> enemyTanks = new Vector<>(); // ����������ڻ�ȡ����̹�ˣ�Ϊ����̹���ص���׼��

    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    // ����������ڽ�MyPanel�е�Vector<EnemyTank> enemyTanks = new Vector<>();�����Ա
    // ���õ�EnemyTank.java�е�һ����Ա
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    // ����ķ��������жϵ�ǰ���������̹���Ƿ��enemyTanks�е�����̹�˷����ص�����ײ
    public boolean isTouchEnemyTank() {

        // �����switch��������жϵ�ǰ���������̹�˵ķ���
        switch (this.getDirect()) {// �����д this. ֱ��д��getDirect()Ҳ��
        case 0:
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i); // ȡ��̹��
                if (enemyTank != this) { // �����Լ�ȥ�Ƚϣ����Լ��Ƚ���Զ������ײ�ģ�
                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {// �������̹�˵ķ������ϻ�����
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {// ��ǰ̹�����Ͻ�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                        if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {// ��ǰ̹�����Ͻ�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                    }
                    if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {// �������̹�˵ķ������������
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {// ��ǰ̹�����Ͻ�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                        if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 60
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {// ��ǰ̹�����Ͻ�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                    }
                }
            }
            break;
        case 1:
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i); // ȡ��̹��
                if (enemyTank != this) { // �����Լ�ȥ�Ƚϣ����Լ��Ƚ���Զ������ײ�ģ�
                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {// �������̹�˵ķ������ϻ�����
                        if (this.getX() + 60 >= enemyTank.getX() + 60 && this.getX() <= enemyTank.getX() + 40
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {// ��ǰ̹�����Ͻ�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                        if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 40
                                && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 60) {// ��ǰ̹�����½�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                    }
                    if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {// �������̹�˵ķ������������
                        if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {// ��ǰ̹�����Ͻ�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                        if (this.getX() + 60 >= enemyTank.getX() && this.getX() + 60 <= enemyTank.getX() + 60
                                && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 40) {// ��ǰ̹�����½�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                    }
                }
            }
            break;
        case 2:
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i); // ȡ��̹��
                if (enemyTank != this) { // �����Լ�ȥ�Ƚϣ����Լ��Ƚ���Զ������ײ�ģ�
                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {// �������̹�˵ķ������ϻ�����
                        if (this.getX() >= enemyTank.getX() + 60 && this.getX() <= enemyTank.getX() + 40
                                && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 60) {// ��ǰ̹�����½�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                        if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 40
                                && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 60) {// ��ǰ̹�����½�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                    }
                    if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {// �������̹�˵ķ������������
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 40) {// ��ǰ̹�����½�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                        if (this.getX() + 40 >= enemyTank.getX() && this.getX() + 40 <= enemyTank.getX() + 60
                                && this.getY() + 60 >= enemyTank.getY() && this.getY() + 60 <= enemyTank.getY() + 40) {// ��ǰ̹�����½�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                    }
                }
            }
            break;
        case 3:
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i); // ȡ��̹��
                if (enemyTank != this) { // �����Լ�ȥ�Ƚϣ����Լ��Ƚ���Զ������ײ�ģ�
                    if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {// �������̹�˵ķ������ϻ�����
                        if (this.getX() >= enemyTank.getX() + 60 && this.getX() <= enemyTank.getX() + 40
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 60) {// ��ǰ̹�����Ͻ�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 40
                                && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 60) {// ��ǰ̹�����½�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                    }
                    if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {// �������̹�˵ķ������������
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                && this.getY() >= enemyTank.getY() && this.getY() <= enemyTank.getY() + 40) {// ��ǰ̹�����Ͻ�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                        if (this.getX() >= enemyTank.getX() && this.getX() <= enemyTank.getX() + 60
                                && this.getY() + 40 >= enemyTank.getY() && this.getY() + 40 <= enemyTank.getY() + 40) {// ��ǰ̹�����½�����
                            return true; // �����Щ����������Ǿ���ײ����
                        }
                    }
                }
            }
            break;
        }

        return false;// ���û�н��뵽���switch��䣬����û��ײ��������false

    }

    @Override
    public void run() {

        while (true) {

            // ��������if������ʵ�ֵ��������Լ����ӵ�����֮�������ٷ����ӵ�
            if (isLive && shots.size() == 0) {// ����̹�˴������ʱ�ӵ�������û�ӵ�����size=0����������ͨ��size<5���õ���̹�˿�����෢��5���ӵ���

                Shot s = null;

                switch (getDirect()) { // �жϵ���̹�˵ķ���Ȼ�󴴽���Ӧ���ӵ�
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
                shots.add(s); // ������´������ӵ��ӽ��ӵ���
                new Thread(s).start(); // ����������ӵ����߳�
            }

            switch (getDirect()) {
            case 0:
                for (int i = 0; i < 30; i++) {// ���for��Ϊ����̹����һ���������ƶ�30�������ٱ䷽�����������������Ȼ
                    if (getY() > 0 && !isTouchEnemyTank()) { // ��ֹ�����Լ�û����ײ����ĵ���̹��
                        moveUp();
                    }

                    try {
                        Thread.sleep(50); // �㲻���ߵĻ�������̹�˺ܶ�ʱ���ִmoveUpɶ�ĺö�ö�Σ��㿴��������
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case 1:
                for (int i = 0; i < 30; i++) {// ���for��Ϊ����̹����һ���������ƶ�30�������ٱ䷽�����������������Ȼ
                    if (getX() + 60 < 1000 && !isTouchEnemyTank()) {// ��ֹ�����Լ�û����ײ����ĵ���̹��
                        moveRight();
                    }
                    try {
                        Thread.sleep(50); // �㲻���ߵĻ�������̹�˺ܶ�ʱ���ִmoveUpɶ�ĺö�ö�Σ��㿴��������
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 30; i++) {// ���for��Ϊ����̹����һ���������ƶ�30�������ٱ䷽�����������������Ȼ
                    if (getY() + 60 < 750 && !isTouchEnemyTank()) {// ��ֹ�����Լ�û����ײ����ĵ���̹��
                        moveDown();
                    }
                    try {
                        Thread.sleep(50); // �㲻���ߵĻ�������̹�˺ܶ�ʱ���ִmoveUpɶ�ĺö�ö�Σ��㿴��������
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 30; i++) {// ���for��Ϊ����̹����һ���������ƶ�30�������ٱ䷽�����������������Ȼ
                    if (getX() > 0 && !isTouchEnemyTank()) {// ��ֹ�����Լ�û����ײ����ĵ���̹��
                        moveLeft();
                    }
                    try {
                        Thread.sleep(50); // �㲻���ߵĻ�������̹�˺ܶ�ʱ���ִmoveUpɶ�ĺö�ö�Σ��㿴��������
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;

            }

            setDirect((int) (Math.random() * 4)); // ������õ���̹�˷���
            if (!isLive) { // ������˵Ļ�
                break; // �˳��߳�
            }

        }
    }
}
