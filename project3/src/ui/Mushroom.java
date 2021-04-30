package ui;

import java.awt.image.BufferedImage;

public class Mushroom {
    BufferedImage img;
    int x;
    int y;
    int w;
    int h;
    BufferedImage deadImg = ImageUtil.getImg("/img/monster/triangle3.png");

    public Mushroom() {
        img = ImageUtil.getImg("/img/monster/triangle1.png");
        w = img.getWidth();
        h = img.getHeight();
        x = 900 - w;
        y = 600 - h - 50;
    }

    public boolean caiBy(Mario m) {
        boolean hit = x <= m.x + m.w && x >= m.x - w && y <= m.y + m.h;
        return hit && m.isJump; // �ȵ���������Ծ״̬�ͷ���true��
    }

    boolean isDie; // ��Ģ���Ƿ�����

    public void goDie() {
        img = deadImg; // Ģ����������Ļ�����ʾ����������ͼƬ
        isDie = true; // Ģ���Ѿ�����
        w = img.getWidth();
        h = img.getHeight();
        y = 600 - h - 50; // ��������Ģ�������λ��

    }

}
