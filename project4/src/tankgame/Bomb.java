package tankgame;

public class Bomb {
    int x;
    int y;
    int life = 9; // ը��������ֵ
    boolean isLive = true; // ը���Ƿ���

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown() { //��������ֵ�ķ���,�����Ϊ�������ը�����ֱ�ը��Ч��
        if (life > 0) {
            life--;  //����ġ��������ö��������������ᣬ�������Ϊһ֡һ֡�Ĳ�����
        } else {
            isLive = false;
        }
    }

}
